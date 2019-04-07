package com.keveon.demo;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.hypermedia.LinkDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

@Transactional
@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class SpringDataJpaDemoApplicationTests {
    /**
     * The Paging links.
     */
    protected static final LinkDescriptor[] pagingLinks = new LinkDescriptor[]{
            linkWithRel("first").optional().description("第一页"),
            linkWithRel("prev").optional().description("上一页"),
            linkWithRel("next").optional().description("下一页"),
            linkWithRel("last").optional().description("最后一页")
    };

    /**
     * The Page request parameters.
     */
    protected static final ParameterDescriptor[] pageRequestParameters = new ParameterDescriptor[]{
            parameterWithName("page").description("要查询的页数").optional(),
            parameterWithName("size").description("每页显示条数").optional(),
            parameterWithName("sort").description("排序条件").optional()
    };

    /**
     * The Base path.
     */
    @Value("${spring.data.rest.base-path:}")
    protected String basePath;
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
     * Gets link.
     *
     * @param result the result
     * @param rel    the rel
     * @return the link
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    protected String getLink(MvcResult result, String rel) throws UnsupportedEncodingException {
        return JsonPath.parse(result.getResponse().getContentAsString()).read("_links." + rel + ".href");
    }
}
