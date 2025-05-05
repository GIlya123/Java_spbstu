package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotBlank(message = "UserId is blank")
    private String userId;
    @NotBlank(message = "Title is blank")
    private String title;
    @NotNull(message = "Target Date is null")
    private LocalDateTime targetDate;
}
