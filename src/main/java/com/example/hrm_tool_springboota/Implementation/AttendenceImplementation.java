package com.example.hrm_tool_springboota.Implementation;

import com.example.hrm_tool_springboota.Modal.Attendance;
import com.example.hrm_tool_springboota.Repository.AttendenceRepository;
import com.example.hrm_tool_springboota.Service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendenceImplementation implements AttendenceService {
    private AttendenceRepository attentionRepository;
@Autowired
    public AttendenceImplementation(AttendenceRepository attentionRepository) {
        this.attentionRepository = attentionRepository;
    }

    @Override
    public void saveCheckin(Attendance attendance) {
    if (attendance!=null) {
        attentionRepository.save(attendance);

    }
    }
}
