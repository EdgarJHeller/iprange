package com.otto.iprange.Controller;

import com.otto.iprange.Service.IpRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;

@RestController
public class IpRangeController {

    @Autowired
    private IpRangeService ipRangeService;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String iprange(@RequestParam(value = "region", defaultValue = "ALL") String region) throws IOException {
        return ipRangeService.getIpRangesForValidRegion(region);
    }

}
