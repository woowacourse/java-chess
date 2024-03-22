package domain;

enum PieceMoveResult {
	SUCCESS, FAILURE, CATCH;

	boolean isMoved() {
		return !this.equals(FAILURE);
	}
}
