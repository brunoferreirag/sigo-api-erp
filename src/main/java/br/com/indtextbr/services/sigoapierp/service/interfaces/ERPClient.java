package br.com.indtextbr.services.sigoapierp.service.interfaces;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.indtextbr.services.sigoapierp.model.LinhaProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.ParadaProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.StatusProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.TurnoDTO;


@FeignClient(name="erpservice")
public interface ERPClient {
	@GetMapping(value = "/linha-producao",produces = { "application/json" })
	ResponseEntity<List<LinhaProducaoDTO>> getAllLinhas();
	
	@GetMapping(value = "/turno",produces = { "application/json" })
	ResponseEntity<List<TurnoDTO>> getAllTurnos();
	
	@GetMapping(value = "/status-producao",produces = { "application/json" })
	ResponseEntity<List<StatusProducaoDTO>> getStatusProducao();

	@GetMapping(value="/parada-producao",produces = { "application/json" })
	ResponseEntity<String> getParadaProducao(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "10")int size); 
	
	@GetMapping(value="/parada-producao/{id}", produces = { "application/json" })
	ResponseEntity<ParadaProducaoDTO> getParadaProducaoById(@PathVariable(value="id") Long id);
	
	@PostMapping(value="/parada-producao",produces = { "application/json" })
	ResponseEntity<Object> incluirParada(ParadaProducaoDTO paradaProducao);
	
	@PutMapping(value="/parada-producao/{id}",produces = { "application/json" })
	ResponseEntity<Void> atualizarParada(@PathVariable(value="id") Long id, @RequestBody ParadaProducaoDTO paradaProducao);
	
	@DeleteMapping(value="/parada-producao/{id}",produces = { "application/json" })
	public ResponseEntity<Void> excluirParada(@PathVariable(value="id") Long id);
	
}
