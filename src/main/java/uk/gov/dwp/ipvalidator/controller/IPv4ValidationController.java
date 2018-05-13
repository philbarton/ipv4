package uk.gov.dwp.ipvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.dwp.ipvalidator.service.IPv4ValidationService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class IPv4ValidationController {

    private final IPv4ValidationService ipv4ValidationService;

    @Autowired
    public IPv4ValidationController(IPv4ValidationService ipv4ValidationService) {
        this.ipv4ValidationService = ipv4ValidationService;
    }

    @RequestMapping(value = "/validator",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Boolean>> ipArrayValidator(@RequestBody List<String> ipAddresses) {
        List<Boolean> validated = ipAddresses
                .stream()
                .map(ipAddress -> ipv4ValidationService.isValidIpv4Address(ipAddress))
                .collect(Collectors.toList());
        return new ResponseEntity<>(validated, HttpStatus.OK);
    }
}
