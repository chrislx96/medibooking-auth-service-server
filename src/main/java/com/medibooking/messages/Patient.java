package com.medibooking.messages;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Patient implements Serializable {
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private Long accountId;
}
