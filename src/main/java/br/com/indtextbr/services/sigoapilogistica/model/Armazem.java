package br.com.indtextbr.services.sigoapilogistica.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Armazem {
	@NotBlank(message = "Identificador do armazém é obrigatório")
	private String id;
	@NotBlank(message = "Endereço é obrigatório")
	private String endereco;
	@NotBlank(message = "Endereço é obrigatório")
	private String bairro;
	@NotBlank(message = "Cidade/Estado é obrigatório")
	@JsonProperty("cidade-estado")
	private String cidadeEstado;
	@NotBlank(message = "CEP é obrigatório")
	@JsonProperty("cep")
	private String CEP;
	@JsonProperty("armazena-items-para-venda")
	@NotNull(message = "Não pode ser nulo")
	private Boolean armazenaItemsParaVenda;
	
	@JsonProperty("armazena-items-para-compra")
	@NotNull(message = "Não pode ser nulo")
	private Boolean armazenaItemsParaCompra;
	
	private String status;

}
