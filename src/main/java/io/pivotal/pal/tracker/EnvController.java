package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port;
    private String memoryLimit;
    private String cfInstanceIdx;
    private String cfInstanceAddr;


    public EnvController(
            @Value("${PORT:NOT SET}") String port,
            @Value("${MEMORY_LIMIT:NOT SET}") String memoryLimit,
            @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstanceIdx,
            @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIdx = cfInstanceIdx;
        this.cfInstanceAddr = cfInstanceAddr;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {

        Map<String, String> test = new HashMap<>();

        test.put("PORT", port);
        test.put("MEMORY_LIMIT", memoryLimit);
        test.put("CF_INSTANCE_INDEX", cfInstanceIdx);
        test.put("CF_INSTANCE_ADDR", cfInstanceAddr);

        return test;
    }
}
