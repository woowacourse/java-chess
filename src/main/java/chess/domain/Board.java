package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class Board {
	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = new HashMap<>();
		initialize();
	}

	private void initialize() {
		initializeRook();
		// initializeKnight();
		// initializeBishop();
		// initializeKingQueen();
		// initializePawn();
	}

	private void initializeRook() {
		pieces.put(Position.of(Column.of("A"), Row.of("1")), new Rook("r", Color.WHITE));
		pieces.put(Position.of(Column.of("H"), Row.of("1")), new Rook("r", Color.WHITE));
		pieces.put(Position.of(Column.of("A"), Row.of("8")), new Rook("R", Color.BLACK));
		pieces.put(Position.of(Column.of("H"), Row.of("8")), new Rook("R", Color.BLACK));
	}

	private void initializeKnight() {
		pieces.put(Position.of(Column.of("B"), Row.of("1")), new Knight("n", Color.WHITE));
		pieces.put(Position.of(Column.of("G"), Row.of("1")), new Knight("n", Color.WHITE));
		pieces.put(Position.of(Column.of("B"), Row.of("8")), new Knight("N", Color.BLACK));
		pieces.put(Position.of(Column.of("G"), Row.of("8")), new Knight("N", Color.BLACK));
	}

	private void initializeBishop() {
		pieces.put(Position.of(Column.of("C"), Row.of("1")), new Bishop("b", Color.WHITE));
		pieces.put(Position.of(Column.of("F"), Row.of("1")), new Bishop("b", Color.WHITE));
		pieces.put(Position.of(Column.of("C"), Row.of("8")), new Bishop("B", Color.BLACK));
		pieces.put(Position.of(Column.of("F"), Row.of("8")), new Bishop("B", Color.BLACK));
	}

	private void initializeKingQueen() {
		pieces.put(Position.of(Column.of("D"), Row.of("1")), new Queen("q", Color.WHITE));
		pieces.put(Position.of(Column.of("E"), Row.of("1")), new King("k", Color.WHITE));
		pieces.put(Position.of(Column.of("D"), Row.of("8")), new Queen("Q", Color.BLACK));
		pieces.put(Position.of(Column.of("E"), Row.of("8")), new King("K", Color.BLACK));
	}

	private void initializePawn() {
		pieces.put(Position.of(Column.of("B"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("A"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("C"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("D"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("E"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("F"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("G"), Row.of("2")), new Pawn("p", Color.WHITE));
		pieces.put(Position.of(Column.of("H"), Row.of("2")), new Pawn("p", Color.WHITE));

		pieces.put(Position.of(Column.of("A"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("B"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("C"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("D"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("E"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("F"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("G"), Row.of("7")), new Pawn("P", Color.BLACK));
		pieces.put(Position.of(Column.of("H"), Row.of("7")), new Pawn("P", Color.BLACK));
	}

	public boolean isNotEmptyPosition(Position position) {
		return pieces.get(position) != null;
	}

	public Piece findPieceBy(Position position) {
		return pieces.get(position);
	}

	public void movePiece(Position from, Position to) {
		Piece target = pieces.remove(from);
		pieces.put(to, target);
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}
}
