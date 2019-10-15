package by.itacademy.callservice.controller;

import by.itacademy.callservice.dto.CallDto;
import by.itacademy.callservice.service.CallService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CallController {

    private final CallService callService;

    @PostMapping(value = "/call")
    public ResponseEntity saveCall(@RequestBody @Valid CallDto callDto) {
        callDto.setTime(LocalDateTime.now());
        try {
            callService.saveCall(callDto);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
