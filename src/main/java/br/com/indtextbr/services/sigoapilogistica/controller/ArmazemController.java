package br.com.indtextbr.services.sigoapilogistica.controller;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.indtextbr.services.sigoapilogistica.common.ErroDTO;
import br.com.indtextbr.services.sigoapilogistica.model.Armazem;
import br.com.indtextbr.services.sigoapilogistica.service.ArmazemService;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("armazens")
@Log4j2
public class ArmazemController {
	private ArmazemService armazemService;
	
	@Autowired
	public ArmazemController(ArmazemService armazemService) {
		this.armazemService = armazemService;
	}
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Armazem>> getArmazens(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "10")int size) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {
		var armazens = this.armazemService.getAllArmazens(size,page);
		return new ResponseEntity<>(armazens, (armazens.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}", produces = { "application/json" })
	public ResponseEntity<Armazem> getArmazemById(@PathVariable(value="id") String id) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {
		var armazem = this.armazemService.getArmazemById(id);
		return new ResponseEntity<>(armazem, (armazem == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping(produces = { "application/json" })
	public ResponseEntity<Object> incluirAmazem(@RequestBody @Valid Armazem armazem) throws JsonProcessingException, InterruptedException, ExecutionException{	
		this.armazemService.incluirArmazem(armazem);
		return ResponseEntity.accepted().build();
	}
	
	@PutMapping(value="/{id}",produces = { "application/json" })
	public ResponseEntity<Void> atualizarAmazem(@PathVariable(value="id") String id, @RequestBody @Valid Armazem armazem) throws JsonProcessingException{
		armazem.setId(id);
		this.armazemService.editarArmazem(armazem);
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping(value="/{id}",produces = { "application/json" })
	public ResponseEntity<Void> inativarArmazem(@PathVariable(value="id") String id) throws JsonProcessingException{
		this.armazemService.inativarArmazem(id);
		return ResponseEntity.accepted().build();
	}
}
