package chess.domain.chessPiece.pieceType;

import java.util.Objects;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.MovableStrategy.QueenMovableStrategy;
import chess.domain.chessPiece.PieceColor;

public class Queen implements PieceType {

	public static final String NAME = "Q";

	private final PieceColor pieceColor;
	private final MovableStrategy movableStrategy;

	Queen(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "퀸의 피스 색상이 null입니다.");
		this.pieceColor = pieceColor;
		this.movableStrategy = new QueenMovableStrategy();
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
