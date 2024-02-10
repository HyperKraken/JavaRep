package com.company.demo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;  // todo: default -> false
    private String message;
    /*todo:
     *   0 it is ok
     *  -1 is not found
     *  -2 db error
     *  -3 validation error
     *  -4 eny error
     */

    private int code; // todo : default -> 0

    private T content;

    private List<ErrorDto> errorList;
}
