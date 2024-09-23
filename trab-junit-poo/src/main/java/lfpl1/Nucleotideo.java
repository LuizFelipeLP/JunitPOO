package lfpl1;

import java.io.*;
import java.nio.file.*;

public class Nucleotideo {
	public static int[] calculaNucleotideos(String caminhoArquivo) throws IOException {
        String sequencia;
        try {
            sequencia = Files.readString(Path.of(caminhoArquivo)).toUpperCase(); // Lê o conteúdo do arquivo
        } catch (IOException e) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        }

        int[] contagem = new int[5]; // Array para armazenar [A, C, G, T, Erros]

        for (char nucleo : sequencia.toCharArray()) {
            switch (nucleo) {
                case 'A':
                    contagem[0]++;
                    break;
                case 'C':
                    contagem[1]++;
                    break;
                case 'G':
                    contagem[2]++;
                    break;
                case 'T':
                    contagem[3]++;
                    break;
                default:
                    contagem[4]++; // Contagem de erros
            }
        }

        // Verifica se os erros excedem 10% da sequência
        int totalCaracteres = sequencia.length();
        if (contagem[4] > totalCaracteres * 0.1) {
            return null;
        }

        return contagem;
    }
}
