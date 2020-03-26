package chess.domain.chessPiece;

import java.util.function.Function;

import chess.domain.position.MoveDirection;

/// TODO: 2020/03/26 Initial Position 또는 Promotion Position을 가질 수 있도록
public enum PieceColor {
	WHITE(String::toLowerCase, MoveDirection.N),
	BLACK(String::toUpperCase, MoveDirection.S);

	private final Function<String, String> convertName;
	private final MoveDirection pawnMovableDirection;

	PieceColor(Function<String, String> convertName, MoveDirection pawnMovableDirection) {
		this.convertName = convertName;
		this.pawnMovableDirection = pawnMovableDirection;
	}

	public String convertName(String pieceName) {
		if (pieceName == null || pieceName.isEmpty()) {
			throw new IllegalArgumentException("체스 이름이 유효하지 않습니다.");
		}
		return convertName.apply(pieceName);
	}

	public MoveDirection getPawnMovableDirection() {
		return pawnMovableDirection;
	}
}
