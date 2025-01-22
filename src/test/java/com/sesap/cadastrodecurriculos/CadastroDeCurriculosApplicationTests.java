package com.sesap.cadastrodecurriculos;

import com.sesap.cadastrodecurriculos.controller.CurriculoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc

class CadastroDeCurriculosApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testarEnvio() throws Exception{
		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);

		mockMvc.perform(multipart("/api/curriculos/enviar")
				.file(arquivo)
				.param("nome", "João Silva")
				.param("email","joao@example.com")
				.param("telefone","12345678")
				.param("cargoDesejado", "telefonista")
				.param("escolaridade","Superior")
				.param("observacoes", "oii")).andExpect(status().isOk()).andExpect(jsonPath("$.nome").value("João Silva"))
				.andExpect(jsonPath("$.datahora").exists());


	}

}
