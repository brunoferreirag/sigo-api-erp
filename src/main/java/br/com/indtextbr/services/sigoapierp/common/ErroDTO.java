package br.com.indtextbr.services.sigoapierp.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroDTO implements Serializable {
    private String field;
    private String value;
    private String message;

    public ErroDTO(String message) {
        this.message = message;
    }
}
