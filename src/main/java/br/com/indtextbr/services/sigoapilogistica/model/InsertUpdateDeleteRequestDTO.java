package br.com.indtextbr.services.sigoapilogistica.model;

import br.com.indtextbr.services.sigoapilogistica.common.EnumAcaoEscritaDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertUpdateDeleteRequestDTO {
	private EnumAcaoEscritaDB acao;
	private Armazem armazem;

}
