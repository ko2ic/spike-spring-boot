package com.github.ko2ic;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.github.ko2ic.core.Template;

@ConfigurationProperties(prefix = "hello-world", ignoreUnknownFields = false)
@Component
public class HelloWorldConfiguration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    public HelloWorldConfiguration() {
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }
}
