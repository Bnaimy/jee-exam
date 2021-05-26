package com.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
@GetMapping("/notauthorized")
public String error() {
	return "notauthorized";
}

}
