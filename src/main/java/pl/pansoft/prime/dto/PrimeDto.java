package pl.pansoft.prime.dto;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Data transfer object.
 */
@Component
@Scope("session")
public class PrimeDto {
    private LocalDateTime startDate;
    private LocalDateTime stopDate;
    private BigInteger rangeFrom;
    private BigInteger rangeTo;
    private Integer length;
    private Integer step;
    private Integer percent;
    private boolean canceledByUser;
    private String xmlPath;
    private Queue<BigInteger> primeNumbers = new ConcurrentLinkedQueue<BigInteger>();

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDateTime stopDate) {
        this.stopDate = stopDate;
    }

    public BigInteger getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(BigInteger rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public BigInteger getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(BigInteger rangeTo) {
        this.rangeTo = rangeTo;
    }

    public boolean isCanceledByUser() {
        return canceledByUser;
    }

    public void setCanceledByUser(boolean canceledByUser) {
        this.canceledByUser = canceledByUser;
    }

    public Queue<BigInteger> getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(Queue<BigInteger> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    public Integer getLength() { return length; }

    public void setLength(Integer length) { this.length = length; }

    public Integer getStep() { return step; }

    public void setStep(Integer step) { this.step = step; }

    @Override
    public String toString() {
        return "PrimeDto{" +
                "startDate=" + startDate +
                ", stopDate=" + stopDate +
                ", rangeFrom=" + rangeFrom +
                ", rangeTo=" + rangeTo +
                ", canceledByUser=" + canceledByUser +
                ", primeNumbers=" + primeNumbers +
                '}';
    }

    public String toXml() {
        XStream xstream = new XStream();

        String result = xstream.toXML(this);
        return result;
    }


    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
