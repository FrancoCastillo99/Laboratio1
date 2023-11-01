package EjMutantes;

import java.util.Scanner;

public class Mutante {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        String [] dna = new String[6];
        String [] dnaColumna = new String[6];
        String diagonal = "";
        char [] letras = {'A','C','G','T'};
        boolean mutante = false;

        for (int i = 0; i < 6; i++) {
            while(true){
                System.out.printf("Ingrese la "+(i+1)+"° secuancia de dna: ");
                dna[i] = leer.next().toUpperCase();

                if(dna[i].length() == 6 && dna[i].matches("[ATCG]+")){
                    break;
                }else{
                    System.out.println("Ingreso incorrecto de secuencia, vuelva a intentarlo");
                }
            }
        }

        for (int i = 0; i < dna[0].length(); i++) {
            StringBuilder columna = new StringBuilder();
            for (int j = 0; j < dna.length; j++) {
                columna.append(dna[j].charAt(i));
            }
            dnaColumna[i] = columna.toString();
        }

        diagonal = obtenerDiagonal(dna);

        if (isMutante(dna,dnaColumna,diagonal,letras)){
            mutante = true;
        }


        System.out.println();
        if(mutante){
            System.out.println("Es mutante");
        }else{
            System.out.println("No es mutante");
        }

    }

    public static boolean isMutante(String [] dna, String [] columnas,String diagonal, char [] letras){
        int contador = 0,contadorColumn = 0,contadorDiagonal =0, contadorTotal = 0;

        for (char busqueda : letras) {
            for (String secuencia : dna) {
                for (int i = 0; i < secuencia.length(); i++) {
                    if (secuencia.charAt(i) == busqueda) {
                        contador++;
                        if (contador == 4) {
                            contadorTotal++;
                        }
                    } else {
                        contador = 0;
                    }
                }
                contador = 0;
            }

            for (String column : columnas) {
                for (int i = 0; i < column.length(); i++) {
                    if (column.charAt(i) == busqueda) {
                        contadorColumn++;
                        if (contadorColumn == 4) {
                            contadorTotal++;
                        }
                    } else {
                        contadorColumn = 0;
                    }
                }
                contadorColumn = 0;
            }

            for (int i = 0; i < diagonal.length(); i++) {
                if (diagonal.charAt(i) == busqueda) {
                    contadorDiagonal++;
                    if (contadorDiagonal == 4) {
                        contadorTotal++;
                    }
                } else {
                    contadorDiagonal = 0;
                }
            }
        }

        if (contadorTotal > 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static String obtenerDiagonal(String[] vector) {
        int longitud = vector.length;

        // Verificar que todas las cadenas tengan la misma longitud
        for (String cadena : vector) {
            if (cadena.length() != longitud) {
                return "Las cadenas no tienen la misma longitud, no se puede obtener la diagonal principal";
            }
        }

        StringBuilder diagonal = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            diagonal.append(vector[i].charAt(i));
        }

        return diagonal.toString();
    }
}
