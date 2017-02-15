/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science;

/**
 *
 * @author Usuario1
 */
public class Util {
    public static int getRandomNumberBetwen(int M,int N){
        return (int) Math.floor(Math.random()*(N-M+1)+M);
    }
}
