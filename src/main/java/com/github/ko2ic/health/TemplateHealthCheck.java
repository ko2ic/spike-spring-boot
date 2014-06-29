package com.github.ko2ic.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.github.ko2ic.HelloWorldConfiguration;
import com.google.common.base.Optional;

@Component
public class TemplateHealthCheck implements HealthIndicator {

    @Autowired
    private HelloWorldConfiguration configuration;

    @Override
    public Health health() {
        String data = configuration.buildTemplate().render(Optional.of("error"));
        return Health.down().withDetail("message", data).build();
    }

}
