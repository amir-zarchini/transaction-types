package com.example.transactiontest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainService {

    private final SecondaryService secondaryService;
    private final RecordRepository recordRepository;

    @Transactional
    public void executeWithRequiresNew() {
        try {
            Record record = new Record();
            record.setName("Main Transaction - Requires New Transaction");
            recordRepository.save(record);
//            secondaryService.requiresNewTransaction();
            throw new RuntimeException("Exception in executeWithRequiresNew");
        } catch (Exception e) {
            System.out.println("Caught exception from requiresNewTransaction");
            throw e;
        }
        // Further processing or DB operations
    }

    @Transactional
    public void executeWithRequired() {
        try {
            Record record = new Record();
            record.setName("Main Transaction - Required Transaction");
            recordRepository.save(record);
            secondaryService.requiredTransaction();
            throw new RuntimeException("Exception in executeWithRequired");
        } catch (Exception e) {
            System.out.println("Caught exception from requiredTransaction" + e.getMessage());
            throw e;
        }
        // Further processing or DB operations
    }

    @Transactional
    public void executeWithNested() {
        try {
            Record record = new Record();
            record.setName("Main Transaction - Nested Transaction");
            recordRepository.save(record);
            secondaryService.nestedTransaction();
//            throw new RuntimeException("Exception in executeWithNested");
        } catch (Exception e) {
            System.out.println("Caught exception from nestedTransaction: " + e.getMessage());
//            throw e;
        }
    }

    @Transactional
    public void executeWithSupports() {

        try{
            Record record = new Record();
            record.setName("Main Transaction - Supports Transaction");
            recordRepository.save(record);

            secondaryService.supportsTransaction();
//            throw new RuntimeException("Exception in executeWithSupports");
        } catch (Exception e) {
            System.out.println("Caught exception from Supports Transaction: " + e.getMessage());
//            throw e;
        }


        // Further processing or DB operations
    }

    public void executeWithoutTransactionSupports() {
        Record record = new Record();
        record.setName("Non-Transactional Call - Supports Transaction");
        recordRepository.save(record);

        secondaryService.supportsTransaction();
    }

    @Transactional
    public void executeWithNotSupported() {
        Record record = new Record();
        record.setName("Main Transaction - non Support Transaction");
        recordRepository.save(record);

        secondaryService.notSupportedTransaction();

        // Further processing or DB operations
    }

    public void executeWithoutTransactionNonSupported() {
        Record record = new Record();
        record.setName("Non-Transactional Call - non Support Transaction");
        recordRepository.save(record);

        secondaryService.notSupportedTransaction();

        // Further processing or DB operations
    }

    @Transactional
    public void executeWithNever() {
        Record record = new Record();
        record.setName("Main Transaction - Never Transaction");
        recordRepository.save(record);

        try {
            secondaryService.neverTransaction();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void executeWithoutNever() {
        Record record = new Record();
        record.setName("Non-Transactional Call - Never Transaction");
        recordRepository.save(record);

        secondaryService.neverTransaction();
    }
}
