package chess.domain.chessPiece.pieceType;

import java.util.Objects;

import chess.domain.MovableStrategy.KnightMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

public class Knight implements PieceType {

	public static final String NAME = "N";

	private final PieceColor pieceColor;
	private final MovableStrategy movableStrategy;

	Knight(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "나이트의 피스 색상이 null입니다.");
		this.pieceColor = pieceColor;
		this.movableStrategy = new KnightMovableStrategy();
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
