package com.keveon.demo;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Index tests.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
class IndexTests extends SpringDataJpaDemoApplicationTests {
    /**
     * Error.
     *
     * @throws Exception the exception
     */
    @Test
    void error() throws Exception {
        this.mockMvc
                .perform(get("/error")
                        .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
                        .requestAttr(RequestDispatcher.ERROR_REQUEST_URI, "/depts")
                        .requestAttr(RequestDispatcher.ERROR_MESSAGE,
                                "The tag 'http://localhost:8080/depts/-1' does not exist"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error", is("Bad Request")))
                .andExpect(jsonPath("timestamp", is(notNullValue())))
                .andExpect(jsonPath("status", is(400)))
                .andExpect(jsonPath("path", is(notNullValue())))
                .andDo(document("error",
                        responseFields(
                                fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
                                fieldWithPath("message").description("A description of the cause of the error"),
                                fieldWithPath("path").description("The path to which the request was made"),
                                fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
                                fieldWithPath("timestamp").description("The time, in milliseconds, at which the error occurred"))));
    }

    /**
     * Index.
     *
     * @throws Exception the exception
     */
    @Test
    void index() throws Exception {
        this.mockMvc.perform(get(basePath + "/"))
                .andExpect(status().isOk())
                .andDo(document("index",
                        links(
                                linkWithRel("depts").description("The <<resources-depts, Depts resource>>"),
                                linkWithRel("employees").description("The <<resources-employees, Employees resource>>"),
                                linkWithRel("profile").description("The ALPS profile for the service")
                        ),
                        responseFields(
                                subsectionWithPath("_links").description("<<resources-index-access_links,Links>> to other resources"))));
    }
}
