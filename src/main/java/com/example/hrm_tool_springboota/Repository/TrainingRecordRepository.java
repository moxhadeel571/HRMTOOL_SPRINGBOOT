package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.TrainingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRecordRepository extends JpaRepository<TrainingRecord, Long> {

    @Query(value = "SELECT SUM(CASE WHEN t.Training_Type = 'external' THEN 1 ELSE 0 END) AS externalCount FROM training_and_development_data t")
    List<Integer> getExternalTrainingCount();

    @Query(value = "SELECT SUM(CASE WHEN t.Training_Type = 'internal' THEN 1 ELSE 0 END) AS internalCount FROM training_and_development_data t")
    List<Integer> getInternalTrainingCount();
}
