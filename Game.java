import java.lang.reflect.Array;
import java.util.Random;

public class Game {

    private final int MIN = 0;
    private final int MAX = 9;
    private final int LENGTH = 4;
    private String userGuess;
    private int compRand[];
    private int countGuesses;
    private int bool;
    private int pgia;

    //Constructor
    public Game(String userGuess){
        this.userGuess = userGuess;
        this.compRand = createRandomDistinctNum();
        this.countGuesses = 0;
    }

    public String getUserGuess(){
        return this.userGuess;
    }

    public void setRand(String rand){
        this.userGuess = rand;
    }

    public int[] getCompRand(){
        return this.compRand;
    }

    public void setCompRand(int[] compRand){
        this.compRand = compRand;
    }

    public int getBool(){
        return this.bool;
    }

    public void setBool(int bool){
        this.bool = bool;
    }

    public int getPgia(){
        return this.pgia;
    }

    public void setPgia(int pgia){
        this.pgia = pgia;
    }

    public int getCountGuesses(){
        return this.countGuesses;
    }

    public void setCountGuesses(int countGuesses){
        this.countGuesses = countGuesses;
    }

    /**
     * This method checks the user guess.
     */
    public boolean play(){
        int bool = 0;
        int pgia = 0;
        this.countGuesses++;

        for (int i = 0; i < LENGTH; ++i){
            for (int j = 0; j < LENGTH; ++j){
                if(this.userGuess.charAt(j) == (compRand[i] + '0')){
                    if(i == j)
                        bool++;
                    else pgia++;
                }
            }
        }
        if (bool == LENGTH){
            this.bool = bool;
            this.pgia = 0;
            return true;
        }
        this.bool = bool;
        this.pgia = pgia;
        return false;
    }

    /**
     * This method checks either the user input is valid, or not.
     * in case its valid - return true, otherwise - return false.
     */
    public boolean validation(){
        String str = this.userGuess;

        if(str.length() != LENGTH)
            return false;

        for (int i = 0; i < LENGTH; ++i){
            for (int j = 0; j < LENGTH; ++j){
                if((str.charAt(i) == str.charAt(j) && i != j) ||  !Character.isDigit(str.charAt(j)))
                    return false;
            }
        }
        return true;
    }

    /**
     * This method reset the relevant parameters for new guess (at the same round).
     */
    public void keepGuessing(String userGuess){
        this.userGuess = userGuess;
        this.bool = 0;
        this.pgia = 0;
    }

    /**
     * This method reset all the parameters for a new round.
     */
    public void restart(String userGuess){
        this.userGuess = userGuess;
        this.compRand = createRandomDistinctNum();
        this.countGuesses = 0;
        this.bool = 0;
        this.pgia = 0;
    }

    /**
     * This method generates the computer's 4-digit random number.
     */
    public int[] createRandomDistinctNum(){
        int hash[] = new int[MAX + 1];
        int num[] = new int[LENGTH];

        for(int i = 0; i < LENGTH; ++i){
            num[i] = (int) Math.floor(Math.random() * (MAX - MIN + 1) + MIN);
            while(hash[num[i]] != 0)
                num[i] = (int) Math.floor(Math.random() * (MAX - MIN + 1) + MIN);
            if(hash[num[i]] == 0)
                hash[num[i]]++;
        }

        return num;
    }
}


