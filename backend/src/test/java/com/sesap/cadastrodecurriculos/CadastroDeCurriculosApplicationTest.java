package com.sesap.cadastrodecurriculos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc

class CadastroDeCurriculosApplicationTest {

	@Autowired
	private MockMvc mockMvc;
    
	private MockMultipartFile criarArquivo(String nome, String tipo, byte[] conteudo) {
        return new MockMultipartFile("arquivo", nome, tipo, conteudo);
    }




	//Classe de equivalência (Campos obrigatórios)
	@Test
    public void nomeVazio() throws Exception {
        MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("email", "Joao@example.com")
                        .param("telefone", "12345678")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
                        .param("observacoes", "oii"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("O nome é obrigatório"));
    }

	@Test
    public void cargoDesejadoVazio() throws Exception {
        MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("telefone", "12345678")
                        .param("escolaridade", "Superior")
                        .param("observacoes", "oii"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("O cargo desejado é obrigatório"));
    }

	@Test
    public void emailVazio() throws Exception {
        MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("telefone", "12345678")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
                        .param("observacoes", "oii"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("O email é obrigatório"));
    }

	@Test
	public void escolaridadeVazia() throws Exception{

		MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
						.param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("telefone", "12345678")
                        .param("cargoDesejado", "Telefonista")
                        .param("observacoes", "oii"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("Escolaridade inválida"));
    }

	@Test
	public void telefoneVazio() throws Exception {
        MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
                        .param("observacoes", "oii"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("O telefone é obrigatório"));
    }

	@Test
	public void telefoneDigitosAcima() throws Exception{

		MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
						.param("telefone","849990301675")
                        .param("observacoes", "oii"))
                .andExpect(status().isLengthRequired())
                .andExpect(jsonPath("$.message").value("O número de telefone deve conter 11 dígitos"));
    }

	@Test
	public void telefoneDigitosAbaixo() throws Exception{

			MockMultipartFile arquivo = criarArquivo("curriculo.pdf", "application/pdf", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
						.param("telefone","8499903016")
                        .param("observacoes", "oii"))
                .andExpect(status().isLengthRequired())
                .andExpect(jsonPath("$.message").value("O número de telefone deve conter 11 dígitos"));
    }


	@Test
    public void tipoArquivoInvalido() throws Exception {
        MockMultipartFile arquivo = criarArquivo("curriculo.txt", "text/plain", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("telefone", "12345678")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
                        .param("observacoes", "oii"))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.message").value("Extensão errada, somente são aceitos .pdf, .doc e .docx"));
    }

	//Validar arquivos .doc e .docx
	@Test
	public void testarEnvioDocx() throws Exception{

		MockMultipartFile arquivo = criarArquivo("curriculo.docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("telefone", "84994149173")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "SUPERIOR")
                        .param("observacoes", "oii"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"));
    }

	@Test
	public void testarLimiteMaximo() throws Exception{

		byte[] tamanhoArquivo = new byte[2*1024*1024];

		MockMultipartFile arquivo = new MockMultipartFile(
			"arquivo",
			"curriculo.pdf",
			"application.pdf",
			tamanhoArquivo
		);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("telefone", "12345678")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "Superior")
                        .param("observacoes", "oii"))
                .andExpect(status().isPayloadTooLarge())
                .andExpect(jsonPath("$.message").value("O tamanho máximo para o arquivo é 1Mb"));
    }

	@Test
	public void testarEnvioDoc() throws Exception{

		MockMultipartFile arquivo = criarArquivo("curriculo.doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "conteudo do arquivo".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/curriculos/enviar")
                        .file(arquivo)
                        .param("nome", "João")
                        .param("email", "Joao@example.com")
                        .param("telefone", "84994149173")
                        .param("cargoDesejado", "Telefonista")
                        .param("escolaridade", "SUPERIOR")
                        .param("observacoes", "oii"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"));
    }
}
