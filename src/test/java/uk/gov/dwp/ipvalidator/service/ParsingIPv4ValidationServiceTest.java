package uk.gov.dwp.ipvalidator.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParsingIPv4ValidationServiceTest {

    private ParsingIPv4ValidationService underTest;

    @Before
    public void setUp() {
        underTest = new ParsingIPv4ValidationService();
    }

    @Test
    public void validLeading() {
        String subject = "123.012.001.000";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(true, result);
    }

    @Test
    public void validNoLeading() {
        String subject = "123.012.001.1";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(true, result);
    }

    @Test
    public void havingSignFails() {
        String subject = "+34.012.001.1";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void notAnOctetFails() {
        String subject = "00000034.012.001.1";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void emptyString() {
        String subject = "";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void moreThan4Octets() {
        String subject = "123.012.001.3.4";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void lessThan4Octets() {
        String subject = "123.012.001";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void lessThan4OctetsTrailingDot() {
        String subject = "123.012.001.";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void nonDigit() {
        String subject = "123.012.001.bad";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void twoEmptyOctets() {
        String subject = "123...1";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void leadingDot() {
        String subject = ".123.012.001.001";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }

    @Test
    public void outOfRange() {
        String subject = "123.012.001.256";
        Boolean result = underTest.isValidIpv4Address(subject);
        assertEquals(false, result);
    }
}