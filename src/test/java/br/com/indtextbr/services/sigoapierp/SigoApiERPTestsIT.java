package br.com.indtextbr.services.sigoapierp;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.indtextbr.services.sigoapierp.model.LinhaProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.ParadaProducaoDTO;
import br.com.indtextbr.services.sigoapierp.model.TurnoDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SigoApiERPTestsIT {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void deveRetornarTurno() {
		String uri = "/erp/turno";
		ResponseEntity response = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		System.out.println(response.getBody());
	}

	@Test
	public void deveRetornarLinhaProducao() {
		String uri = "/erp/linha-producao";
		ResponseEntity response = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		System.out.println(response.getBody());
	}

	@Test
	public void deveRetornarStatusProducao() {
		String uri = "/erp/status-producao";
		ResponseEntity response = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		System.out.println(response.getBody());
	}

	@Test
	public void deveRetornarParadaProducao() {
		String uri = "/erp/parada-producao?page=0&size=10";
		ResponseEntity response = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		System.out.println(response.getBody());
	}

	@Test
	public void deveRetornarParadaProducaoPorCodigo() {
		String uri = "/erp/parada-producao/1";
		ResponseEntity response = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
		System.out.println(response.getBody());
	}

	@Test
	public void incluirParadaProducao() throws JsonProcessingException {
		String uri = "/erp/parada-producao";
		ParadaProducaoDTO dto = new ParadaProducaoDTO();
		dto.setDataHoraInicio(LocalDateTime.now());
		LinhaProducaoDTO linha = new LinhaProducaoDTO();
		linha.setId((long) 1);
		dto.setLinha(linha);

		TurnoDTO turno = new TurnoDTO();
		turno.setId((long) 1);
		dto.setTurno(turno);

		String payload = this.mapper.writeValueAsString(dto);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(payload, headers);

		ResponseEntity response = restTemplate.postForEntity(uri, dto, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void alterarParadaProducao() throws JsonProcessingException {
		String uri = "/erp/parada-producao/1";
		ParadaProducaoDTO dto = new ParadaProducaoDTO();
		dto.setId((long) 1);
		dto.setDataHoraInicio(LocalDateTime.now());
		LinhaProducaoDTO linha = new LinhaProducaoDTO();
		linha.setId((long) 1);
		dto.setLinha(linha);

		TurnoDTO turno = new TurnoDTO();
		turno.setId((long) 1);
		dto.setTurno(turno);

		String payload = this.mapper.writeValueAsString(dto);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(payload, headers);

		restTemplate.put(uri, dto, String.class);
	}

}
