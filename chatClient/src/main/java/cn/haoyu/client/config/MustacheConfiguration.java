package cn.haoyu.client.config;

import com.samskivert.mustache.Mustache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.boot.autoconfigure.mustache.web.MustacheViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * Created by sheep on 2017-6-2.
 */

@Configuration
public class MustacheConfiguration {
    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private MustacheViewResolver mustacheViewResolver;

    @PostConstruct
    public void setContextPath() {
        String contextPath = serverProperties.getContextPath();
        if (contextPath == null) {
            contextPath = "";
        }

        mustacheViewResolver.getAttributesMap().put("ctx", contextPath);
    }

    @Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader mustacheTemplateLoader,
                                              Environment environment) {

        MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);

        // default value
        Mustache.Compiler compiler = Mustache.compiler().defaultValue("")
                .withLoader(mustacheTemplateLoader)
                .withCollector(collector);
        return compiler;
    }
}
