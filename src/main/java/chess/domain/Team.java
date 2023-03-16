package chess.domain;

public enum Team {

	BLACK, WHITE, EMPTY;

	public boolean isBlack(){
		return this.equals(BLACK);
	}

	public boolean isWhite(){
		return this.equals(WHITE);
	}
}
