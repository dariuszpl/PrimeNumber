package pl.pansoft.prime.calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.pansoft.prime.dto.PrimeDto;
import pl.pansoft.prime.result.PrimeSaver;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class PrimeNumBaseAlgorithm implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(PrimeNumBaseAlgorithm.class);
    private PrimeDto primeData;

    public PrimeNumBaseAlgorithm(PrimeDto primeData) {
        this.primeData = primeData;
    }

    @Override
    public void run() {
        primeData.setStartDate(LocalDateTime.now());
        BigInteger firstPrimNum = BigInteger.ONE.add(BigInteger.ONE);
        BigInteger actualRangeNum = primeData.getRangeFrom();
        BigInteger stopRangeNum = primeData.getRangeTo();
        primeData.getPrimeNumbers().clear();
        boolean flag = false;
        int step = 0;
        while (actualRangeNum.compareTo(stopRangeNum) <= 0) {
            BigInteger divNum = firstPrimNum;
            while (divNum.compareTo(actualRangeNum) < 0) {
                if (actualRangeNum.mod(divNum).compareTo(BigInteger.ZERO) == 0) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
                divNum = divNum.add(BigInteger.ONE);
                if (primeData.isCanceledByUser()) {
                    break;
                }
            }
            if (primeData.isCanceledByUser()) {
                break;
            }
            if (flag || actualRangeNum.compareTo(firstPrimNum) == 0) {
                BigInteger finder = actualRangeNum;
                primeData.getPrimeNumbers().add(finder);
            }
            actualRangeNum = actualRangeNum.add(BigInteger.ONE);
            primeData.setStep(step++);
            primeData.setPercent((int) ((double) primeData.getStep() / (double) primeData.getLength() * 100d));
        }
        primeData.setStopDate(LocalDateTime.now());
        logger.info("xml saved in: " + PrimeSaver.saveToFile(primeData));
    }
}
