package uk.gov.dwp.ipvalidator.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParsingIPv4ValidationService implements IPv4ValidationService {

    @Override
    public Boolean isValidIpv4Address(String ipAddress) {
        String[] inputOctets = ipAddress
                .replace('+', ' ') // remove signs
                .replace('-', ' ') // remove signs
                .split("\\.");

        if (inputOctets.length != 4) {
            return false;
        }

        List<Boolean> correctOctets = Arrays.stream(inputOctets)
                .map(s -> {
                    try {
                        if (s.length() > 3) {
                            return false;
                        }
                        int val = Integer.parseInt(s);
                        return val >= 0 && val <= 255;
                    } catch (NumberFormatException ignore) {
                        return false;
                    }
                })
                .filter(result -> result)
                .collect(Collectors.toList());

        return correctOctets.size() == 4;
    }
}
