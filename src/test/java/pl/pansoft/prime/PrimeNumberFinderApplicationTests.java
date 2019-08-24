package pl.pansoft.prime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pansoft.prime.calculation.PrimeNumBaseAlgorithm;
import pl.pansoft.prime.dto.PrimeDto;

import java.math.BigInteger;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrimeNumberFinderApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void primeNumBaseAlgorithm() {
		PrimeDto primeData = new PrimeDto();
		primeData.setRangeFrom(new BigInteger("2"));
		primeData.setRangeTo(new BigInteger("100"));
		primeData.setLength(primeData.getRangeTo().subtract(primeData.getRangeFrom()).intValue());
		PrimeNumBaseAlgorithm alg = new PrimeNumBaseAlgorithm(primeData);
		alg.run();
		int size = primeData.getPrimeNumbers().size();
		assertEquals("in range 2 - 100 should be 25 prime numbers", 25, size);

	}
}
