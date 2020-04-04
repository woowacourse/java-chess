package chess.domain.piece.pieces;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;

public enum PieceInitializer {
	A1(PieceType.ROOK, PositionFactory.of("a1")),
	B1(PieceType.KNIGHT, PositionFactory.of("b1")),
	C1(PieceType.BISHOP, PositionFactory.of("c1")),
	D1(PieceType.KING, PositionFactory.of("d1")),
	E1(PieceType.QUEEN, PositionFactory.of("e1")),
	F1(PieceType.BISHOP, PositionFactory.of("f1")),
	G1(PieceType.KNIGHT, PositionFactory.of("g1")),
	H1(PieceType.ROOK, PositionFactory.of("h1")),
	A2(PositionFactory.of("a2")),
	B2(PositionFactory.of("b2")),
	C2(PositionFactory.of("c2")),
	D2(PositionFactory.of("d2")),
	E2(PositionFactory.of("e2")),
	F2(PositionFactory.of("f2")),
	G2(PositionFactory.of("g2")),
	H2(PositionFactory.of("h2")),
	A7(PositionFactory.of("a7")),
	B7(PositionFactory.of("b7")),
	C7(PositionFactory.of("c7")),
	D7(PositionFactory.of("d7")),
	E7(PositionFactory.of("e7")),
	F7(PositionFactory.of("f7")),
	G7(PositionFactory.of("g7")),
	H7(PositionFactory.of("h7")),
	A8(PieceType.ROOK, PositionFactory.of("a8")),
	B8(PieceType.KNIGHT, PositionFactory.of("b8")),
	C8(PieceType.BISHOP, PositionFactory.of("c8")),
	D8(PieceType.KING, PositionFactory.of("d8")),
	E8(PieceType.QUEEN, PositionFactory.of("e8")),
	F8(PieceType.BISHOP, PositionFactory.of("f8")),
	G8(PieceType.KNIGHT, PositionFactory.of("g8")),
	H8(PieceType.ROOK, PositionFactory.of("h8"));

	private final PieceType pieceType;
	private final Position position;
	private final Color color;

	PieceInitializer(PieceType pieceType, Position position) {
		this.pieceType = pieceType;
		this.position = position;
		if (position.isHalfBottom()) {
			this.color = Color.WHITE;
			return;
		}
		this.color = Color.BLACK;
	}

	PieceInitializer(Position position) {
		this.position = position;
		if (position.isHalfBottom()) {
			this.pieceType = PieceType.WHITE_PAWN;

			this.color = Color.WHITE;
			return;
		}
		this.pieceType = PieceType.BLACK_PAWN;
		this.color = Color.BLACK;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}
}
