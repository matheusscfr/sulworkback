package br.com.sulwork.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {
    private Integer status;
    @JsonFormat(pattern = "dd/MM/yyyy-HH:mm:ss")
    private LocalDateTime time;
    private String message;
}
