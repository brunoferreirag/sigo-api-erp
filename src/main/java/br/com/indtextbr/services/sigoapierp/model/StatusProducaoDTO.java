package br.com.indtextbr.services.sigoapierp.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusProducaoDTO {
	private FaseIndustrialDTO fase;
	@JsonProperty("percentual-concluido")
	private BigDecimal percentualConcluido;
	@JsonProperty("percentual-planejado")
	private BigDecimal percentualPlanejado;
	@JsonProperty("possui-parada-producao")
	private Boolean possuiParadaProducao;
}
