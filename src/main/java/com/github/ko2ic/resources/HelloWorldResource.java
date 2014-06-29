package com.github.ko2ic.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ko2ic.HelloWorldConfiguration;
import com.github.ko2ic.core.Saying;
import com.github.ko2ic.core.Template;
import com.google.common.base.Optional;

@RestController
@RequestMapping(value = "/hello-world")
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    @Autowired
    private HelloWorldConfiguration configuration;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public Saying sayHello(@RequestParam(value = "name", required = false) String name, HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=86400");
        Template template = configuration.buildTemplate();
        return new Saying(counter.incrementAndGet(), template.render(Optional.fromNullable(name)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public void receiveHello(@RequestBody @Valid Saying saying) {
        LOGGER.info("Received a saying: {}", saying);
    }
}
