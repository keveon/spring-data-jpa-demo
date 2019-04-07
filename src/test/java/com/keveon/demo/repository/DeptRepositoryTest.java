package com.keveon.demo.repository;

import com.keveon.demo.SpringDataJpaDemoApplicationTests;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Dept repository test.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @see DeptRepository
 * @since 1.0.0
 */
class DeptRepositoryTest extends SpringDataJpaDemoApplicationTests {
    private static final String RESOURCE_NAME = "depts";
    private static final Integer id = 1;

    private static final LinksSnippet selfLinksSnippet = links(
            linkWithRel("self").ignored(),
            linkWithRel("dept").description("<<resources-" + RESOURCE_NAME + "-retrieve, 部门>> 资源"),
            linkWithRel("employees").description("<<resources-employees, 员工资源（Employees）>> 的列表")
    );

    private static final ResponseFieldsSnippet responseFields = responseFields(
            fieldWithPath("id").description("部门编号"),
            fieldWithPath("name").description("部门名称"),
            fieldWithPath("new").description("是否新建")
    ).and(subsectionWithPath("_links").ignored());

    /**
     * Create.
     *
     * @throws Exception the exception
     * @see DeptRepository#save(Object)
     */
    @Test
    void create() throws Exception {

        JSONObject params = new JSONObject();
        params.put("name", "This is a test dept.");

        this.mockMvc.perform(
                post(basePath + "/" + RESOURCE_NAME)
                        .contentType(MediaTypes.HAL_JSON)
                        .content(params.toJSONString()))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", notNullValue()))
                .andDo(document(RESOURCE_NAME + "-create",
                        selfLinksSnippet,
                        requestFields(
                                fieldWithPath("name").description("部门名称")
                        ),
                        responseFields
                ));
    }

    /**
     * Delete by id.
     *
     * @throws Exception the exception
     * @see DeptRepository#deleteById(Object)
     */
    @Test
    void deleteById() throws Exception {
        this.mockMvc.perform(
                delete(basePath + "/" + RESOURCE_NAME + "/{id}", id).contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isNoContent())
                .andDo(document(RESOURCE_NAME + "-delete",
                        pathParameters(
                                parameterWithName("id").description("部门编号")
                        )
                ));
    }

    /**
     * Update by id.
     *
     * @throws Exception the exception
     * @see DeptRepository#save(Object)
     */
    @Test
    void updateById() throws Exception {
        JSONObject param = new JSONObject();
        param.put("name", "This is renamed dept.");

        this.mockMvc.perform(
                patch(basePath + "/" + RESOURCE_NAME + "/{id}", id)
                        .contentType(MediaTypes.HAL_JSON)
                        .content(param.toJSONString()))
                .andExpect(status().isOk())
                .andDo(document(RESOURCE_NAME + "-update",
                        selfLinksSnippet,
                        pathParameters(
                                parameterWithName("id").description("部门编号")
                        ),
                        requestFields(
                                fieldWithPath("name").description("新的部门名称")
                        ),
                        responseFields
                ));
    }

    /**
     * Retrieve.
     *
     * @throws Exception the exception
     * @see DeptRepository#findById(Object)
     * @see DeptRepository#getOne(Object)
     */
    @Test
    void retrieve() throws Exception {
        this.mockMvc.perform(get(basePath + "/" + RESOURCE_NAME + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(id)))
                .andExpect(jsonPath("name", notNullValue()))
                .andDo(document(RESOURCE_NAME + "-retrieve",
                        selfLinksSnippet,
                        pathParameters(
                                parameterWithName("id").description("部门编号")
                        ),
                        responseFields
                ));
    }

    /**
     * @throws Exception the exception
     * @see DeptRepository#findAll(Pageable)
     */
    @Test
    void list() throws Exception {
        this.mockMvc.perform(
                get(basePath + "/" + RESOURCE_NAME)
                        .param("page", "1")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andDo(document(RESOURCE_NAME + "-list",
                        links(
                                linkWithRel("self").ignored(),
                                linkWithRel("profile").description("The ALPS profile for this resource")
                        ).and(pagingLinks),
                        requestParameters(

                        ).and(pageRequestParameters),
                        responseFields(
                                subsectionWithPath("_embedded." + RESOURCE_NAME)
                                        .description("部门资源的列表, 详情查看《<<resources-" + RESOURCE_NAME + "-retrieve, 通过部门编号, 检索单个部门的完整信息>>》"),
                                subsectionWithPath("_links").description("<<resources-" + RESOURCE_NAME + "-list_links, Links>> to other resources"),
                                subsectionWithPath("page").description("<<resources-page, Page>> to other resources"))
                        )
                );
    }
}