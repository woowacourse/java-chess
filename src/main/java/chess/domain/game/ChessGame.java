package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesInitializer;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.position.positions.Positions;
import chess.dto.BoardDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChessGame {
	private Pieces pieces;
	private Turn turn;

	public ChessGame() {
		pieces = PiecesInitializer.operate();
		turn = new Turn(Color.WHITE);
	}

	public void move(Position start, Position end) {
		pieces.move(start, end, turn.getColor());
		turn = turn.change();
	}

	public ScoreResult calculateScore() {
		return new ScoreResult(pieces);
	}

	public boolean isKingDead() {
		return pieces.isKingDead();
	}

	public Color getAliveKingColor() {
		return pieces.getAliveKingColor();
	}

	public Board createBoard() {
		return new Board(pieces);
	}

	public Positions findMovablePositions(Position position) {
		Piece piece = pieces.findBy(position, turn.getColor());
		return piece.createMovablePositions(pieces.getPieces());
	}

	public List<String> findMovablePositionNames(String position) {
		return this.findMovablePositions(PositionFactory.of(position))
				.getPositions()
				.stream()
				.map(Position::toString)
				.collect(Collectors.toList());
	}

	public Turn getTurn() {
		return turn;
	}

	public Pieces getPieces() {
		return pieces;
	}
}
