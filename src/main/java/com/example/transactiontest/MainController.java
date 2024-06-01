package com.example.transactiontest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/testRequiresNew")
    public String testRequiresNew() {
        mainService.executeWithRequiresNew();
        return "Executed with REQUIRES_NEW";
    }

    @GetMapping("/testRequired")
    public String testRequired() {
        mainService.executeWithRequired();
        return "Executed with REQUIRED";
    }

    @GetMapping("/testNested")
    public String testNested() {
        mainService.executeWithNested();
        return "Executed with NESTED";
    }

    @GetMapping("/testSupports")
    public String testSupports() {
        mainService.executeWithSupports();
        return "Executed with SUPPORTS";
    }

    @GetMapping("/testSupportsNoTransaction")
    public String testSupportsNoTransaction() {
        mainService.executeWithoutTransactionSupports();
        return "Executed with SUPPORTS without Transaction";
    }

    @GetMapping("/testNotSupportedWithTransaction")
    public String testNotSupportedWithTransaction() {
        mainService.executeWithNotSupported();
        return "Executed with NOT_SUPPORTED in Transaction";
    }

    @GetMapping("/testNotSupportedNoTransaction")
    public String testNotSupportedNoTransaction() {
        mainService.executeWithoutTransactionNonSupported();
        return "Executed with NOT_SUPPORTED without Transaction";
    }

    @GetMapping("/testNeverWithTransaction")
    public String testNeverWithTransaction() {
        mainService.executeWithNever();
        return "Executed with NEVER in Transaction";
    }

    @GetMapping("/testNeverNoTransaction")
    public String testNeverNoTransaction() {
        mainService.executeWithoutNever();
        return "Executed with NEVER without Transaction";
    }

    @GetMapping("/testMandatoryWithTransaction")
    public String testMandatoryWithTransaction() {
        mainService.executeWithMandatoryTransaction();
        return "Executed with MANDATORY in Transaction";
    }

    @GetMapping("/testMandatoryNoTransaction")
    public String testMandatoryNoTransaction() {
        mainService.executeWithoutMandatoryTransaction();
        return "Executed with MANDATORY without Transaction";
    }
}

