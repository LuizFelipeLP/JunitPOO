package lfpl1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import org.junit.jupiter.api.Test;

class NucleotideoTeste {

	 @BeforeEach
	    void setup() throws IOException {
	        Files.writeString(Path.of("seqValida.txt"), "AAAGTCTGAC");
	        Files.writeString(Path.of("seqComErro.txt"), "AACTGTCGBA");
	        Files.writeString(Path.of("seqInvalida.txt"), "ABC TEM FALHA");
	    }

	    // Finalizador para excluir os arquivos após os testes
	    @AfterEach
	    void cleanup() throws IOException {
	        Files.deleteIfExists(Path.of("seqValida.txt"));
	        Files.deleteIfExists(Path.of("seqComErro.txt"));
	        Files.deleteIfExists(Path.of("seqInvalida.txt"));
	    }

	    // Teste para a sequência válida
	    @Test
	    @DisplayName("Verifica se o método calcula corretamente o número de nucleotídeos com uma sequência válida.")
	    void testSequenciaValida() throws Exception {
	        int[] esperado = {4, 2, 2, 2, 0};
	        assertArrayEquals(esperado, calculaNucleotideos("seqValida.txt"));
	    }

	    // Teste para sequência com erro
	    @Test
	    @DisplayName("Verifica se o método conta corretamente nucleotídeos e erros em uma sequência com um caractere inválido.")
	    void testSequenciaComErro() throws Exception {
	        int[] esperado = {3, 2, 2, 2, 1};
	        assertArrayEquals(esperado, calculaNucleotideos("seqComErro.txt"));
	    }

	    // Teste para sequência inválida com mais de 10% de caracteres inválidos
	    @Test
	    @DisplayName("Verifica se o método retorna null quando o número de caracteres inválidos ultrapassa 10%.")
	    void testSequenciaInvalida() throws Exception {
	        assertNull(calculaNucleotideos("seqInvalida.txt"));
	    }

	    // Teste para arquivo não encontrado
	    @Test
	    @DisplayName("Verifica se o método lança uma exceção quando o arquivo não é encontrado.")
	    void testArquivoNaoEncontrado() {
	        Exception exception = assertThrows(FileNotFoundException.class, () -> {
	            calculaNucleotideos("arquivoInexistente.txt");
	        });
	        assertEquals("Arquivo não encontrado.", exception.getMessage());
	    }

}
