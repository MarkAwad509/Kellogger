package edu.csusm.kellogger.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class HelloController {
    @GetMapping("hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from endpoint");
    }
}