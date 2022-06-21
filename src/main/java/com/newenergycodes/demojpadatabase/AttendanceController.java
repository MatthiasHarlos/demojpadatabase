package com.newenergycodes.demojpadatabase;

import com.newenergycodes.demojpadatabase.domain.Attendance;
import com.newenergycodes.demojpadatabase.model.CreateAttendance;
import com.newenergycodes.demojpadatabase.model.UpdateAttendance;
import com.newenergycodes.demojpadatabase.repository.AttendanceRepository;
import com.newenergycodes.demojpadatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "attendance", method = {RequestMethod.POST, RequestMethod.GET})
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Attendance> getAttendances(){
        return attendanceRepository.findAll();
    }
    @GetMapping("/{id}")
    public Attendance getAttendance(@PathVariable Long id){
        return attendanceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE}, consumes = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createAttendance(@RequestBody CreateAttendance createAttendance) throws URISyntaxException {
        Attendance newAttendance = new Attendance();
        if (userRepository.findById(createAttendance.getUserId()).isEmpty()) {
            return new ResponseEntity<>(
                    "User with ID could not be found",
                    HttpStatus.NOT_FOUND);
        }
        newAttendance.setUser(userRepository.getById(createAttendance.getUserId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         newAttendance.setDate(LocalDateTime.parse(createAttendance.getDate(), formatter));
        Attendance savedAttendance = attendanceRepository.save(newAttendance);
        return ResponseEntity.created(new URI("attendace/" + savedAttendance.getId())).body(savedAttendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAttendance(@PathVariable Long id, @RequestBody UpdateAttendance updateAttendance) {
        Attendance currentAttendance = attendanceRepository.findById(id).orElseThrow(RuntimeException::new);

        if (userRepository.findById(updateAttendance.getUserId()).isEmpty()) {
            return new ResponseEntity<>(
                    "User with ID could not be found",
                    HttpStatus.NOT_FOUND);
        }
        currentAttendance.setUser(userRepository.getById(updateAttendance.getUserId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentAttendance.setDate(LocalDateTime.parse(updateAttendance.getDate(), formatter));
        currentAttendance = attendanceRepository.save(currentAttendance);
        return ResponseEntity.ok(currentAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAttendance(@PathVariable Long id) {
        attendanceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
