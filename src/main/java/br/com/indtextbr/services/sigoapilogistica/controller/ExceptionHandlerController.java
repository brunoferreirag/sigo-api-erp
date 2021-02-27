package br.com.indtextbr.services.sigoapilogistica.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.indtextbr.services.sigoapilogistica.common.ErroDTO;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ExceptionHandlerController {
	@ExceptionHandler(KafkaReplyTimeoutException.class)
    public ResponseEntity<List<ErroDTO>> handlerKafkaException(KafkaReplyTimeoutException ex) {
        log.error("internal server error", ex);
        List<ErroDTO> erros = Collections.singletonList(new ErroDTO("Sistema de logistica indisponível"));
        return new ResponseEntity<>(erros, HttpStatus.GATEWAY_TIMEOUT);
    }
}
