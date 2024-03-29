package com.kidari.api.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Tag(name = "Home", description = "Home API Document")
class HomeController {

    @GetMapping("/hello")
    @Operation(summary = "인사하기", description = "Hello, Starnager.")
    String hello() {
        return "Hello, stranger.";
    }
}
