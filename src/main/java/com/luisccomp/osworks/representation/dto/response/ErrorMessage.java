package com.luisccomp.osworks.representation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private int status;
    private String message;

    private List<Field> fields;

    @Getter
    @Setter
    @Builder
    public static class Field {

        private String fieldName;
        private String errorMessage;

    }

}
