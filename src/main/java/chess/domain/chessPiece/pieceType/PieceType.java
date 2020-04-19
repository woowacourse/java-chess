package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.pieceStrategy.BishopStrategy;
import chess.domain.chessPiece.pieceStrategy.BlackPawnStrategy;
import chess.domain.chessPiece.pieceStrategy.KingStrategy;
import chess.domain.chessPiece.pieceStrategy.KnightStrategy;
import chess.domain.chessPiece.pieceStrategy.PieceStrategy;
import chess.domain.chessPiece.pieceStrategy.QueenStrategy;
import chess.domain.chessPiece.pieceStrategy.RookStrategy;
import chess.domain.chessPiece.pieceStrategy.WhitePawnStrategy;
import chess.domain.position.Position;

public enum PieceType implements PieceStrategy {

	BLACK_KING(PieceColor.BLACK, new KingStrategy(), "K", 0),
	WHITE_KING(PieceColor.WHITE, new KingStrategy(), "k", 0),
	BLACK_KNIGHT(PieceColor.BLACK, new KnightStrategy(), "N", 2.5),
	WHITE_KNIGHT(PieceColor.WHITE, new KnightStrategy(), "n", 2.5),
	BLACK_QUEEN(PieceColor.BLACK, new QueenStrategy(), "Q", 9),
	WHITE_QUEEN(PieceColor.WHITE, new QueenStrategy(), "q", 9),
	BLACK_ROOK(PieceColor.BLACK, new RookStrategy(), "R", 5),
	WHITE_ROOK(PieceColor.WHITE, new RookStrategy(), "r", 5),
	BLACK_BISHOP(PieceColor.BLACK, new BishopStrategy(), "B", 3),
	WHITE_BISHOP(PieceColor.WHITE, new BishopStrategy(), "b", 3),
	BLACK_PAWN(PieceColor.BLACK, new BlackPawnStrategy(), "P", 1),
	WHITE_PAWN(PieceColor.WHITE, new WhitePawnStrategy(), "p", 1);

	private final PieceColor pieceColor;
	private final PieceStrategy pieceStrategy;
	private final String name;
	private final double score;

	PieceType(final PieceColor pieceColor, final PieceStrategy pieceStrategy, final String name, final double score) {
		this.pieceColor = pieceColor;
		this.pieceStrategy = pieceStrategy;
		this.name = name;
		this.score = score;
	}

	@Override
	public boolean canLeap() {
		return this.pieceStrategy.canLeap();
	}

	@Override
	public boolean canMove(final Position sourcePosition, final Position targetPosition, final int pawnMovableRange) {
		return this.pieceStrategy.canMove(sourcePosition, targetPosition, pawnMovableRange);
	}

	@Override
	public boolean canCatch(final Position sourcePosition, final Position targetPosition) {
		return this.pieceStrategy.canCatch(sourcePosition, targetPosition);
	}

	public boolean isSame(final PieceColor pieceColor) {
		return this.pieceColor.equals(pieceColor);
	}

	public boolean isSamePieceColor(final PieceType pieceType) {
		return this.pieceColor.equals(pieceType.pieceColor);
	}

	public boolean isPawn() {
		return this.equals(BLACK_PAWN) || this.equals(WHITE_PAWN);
	}

	public boolean isKing() {
		return this.equals(BLACK_KING) || this.equals(WHITE_KING);
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}

}
