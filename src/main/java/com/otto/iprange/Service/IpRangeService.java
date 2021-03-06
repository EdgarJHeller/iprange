package com.otto.iprange.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

@Service
public class IpRangeService {
    public String getIpRangesForValidRegion(String region) throws IOException {
        //Valid regions are EU, US, AP, CN, SA, AF, CA.
        ArrayList<String> validRegions = new ArrayList<>(Arrays.asList("EU", "US", "AP", "CN", "SA", "AF", "CA"));

        //Return the ip-ranges for one region.
        if (validRegions.contains(region)) {
            ArrayList<String> regionArr = new ArrayList<>(Collections.singletonList(region));
            return getIpRangesFromJson(regionArr);
        }
        //Return the ip-ranges for all valid regions.
        if (region.equals("ALL")) {
            return getIpRangesFromJson(validRegions);
        }
        return "no valid region";

    }

    private String getIpRangesFromJson(ArrayList<String> validRegions) throws IOException {
        String url = "https://ip-ranges.amazonaws.com/ip-ranges.json";

        //Read JSON
        JSONObject ipRangeJson = readJsonFromUrl(url);
        JSONArray prefixesArr = ipRangeJson.getJSONArray("prefixes");

        //Build IP range list as multiline String
        StringBuilder ipRangeList = new StringBuilder();
        for (int i=0; i<prefixesArr.length();i++) {
            if (validRegions.contains(prefixesArr.getJSONObject(i).getString("region").substring(0,2).toUpperCase(Locale.ROOT))) {
                String ip_prefix = prefixesArr.getJSONObject(i).getString("ip_prefix");
                ipRangeList.append(ip_prefix);
                ipRangeList.append("\n");
            }
        }

        return String.valueOf(ipRangeList);

    }

    private JSONObject readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
