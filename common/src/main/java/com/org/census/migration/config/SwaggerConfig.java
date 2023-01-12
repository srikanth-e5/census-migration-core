package com.org.census.migration.config;

import com.org.census.migration.constant.Constants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        String[] packagedToMatch = { "com.org.census" };
        return GroupedOpenApi.builder().packagesToScan(packagedToMatch)
                             .pathsToMatch("/**").group(Constants.OpenAPI.GROUP)
                             .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components())
                            .info(new Info().title(Constants.OpenAPI.TITLE)
                                            .version(Constants.OpenAPI.VERSION)
                                            .description(Constants.OpenAPI.DESCRIPTION));
    }

}
