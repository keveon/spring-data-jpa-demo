package com.keveon.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

/**
 * The type Employee repository test.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @see EmployeeRepository
 * @since 1.0.0
 */
@Transactional
@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class EmployeeRepositoryTest {

    /**
     * The Context.
     */
    @Autowired
    protected WebApplicationContext context;
    /**
     * The Mock mvc.
     */
    protected MockMvc mockMvc;

    /**
     * Sets up.
     *
     * @param restDocumentation the rest documentation
     */
    @BeforeEach
    protected void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                )
                .build();
    }

    /**
     * Retrieve.
     *
     * @throws Exception the exception
     * @see EmployeeRepository
     * @see EmployeeRepository#getOne(Object)
     * @see EmployeeRepository#findById(Object)
     */
    @Test
    void retrieve() throws Exception {
    }
}
