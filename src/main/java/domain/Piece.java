package domain;

abstract class Piece {
	abstract PieceMoveResult move(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard);

	abstract boolean isOn(Position position);

	public abstract Team getTeam();

	public abstract PieceType getPieceType();
}
