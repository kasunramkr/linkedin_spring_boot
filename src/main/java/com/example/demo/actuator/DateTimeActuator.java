package com.example.demo.actuator;

import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Endpoint(id="system-date")
public class DateTimeActuator {

    @ReadOperation
    public SystemTime getSystemDate()
    {
        SystemTime systemTime = new SystemTime();
        systemTime.systemTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return systemTime;
    }

    @Data
    public class SystemTime{
        private String systemTime;
    }
}
