package Labyrinth;

public class HighScore {
    
    private final int name;
    private final int score;

    public HighScore(int name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + '}';
    }
    

}
