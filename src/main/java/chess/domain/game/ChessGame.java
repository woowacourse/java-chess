package chess.domain.game;

import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesFactory;

public class ChessGame {
	private final Pieces pieces;
	private Color color;

	public ChessGame() {
		pieces = PiecesFactory.of();
		color = Color.WHITE;
	}

	public void move(Position start, Position end) {
		pieces.move(start, end, color);
		color = color.changeColor();
	}

	public ScoreResult calculateScore() {
		return new ScoreResult(pieces.getPieces());
	}

	public boolean isKingDead() {
		return pieces.isKingDead();
	}

	public Color getAliveKingColor() {
		return pieces.getAliveKingColor();
	}

	public Pieces getPieces() {
		return pieces;
	}
}
