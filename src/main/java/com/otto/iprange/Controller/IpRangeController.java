package com.otto.iprange.Controller;

import com.otto.iprange.Service.IpRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IpRangeController {

    @Autowired
    private IpRangeService ipRangeService;
    @GetMapping("/")
    public String iprange(@RequestParam(value = "region", defaultValue = "ALL") String region) throws IOException {
        return ipRangeService.getIpRangesForValidRegion(region);
    }
}
