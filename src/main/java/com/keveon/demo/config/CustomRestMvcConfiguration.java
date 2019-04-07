package com.keveon.demo.config;

import com.keveon.demo.domain.Dept;
import com.keveon.demo.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * The type Custom rest mvc configuration.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 5.0.0
 */
@Configuration
public class CustomRestMvcConfiguration {
    /**
     * Repository rest configurer repository rest configurer.
     *
     * @return the repository rest configurer
     */
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration) {
                configuration.exposeIdsFor(Dept.class, Employee.class);
            }
        };
    }
}
