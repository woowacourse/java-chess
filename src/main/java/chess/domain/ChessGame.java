package chess.domain;

import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;

import java.util.Map;

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

	public Pieces getPieces() {
		return pieces;
	}

	public boolean isKingDead() {
		return pieces.isKingDead();
	}

	public Color getAliveKingColor() {
		return pieces.getAliveKingColor();
	}

	public Map<Color, Double> calculateScore() {
		return ScoreResult.calculateScore(pieces.getPieces());
	}
}
