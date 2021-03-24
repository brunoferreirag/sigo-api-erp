package br.com.indtextbr.services.sigoapierp.model;

import java.math.BigDecimal;

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
	private BigDecimal percentualConcluido;
	private Boolean possuiParadaProducao;
}
