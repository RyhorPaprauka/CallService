package by.itacademy.callservice.service;

import by.itacademy.callservice.dto.CallDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static by.itacademy.callservice.utill.Constants.DATE_FORMAT;
import static by.itacademy.callservice.utill.Constants.DEFAULT_REGION;
import static by.itacademy.callservice.utill.Constants.LOCAL_LENGTH;
import static by.itacademy.callservice.utill.Constants.NON_DIGIT;
import static by.itacademy.callservice.utill.Constants.SPACE;
import static by.itacademy.callservice.utill.Constants.TXT;
import static by.itacademy.callservice.utill.Constants.UNDERSCORE;
import static by.itacademy.callservice.utill.Constants.VOID;
import static by.itacademy.callservice.utill.Constants.ZERO_ZERO;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CallService {

    public void saveCall(CallDto call) throws IOException {
        File file = new File(getFileName(call));

        try {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.append(call.getTime().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
                bufferedWriter.append(SPACE);
                bufferedWriter.append(formatPhone(call.getNumber()));
                bufferedWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private String getFileName(CallDto call) {
        StringBuilder stringBuilder = new StringBuilder(call.getSurname().toUpperCase());

        if (call.getName() != null) {
            stringBuilder.append(UNDERSCORE);
            stringBuilder.append(call.getName().toUpperCase());
        }
        stringBuilder.append(TXT);
        return stringBuilder.toString();
    }

    private String formatPhone(String phone) {
        phone = phone.replaceAll(NON_DIGIT, VOID);
        if (!phone.startsWith(ZERO_ZERO)) {
            phone = ZERO_ZERO + phone;
        }

        String local = phone.substring(phone.length() - LOCAL_LENGTH);
        String region = phone.substring(ZERO_ZERO.length(), phone.length() - LOCAL_LENGTH);

        if (region.isEmpty()) {
            region = DEFAULT_REGION;
        }

        return ZERO_ZERO + region + local;
    }
}
