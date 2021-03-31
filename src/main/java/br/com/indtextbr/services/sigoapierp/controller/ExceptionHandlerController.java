package br.com.indtextbr.services.sigoapierp.controller;

import java.net.NoRouteToHostException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import br.com.indtextbr.services.sigoapierp.common.ErroDTO;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ExceptionHandlerController {
	@ExceptionHandler(NoRouteToHostException.class)
    public ResponseEntity<List<ErroDTO>> handlerConnectException(NoRouteToHostException ex) {
        log.error("internal server error", ex);
        List<ErroDTO> erros = Collections.singletonList(new ErroDTO("SAP Indisponível"));
        return new ResponseEntity<>(erros, HttpStatus.GATEWAY_TIMEOUT);
    }
	
	@ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<List<ErroDTO>> handlerStatusCodeException(HttpStatusCodeException ex) {
        log.error("internal server error", ex);
        List<ErroDTO> erros = Collections.singletonList(new ErroDTO("Erro no SAP"));
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }
}
