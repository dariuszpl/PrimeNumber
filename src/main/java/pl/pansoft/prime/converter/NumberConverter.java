package pl.pansoft.prime.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class NumberConverter {

    final static Logger logger = LoggerFactory.getLogger(NumberConverter.class);

    public static BigInteger toNum(String text) {
        BigInteger result = null;
        try {
            result = new BigInteger(text);
        } catch (NumberFormatException e) {
            logger.error(e.toString());
        }
        return result;
    }
}
