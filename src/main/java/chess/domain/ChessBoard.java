package chess.domain;

import java.util.List;
import java.util.Objects;

import chess.domain.piece.Piece;

public class ChessBoard {
	List<Piece> chessBoard;

	public ChessBoard(List<Piece> chessBoard) {
		validateNullAndEmpty(chessBoard);
		this.chessBoard = chessBoard;
	}

	private void validateNullAndEmpty(List<Piece> chessBoard) {
		if (Objects.isNull(chessBoard) || chessBoard.isEmpty()) {
			throw new IllegalArgumentException("체스판엔 null이나 빈 값이 올 수 없습니다.");
		}
	}
}
