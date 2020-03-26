package chess.domain.chessPiece.pieceType;

import java.util.Objects;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.MovableStrategy.RookMovableStrategy;
import chess.domain.chessPiece.PieceColor;

public class Rook implements PieceType {

	public static final String NAME = "R";

	private final PieceColor pieceColor;
	private final MovableStrategy movableStrategy;

	Rook(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "룩의 피스 색상이 null입니다.");
		this.pieceColor = pieceColor;
		this.movableStrategy = new RookMovableStrategy();
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
