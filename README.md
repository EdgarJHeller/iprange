# iprange
Spring Boot Application with REST controller that returns a filtered list of IP ranges by region from here: [https://ip-ranges.amazonaws.com/ip-ranges.json](https://ip-ranges.amazonaws.com/ip-ranges.json).

## Run from docker container:
```
sudo docker run -p 8080:8080 hedgarj/iprange
```

## Sample request:
Send `localhost:8080/?region=CN` for all IP ranges that correspond to region CN, eg.:

```
52.82.169.16/28
52.82.169.0/28
...
```
For reference:
```
{
      "ip_prefix": "52.82.169.16/28",
      "region": "cn-northwest-1",
      "service": "AMAZON",
      "network_border_group": "cn-northwest-1"
},
{
      "ip_prefix": "52.82.169.0/28",
      "region": "cn-northwest-1",
      "service": "AMAZON",
      "network_border_group": "cn-northwest-1"
},
...
```
Default request param is `ALL`, for all valid regions: `EU, US, AP, CN, SA, AF, CA` 

The data is presented as MIME type text/plain, each value is shown as a single line in the output. The list is not filtered for duplicates.

CI pipeline is set up in github actions.

