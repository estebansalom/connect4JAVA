/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4fx;

import java.io.*;
/**
 *
 * @author esteb
 */
public class GUIRutinas {
    
    public static int contFichas1, contFichas2;
    
    public static char[][] matrizConnect4 = {
            {'-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-'}};
    
    public static int[] intentarPrimerJugador(int pindice) {
        int[] coordenadas = new int[3];
        int coordX = 0;
        int coordY = 0;
        int jugador = 1;
        //Busca de abajo hacia arriba en la columna que escogio el usuario
        for (int x = 5; x >= 0; x--) {
            //Al encontrar un espacio vacio
            if (matrizConnect4[x][pindice] == '-') {
                //Coloca la ficha en ese espacio
                matrizConnect4[x][pindice] = 'A';
                coordX = x;
                break;
            } else {
                if (x == 0) {

                }
            }
        }
        
        //Devuelve las coordenadas
        coordY = pindice;
        coordenadas[0] = coordX;
        coordenadas[1] = coordY;
        coordenadas[2] = jugador;
        contFichas1++;
        return coordenadas;
    }
    
    public static int[] intentarSegundoJugador(int pindice) {
        int[] coordenadas = new int[3];
        int coordX = 0;
        int coordY = 0;
        int jugador = 2;
        //Busca de abajo hacia arriba en la columna que escogio el usuario
        for (int x = 5; x >= 0; x--) {
            //Al encontrar un espacio vacio
            if (matrizConnect4[x][pindice] == '-') {
                //Coloca la ficha en ese espacio
                matrizConnect4[x][pindice] = 'R';
                coordX = x;
                break;
            } else {
                if (x == 0) {

                }
            }
        }
        
        //Devuelve las coordenadas
        coordY = pindice;
        coordenadas[0] = coordX;
        coordenadas[1] = coordY;
        coordenadas[2] = jugador;
        contFichas2++;
        return coordenadas;
    }
    
    public static int fichasJugador(int jugador){
        int fichasRestantes = 0;
        if(jugador==1){
            fichasRestantes = contFichas1;
        }
        if(jugador==2){
            fichasRestantes = contFichas2;
        }
        return fichasRestantes;
    }

    public static boolean ganoJuego(int[] pcoordenadas){
        int X, Y;
        boolean ganador = false;
        X = pcoordenadas[0];
        Y = pcoordenadas[1];    
        
        if(X < 3 && Y < 4){
            if(matrizConnect4[X][Y] == matrizConnect4[X][(Y+1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+2)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+3)]){
                ganador = true;
            }
            if(matrizConnect4[X][Y] == matrizConnect4[X+1][Y] && matrizConnect4[X][Y] == matrizConnect4[X+2][Y] && matrizConnect4[X][Y] == matrizConnect4[X+3][Y]){
                ganador = true;
            }
            if(matrizConnect4[X][Y] == matrizConnect4[X+1][Y+1] && matrizConnect4[X][Y] == matrizConnect4[X+2][Y+2] && matrizConnect4[X][Y] == matrizConnect4[X+3][Y+3]){
                ganador = true;
            }
        }
        
        if(X < 3 && Y > 2){
            if(matrizConnect4[X][Y] == matrizConnect4[X][(Y-1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y-2)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y-3)]){
                ganador = true;
            }
            if(matrizConnect4[X][Y] == matrizConnect4[X+1][Y] && matrizConnect4[X][Y] == matrizConnect4[X+2][Y] && matrizConnect4[X][Y] == matrizConnect4[X+3][Y]){
                ganador = true;
            }
            if(matrizConnect4[X][Y] == matrizConnect4[X+1][Y-1] && matrizConnect4[X][Y] == matrizConnect4[X+2][Y-2] && matrizConnect4[X][Y] == matrizConnect4[X+3][Y-3]){
                ganador = true;
            }
        }
        
        if(X > 2 && Y < 4){
            if(matrizConnect4[X][Y] == matrizConnect4[X][(Y+1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+2)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+3)]){
                ganador = true;
            }
            if(matrizConnect4[X][Y] == matrizConnect4[X-1][Y+1] && matrizConnect4[X][Y] == matrizConnect4[X-2][Y+2] && matrizConnect4[X][Y] == matrizConnect4[X-3][Y+3]){
                ganador = true;
            }
        }
        
        if(X > 2 && Y > 2){
            if(matrizConnect4[X][Y] == matrizConnect4[X][(Y-1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y-2)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y-3)]){
                ganador = true;
            }
            if(matrizConnect4[X][Y] == matrizConnect4[X-1][Y-1] && matrizConnect4[X][Y] == matrizConnect4[X-2][Y-2] && matrizConnect4[X][Y] == matrizConnect4[X-3][Y-3]){
                ganador = true;
            }
        }
        
        if(Y > 0 && Y < 5){
            if(matrizConnect4[X][Y] == matrizConnect4[X][(Y-1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+2)]){
                ganador = true;
            }
            
            if(X>1 && X<5){
                if(matrizConnect4[X][Y] == matrizConnect4[X+1][Y-1] && matrizConnect4[X][Y] == matrizConnect4[X-1][Y+1] && matrizConnect4[X][Y] == matrizConnect4[X-2][Y+2]){
                ganador = true;
                }
            }
            
            if(X>0 && X<4){
                if(matrizConnect4[X][Y] == matrizConnect4[X-1][Y-1] && matrizConnect4[X][Y] == matrizConnect4[X+1][Y+1] && matrizConnect4[X][Y] == matrizConnect4[X+2][Y+2]){
                ganador = true;
                }
            }
        }
        
        if(Y > 1 && Y < 6){
            if(matrizConnect4[X][Y] == matrizConnect4[X][(Y-2)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y-1)] && matrizConnect4[X][Y] == matrizConnect4[X][(Y+1)]){
                ganador = true;
            }
            
            if(X>1 && X<5){
                if(matrizConnect4[X][Y] == matrizConnect4[X-2][Y-2] && matrizConnect4[X][Y] == matrizConnect4[X-1][Y-1] && matrizConnect4[X][Y] == matrizConnect4[X+1][Y+1]){
                ganador = true;
                }
            }
            
            if(X>0 && X<4){
                if(matrizConnect4[X][Y] == matrizConnect4[X+2][Y-2] && matrizConnect4[X][Y] == matrizConnect4[X+1][Y-1] && matrizConnect4[X][Y] == matrizConnect4[X-1][Y+1]){
                ganador = true;
                }
            }
        }
        
        return ganador;
    }//Fin ganoJuego
    
    public static void reiniciarMatriz(){
        contFichas1 = 0;
        contFichas2 = 0;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                matrizConnect4[x][y] = '-';
            }
            matrizConnect4[x][6] = '-';
        }
    }
}
