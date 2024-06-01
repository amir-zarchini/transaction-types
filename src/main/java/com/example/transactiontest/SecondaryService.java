package com.example.transactiontest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SecondaryService {

    @Autowired
    private RecordRepository recordRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewTransaction() {
            Record record = new Record();
            record.setName("Requires New Transaction");
            recordRepository.save(record);
            // Simulate an exception
//            throw new RuntimeException("Exception in requiresNewTransaction");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredTransaction() {
        Record record = new Record();
        record.setName("Required Transaction");
        recordRepository.save(record);
        // Simulate an exception
//        throw new RuntimeException("Exception in requiredTransaction");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedTransaction() {
        Record record = new Record();
        record.setName("Nested Transaction");
        recordRepository.save(record);
        // Simulate an exception
        throw new RuntimeException("Exception in nestedTransaction");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsTransaction() {
        Record record = new Record();
        record.setName("Supports Transaction");
        recordRepository.save(record);
        throw new RuntimeException("Exception in Supports Transaction");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedTransaction() {
        Record record = new Record();
        record.setName("Not Supported Transaction");
        recordRepository.save(record);
        throw new RuntimeException("Exception in Not Supported Transaction");
    }

    @Transactional(propagation = Propagation.NEVER)
    public void neverTransaction() {
        Record record = new Record();
        record.setName("Never Transaction");
        recordRepository.save(record);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryTransaction() {
        Record record = new Record();
        record.setName("Mandatory Transaction");
        recordRepository.save(record);
    }
}

