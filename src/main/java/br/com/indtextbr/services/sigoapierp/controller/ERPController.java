package br.com.indtextbr.services.sigoapierp.controller;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import br.com.indtextbr.services.sigoapierp.model.LinhaProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.ParadaProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.StatusProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.TurnoDTO;
import br.com.indtextbr.services.sigoapierp.service.interfaces.ERPClient;
import br.com.indtextbr.services.sigoapierp.util.RestPage;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping()
public class ERPController {
	private ERPClient erpClient;

	@Autowired
	public ERPController(ERPClient erpClient) {
		this.erpClient = erpClient;
	}

	@GetMapping(value = "turno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TurnoDTO>> getTurnos() {
		return this.erpClient.getAllTurnos();
	}

	@GetMapping(value = "linha-producao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LinhaProducaoDTO>> getLinhasProducao() {
		return this.erpClient.getAllLinhas();
	}

	@GetMapping(value = "status-producao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StatusProducaoDTO>> getStatusProducao() {
		return this.erpClient.getStatusProducao();
	}

	@GetMapping(value = "parada-producao", produces = { "application/json" })
	public ResponseEntity<String> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return this.erpClient.getParadaProducao(page, size);
	}

	@GetMapping(value = "/parada-producao/{id}", produces = { "application/json" })
	public ResponseEntity<ParadaProducaoDTO> getParadaById(@PathVariable(value = "id") Long id) {
		return this.erpClient.getParadaProducaoById(id);
	}

	@PostMapping(value="/parada-producao",produces = { "application/json" })
	public ResponseEntity<Object> incluirParada(@RequestBody @Valid ParadaProducaoDTO paradaProducao) {
		Object id = this.erpClient.incluirParada(paradaProducao).getBody();
		URI location = URI.create(String.format("/parada-producao/%s", id));
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/parada-producao/{id}", produces = { "application/json" })
	public ResponseEntity<Void> atualizarParada(@PathVariable(value = "id") Long id,
			@RequestBody @Valid ParadaProducaoDTO paradaProducao) {
		this.erpClient.atualizarParada(id, paradaProducao);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/parada-producao/{id}", produces = { "application/json" })
	public ResponseEntity<Void> excluirParada(@PathVariable(value = "id") Long id) {
		this.erpClient.excluirParada(id);
		return ResponseEntity.accepted().build();
	}
	
}
