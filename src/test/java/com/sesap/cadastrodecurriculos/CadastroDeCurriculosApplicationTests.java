package com.sesap.cadastrodecurriculos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesap.cadastrodecurriculos.dto.CurriculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc

class CadastroDeCurriculosApplicationTests {

	@Autowired
	private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


	//Teste geral
	/*
	@Test
	public void testarEnvio() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("12345678");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
            "dados",
            "",
            MediaType.APPLICATION_JSON_VALUE,
            objectMapper.writeValueAsBytes(request)
    );

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
				.file(dados)
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("João"))
				.andExpect(jsonPath("$.cargoDesejado").value("telefonista"));
	}
*/
	//Classe de equivalência (Campos obrigatórios)
	@Test
	public void nomeVazio() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("");
		request.setEmail("Joao@example.com");
		request.setTelefone("12345678");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("O nome é obrigatório"));
	}

	@Test
	public void cargoVazio() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("12345678");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("O cargo desejado é obrigatório"));
	}

	@Test
	public void emailVazio() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setTelefone("12345678");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("O email é obrigatório"));
	}

	@Test
	public void escolaridadeVazia() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("12345678");
		request.setCargoDesejado("telefonista");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("Escolaridade inválida"));
	}

	@Test
	public void telefoneVazio() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.message").value("O telefone é obrigatório"));
	}

	@Test
	public void telefoneDigitosAcima() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("123456789012");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isLengthRequired())
				.andExpect(jsonPath("$.message").value("O número de telefone deve conter 11 dígitos"));
	}

	@Test
	public void telefoneDigitosAbaixo() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("1234567890");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.pdf",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isLengthRequired())
				.andExpect(jsonPath("$.message").value("O número de telefone deve conter 11 dígitos"));
	}


	@Test
	public void tipoInvalidoArquivo() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("12345678");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.jpg",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isUnsupportedMediaType())
				.andExpect(jsonPath("$.message").value("Extensão errada, somente são aceitos .pdf, .doc e .docx"));
	}

	//Validar arquivos .doc e .docx
	@Test
	public void testarEnvioDocx() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("84994149177");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.docx",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("João"))
				.andExpect(jsonPath("$.cargoDesejado").value("telefonista"));
	}

	@Test
	public void testarEnvioDoc() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("84994149177");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.doc",
				"application/pdf",
				"conteudo do arquivo em pdf".getBytes()

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("João"))
				.andExpect(jsonPath("$.cargoDesejado").value("telefonista"));
	}

	@Test
	public void testarLimiteMaximo() throws Exception{

		CurriculoDTO request = new CurriculoDTO();
		request.setNome("João");
		request.setEmail("Joao@example.com");
		request.setTelefone("12345678");
		request.setCargoDesejado("telefonista");
		request.setEscolaridade("Superior");
		request.setObservacoes("oii");

		byte[] tamanhoArquivo = new byte[2 * 1024 * 1024];// 2MB

		MockMultipartFile arquivo = new MockMultipartFile(
				"arquivo",
				"curriculo.doc",
				"application/pdf",
				tamanhoArquivo

		);
		MockMultipartFile dados = new MockMultipartFile(
				"dados",
				"",
				MediaType.APPLICATION_JSON_VALUE,
				objectMapper.writeValueAsBytes(request)
		);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar").file(arquivo)
						.file(dados)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isPayloadTooLarge()).andExpect(jsonPath("$.message").value("O tamanho máximo para o arquivo é 1Mb"));

	}

}
