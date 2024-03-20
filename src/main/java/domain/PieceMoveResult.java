package domain;

enum PieceMoveResult {
	SUCCESS, FAILURE, CATCH;

	boolean toBoolean() {
		return !this.equals(FAILURE);
	}
}
