package com.otto.iprange.Service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IpRangeServiceTest {

    IpRangeService ipRangeService = new IpRangeService();

    @Test
    void noValidRegionResponseOnRegionHH() throws IOException {
        String response = ipRangeService.getIpRangesForValidRegion("HH");
        assertEquals("no valid region", response);
    }

    @Test
    void validRegionResponseOnRegionCN() throws IOException {
        String response = ipRangeService.getIpRangesForValidRegion("CN");
        assertNotEquals("no valid region", response);
    }

}