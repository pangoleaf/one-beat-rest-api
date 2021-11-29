package com.pangoleaf.obra.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class BaseController {
    protected String mapping = this.getClass().getAnnotation(RequestMapping.class).path()[0];
}
