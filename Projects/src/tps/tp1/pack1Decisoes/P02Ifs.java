package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P02Ifs corresponde ao 2o exercicio do capitulo de Decisoes da serie de
 * exercicios. Um objecto desta classe mostra a ordenacao descendente de 3 numeros
 * inteiros. A ordenacao dos numeros e' realizada pela comparacao entre cada um
 * sempre que e' introduzido um numero pelo utilizador.
 * <p>
 * @author Andre Fonseca
 */
public class P02Ifs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Give me three int numbers:\n");

        int[] input = new int[3];

        for (int i = 0; i < 3; i++) {
            input[i] = Integer.parseInt(scanner.nextLine());

            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    input[i] = input[i] * input[j];
                    input[j] = input[i] / input[j];
                    input[i] = input[i] / input[j];
                }
            }
        }

        System.out.printf("Greatest value: %d, middle value: %d, lowest value: %d", input[0], input[1], input[2]);
    }
}

