package se.liu.vicbe988.background;

public class GameState implements IGameState {

    private boolean hasWon;

    @Override
    public boolean hasWon() {
	return hasWon;
    }

    @Override
    public void setHasWon(boolean won) {
	this.hasWon = won;
    }
}
