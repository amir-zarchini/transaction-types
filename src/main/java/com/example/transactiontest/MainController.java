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

    // ----------------------------------------- REQUIRED Transaction ----------------------------------------------
    @GetMapping("/testRequired")
    public String testRequired() {
        mainService.executeWithRequired();
        return "Executed with REQUIRED";
    }
    // -------------------------------------------------------------------------------------------------------------



    // --------------------------------------- REQUIRES_NEW Transaction --------------------------------------------
    /*
    REQUIRES_NEW Propagation:
       . REQUIRES_NEW transactions suspend the current transaction and start a completely new and independent transaction.
       . If a REQUIRES_NEW transaction fails, only the new transaction is rolled back.
       . The suspended outer transaction resumes and can continue and be committed independently of the new transaction.
     */
    @GetMapping("/testRequiresNew")
    public String testRequiresNew() {
        mainService.executeWithRequiresNew();
        return "Executed with REQUIRES_NEW";
    }
    // -------------------------------------------------------------------------------------------------------------



    // ----------------------------------------- NESTED Transaction ------------------------------------------------
    /*
    NESTED Propagation:
       . NESTED transactions create a savepoint within the current transaction.
       . If a NESTED transaction fails, only the nested transaction is rolled back to the savepoint,
            but the outer transaction can still continue and be committed.
       . Think of NESTED transactions as sub-transactions within the main transaction.
     */
    @GetMapping("/testNested")
    public String testNested() {
        mainService.executeWithNested();
        return "Executed with NESTED";
    }
    // -------------------------------------------------------------------------------------------------------------



    // --------------------------------------- SUPPORTS Transaction ------------------------------------------------
    /*
    1) With Existing Transaction:
       . When you call mainService.executeWithSupports(), it starts a transaction because of the @Transactional annotation.
       . The secondaryService.supportsTransaction() method joins this existing transaction.
       . Both "Main Transaction" and "Supports Transaction" records are saved as part of the same transaction.
     */
    @GetMapping("/testSupports")
    public String testSupports() {
        mainService.executeWithSupports();
        return "Executed with SUPPORTS";
    }

    /*
    2) Without Existing Transaction:
       . When you call mainService.executeWithoutTransactionSupports(),
            it does not start a transaction because there is no @Transactional annotation.
       . The secondaryService.supportsTransaction() method runs non-transactionally.
       . Both "Non-Transactional Call" and "Supports Transaction" records are saved independently
     */
    @GetMapping("/testSupportsNoTransaction")
    public String testSupportsNoTransaction() {
        mainService.executeWithoutTransactionSupports();
        return "Executed with SUPPORTS without Transaction";
    }
    // ---------------------------------------------------------------------------------------------------------------



    // --------------------------------------- NOT_SUPPORTED Transaction ---------------------------------------------
    /*
    1) With Existing Transaction:
       . When you call mainService.executeWithNotSupported(), it starts a transaction because of the @Transactional annotation.
       . The secondaryService.notSupportedTransaction() method suspends the current transaction and runs non-transactionally.
       . After notSupportedTransaction completes, the main transaction resumes.
       . Both "Main Transaction" and "Not Supported Transaction" records are saved independently.
     */
    @GetMapping("/testNotSupportedWithTransaction")
    public String testNotSupportedWithTransaction() {
        mainService.executeWithNotSupported();
        return "Executed with NOT_SUPPORTED in Transaction";
    }

    /*
    2) Without Existing Transaction:
       . When you call mainService.executeWithoutTransactionNonSupported(),
            it does not start a transaction because there is no @Transactional annotation.
       . The secondaryService.notSupportedTransaction() method runs non-transactionally as there is no transaction to suspend.
       . Both "Non-Transactional Call" and "Not Supported Transaction" records are saved independently
     */
    @GetMapping("/testNotSupportedNoTransaction")
    public String testNotSupportedNoTransaction() {
        mainService.executeWithoutTransactionNonSupported();
        return "Executed with NOT_SUPPORTED without Transaction";
    }
    // -----------------------------------------------------------------------------------------------------------



    // ------------------------------------------ NEVER Transaction ----------------------------------------------
    /*
    1) With Existing Transaction:
       . When you call mainService.executeWithNever(), it starts a transaction because of the @Transactional annotation.
       . The secondaryService.neverTransaction() method throws an exception because it must not be executed within a transaction.
       . The exception is caught, and the message is printed.
       . Only "Main Transaction" is saved because neverTransaction did not execute successfully.
     */
    @GetMapping("/testNeverWithTransaction")
    public String testNeverWithTransaction() {
        mainService.executeWithNever();
        return "Executed with NEVER in Transaction";
    }

    /*
    2)Without Existing Transaction:
       . When you call mainService.executeWithoutNever(), it does not start a transaction because there is no @Transactional annotation.
       . The secondaryService.neverTransaction() method runs normally as there is no transaction.
       . Both "Non-Transactional Call" and "Never Transaction" records are saved.
     */
    @GetMapping("/testNeverNoTransaction")
    public String testNeverNoTransaction() {
        mainService.executeWithoutNever();
        return "Executed with NEVER without Transaction";
    }
    // --------------------------------------------------------------------------------------------------------------



    //------------------------------------------ MANDATORY Transaction ----------------------------------------------
    /*
    1) With Existing Transaction:
        . When you call mainService.executeWithMandatoryTransaction(),
            it starts a transaction because of the @Transactional annotation.
        . The secondaryService.mandatoryTransaction() method joins this existing transaction.
        . Both "Main Transaction" and "Mandatory Transaction" records are saved as part of the same transaction.
     */
    @GetMapping("/testMandatoryWithTransaction")
    public String testMandatoryWithTransaction() {
        mainService.executeWithMandatoryTransaction();
        return "Executed with MANDATORY in Transaction";
    }

    /*
    2) Without Existing Transaction:
        . When you call mainService.executeWithoutMandatoryTransaction(),
            it does not start a transaction because there is no @Transactional annotation.
        . The secondaryService.mandatoryTransaction() method throws an exception because there is no existing transaction.
        . The exception is caught, and the message is printed.
        . Only "Non-Transactional Call" is saved because mandatoryTransaction did not execute successfully.
     */
    @GetMapping("/testMandatoryNoTransaction")
    public String testMandatoryNoTransaction() {
        mainService.executeWithoutMandatoryTransaction();
        return "Executed with MANDATORY without Transaction";
    }
    //---------------------------------------------------------------------------------------------------------------
}

