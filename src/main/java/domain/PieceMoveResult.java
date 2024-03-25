package domain;

public enum PieceMoveResult {
	SUCCESS, FAILURE, CATCH;

	public boolean isMoved() {
		return !this.equals(FAILURE);
	}
}
