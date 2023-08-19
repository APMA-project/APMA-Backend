package APMA.APMAproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class testController {

    /**
     * aws 타겟그룹위한 test Controller
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Healthy";
    }
}
