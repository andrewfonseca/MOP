package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P01Ifs corresponde ao 1o exercicio do capitulo de Decisoes da serie de
 * exercicios. Esta classe mostra o resultado e o resto da divisao por 3 de um
 * numero dentro de um intervalo de 1 a 20.<p>
 * @author Andre Fonseca
 */
public class P01Ifs {

    /**
     * O programa inicia-se por pedir ao utilizador um numero entre 1 e 20, atraves do metodo
     * nextLine() do objecto Scanner. De seguida este numero e' verificado, pela condicao if,
     * para ser correspondido ao intervalo desejado.
     * <p>
     * Se o numero nao estiver dentro do intervalo o programa ira' terminar indicando esse
     * facto. Caso contrario sera sera apresentado ao utilizador o resultado e o resto da
     * divisao por 3 e tambem se este e' um numero multiplo de 3.
     * <p>
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Just give me a number (1-30): ");
        String read = scanner.nextLine();

        float input = Float.parseFloat(read);

        if(input > 0 && input < 30) {
            System.out.println("Quotient: " + (input / 7));
            System.out.println("Remainder: " + (input % 7));

            System.out.println("This is" + (input % 5 != 0 ? " not" : "") + " a multiple number of 5");
        }
        else {
            System.out.println("Number is not within range.");
        }
    }

}