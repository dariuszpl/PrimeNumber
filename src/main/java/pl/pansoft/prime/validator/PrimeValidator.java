package pl.pansoft.prime.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.pansoft.prime.converter.NumberConverter;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PrimeValidator  {

    final static Logger logger = LoggerFactory.getLogger(PrimeValidator.class);

    //TODO move the constants to properties file EN/PL
    private static final BigInteger MIN_RANGE_VAL = BigInteger.ONE.add(BigInteger.ONE);
    private static final String RANGE_FROM_NAME = "rangeFrom";
    private static final String RANGE_TO_NAME = "rangeTo";
    private static final String ERROR_MESSAGE_RANGE_FROM = RANGE_FROM_NAME + " has an invalid value";
    private static final String ERROR_MESSAGE_RANGE_FROM_MIN = RANGE_FROM_NAME + " should be greater or equal to " + MIN_RANGE_VAL.toString();
    private static final String ERROR_MESSAGE_RANGE_TO = RANGE_TO_NAME + " has an invalid value";
    private static final String ERROR_MESSAGE_RANGE = RANGE_TO_NAME + " should be greater than " + RANGE_FROM_NAME;


    public static List<Validator> validateRange(String rangeFrom, String rangeTo) {
        List<Validator> result = new LinkedList<Validator>();
        BigInteger from = NumberConverter.toNum(rangeFrom);
        BigInteger to = NumberConverter.toNum(rangeTo);

        if (from == null) {
            result.add(new Validator(RANGE_FROM_NAME, ERROR_MESSAGE_RANGE_FROM));
        } else if (from.compareTo(MIN_RANGE_VAL) < 0) {
            result.add(new Validator(RANGE_FROM_NAME, ERROR_MESSAGE_RANGE_FROM_MIN));
        }
        if (to == null) {
            result.add(new Validator(RANGE_FROM_NAME, ERROR_MESSAGE_RANGE_TO));
        }
        if (from != null && to != null && from.compareTo(to) >= 0) {
            result.add(new Validator(RANGE_FROM_NAME, ERROR_MESSAGE_RANGE));
            result.add(new Validator(RANGE_TO_NAME, ERROR_MESSAGE_RANGE));
        }
        return result;
    }

}
