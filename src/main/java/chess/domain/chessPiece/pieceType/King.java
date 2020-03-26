package chess.domain.chessPiece.pieceType;

import java.util.Objects;

import chess.domain.MovableStrategy.KingMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

public class King implements PieceType {

	public static final String NAME = "K";

	private final PieceColor pieceColor;
	private final MovableStrategy movableStrategy; // TODO: 2020/03/26 movableStrategy로 명명 수정

	King(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "킹의 피스 색상이 null입니다.");
		this.pieceColor = pieceColor;
		this.movableStrategy = new KingMovableStrategy();
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}

}
