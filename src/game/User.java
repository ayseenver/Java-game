/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 * Saves the player's name and score, so that the scores can be sorted
 * and the respective player name accessed.
 * 
 */
public class User implements Comparable<User> {

    String name;
    int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ":" + score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(User o){
        if (this.getScore() > o.getScore()){
            return -1;
        }else if(this.getScore() == o.getScore()){
            return 0;
        }else{
            return 1;
        }
    }
}
