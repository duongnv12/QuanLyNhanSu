package com.company.quanlynhansu.controller;

import com.company.quanlynhansu.dao.TrainingRecordDAO;
import com.company.quanlynhansu.model.TrainingRecord;
import java.util.List;

public class TrainingRecordController {
    private static TrainingRecordController instance;
    private TrainingRecordDAO trainingDAO;

    private TrainingRecordController() {
        trainingDAO = new TrainingRecordDAO();
    }

    public static TrainingRecordController getInstance() {
        if (instance == null) {
            instance = new TrainingRecordController();
        }
        return instance;
    }

    public boolean addTrainingRecord(TrainingRecord record) {
        return trainingDAO.addTrainingRecord(record);
    }

    public boolean updateTrainingRecord(TrainingRecord record) {
        return trainingDAO.updateTrainingRecord(record);
    }

    public List<TrainingRecord> getTrainingRecordsByEmployee(int employeeId) {
        return trainingDAO.getTrainingRecordsByEmployee(employeeId);
    }
    
    public List<TrainingRecord> getAllTrainingRecords() {
        return trainingDAO.getAllTrainingRecords();
    }
}
