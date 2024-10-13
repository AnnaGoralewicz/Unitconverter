package org.unitconverter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Configuration
@EnableWebMvc
public class HTTPServerConfig implements WebMvcConfigurer {

    @Value( "${html.root:htmlroot}" )
    private String htmlroot;
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            var file=new File(htmlroot);
            log.info("static html root is file:"+ file.getAbsolutePath());
            registry.addResourceHandler("/static/**").addResourceLocations("file:"+file.getAbsolutePath()+"/");
        }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");

    }
}

