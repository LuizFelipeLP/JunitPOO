/*
Classe Nucleotideo, que será testada
Author: LuizFelipe
*/
package lfpl1;

import java.io.*;
import java.nio.file.*;

public class Nucleotideo {
	public static int[] calculaNucleotideos(String nomeArquivo) throws IOException {
        String sequencia;
        try {
            sequencia = Files.readString(Path.of(nomeArquivo)).toUpperCase();
        } catch (IOException e) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        }

        int[] contagem = new int[5];

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
                    contagem[4]++;
            }
        }

        int totalCaracteres = sequencia.length();
        if (contagem[4] > totalCaracteres * 0.1) {
            return null;
        }

        return contagem;
    }
}
