package pl.pansoft.prime.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pansoft.prime.calculation.PrimeNumBaseAlgorithm;
import pl.pansoft.prime.dto.PrimeDto;
import pl.pansoft.prime.converter.NumberConverter;
import pl.pansoft.prime.validator.PrimeValidator;
import pl.pansoft.prime.validator.Validator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/")
@Scope("session")
public class PrimeController {

    final static Logger logger = LoggerFactory.getLogger(PrimeController.class);

    private PrimeDto primeData;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    public PrimeController(PrimeDto primeData) {
        this.primeData = primeData;
    }

    @RequestMapping(value="", method= RequestMethod.GET)
    public String primeNumbers(Model model) {
        model.addAttribute("primeData", primeData);
        return "primeForm";
    }

    @RequestMapping(value="/find", method=RequestMethod.POST)
    public String findByDiv(
            @RequestParam("rangeFrom") String rangeFrom,
            @RequestParam ("rangeTo") String rangeTo,
            Model model) {
        String view = "primeForm";
        List<Validator> validateResult = PrimeValidator.validateRange(rangeFrom, rangeTo);
        if (validateResult.isEmpty()) {
            primeData.setRangeFrom(NumberConverter.toNum(rangeFrom));
            primeData.setRangeTo(NumberConverter.toNum(rangeTo));
            primeData.setLength(primeData.getRangeTo().subtract(primeData.getRangeFrom()).intValue());
            primeData.setStep(0);
            primeData.setCanceledByUser(false);
            executor.submit(new PrimeNumBaseAlgorithm(primeData));
            view = "redirect:/progress";
        }
        model.addAttribute("rangeFrom", rangeFrom);
        model.addAttribute("rangeTo", rangeTo);
        model.addAttribute("validateResult", validateResult);
        return view;
    }

    @RequestMapping(value="/cancel", method=RequestMethod.POST)
    public String cancel(Model model) {
        primeData.setCanceledByUser(true);
        model.addAttribute("primeData", primeData);
        return "redirect:/progress";
    }

    @RequestMapping(value="/progress", method=RequestMethod.GET)
    public String progress(Model model) {
        model.addAttribute("primeData", primeData);
        return "primeNumbers";
    }

}
