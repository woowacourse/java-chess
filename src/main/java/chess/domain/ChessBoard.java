package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;

public class ChessBoard {
	private List<Piece> pieces;

	public ChessBoard(List<Piece> pieces) {
		validateNullAndEmpty(pieces);
		this.pieces = pieces;
	}

	private void validateNullAndEmpty(List<Piece> pieces) {
		if (Objects.isNull(pieces) || pieces.isEmpty()) {
			throw new IllegalArgumentException("체스판엔 null이나 빈 값이 올 수 없습니다.");
		}
	}

	public void move(Position source, Position target, Side turn) {
		Piece sourcePiece = findByPosition(source);

		validateTurn(sourcePiece, turn);
		validateMove(source, target);
		pieces.removeIf(piece -> piece.isSamePosition(target));
		sourcePiece.move(target);
	}

	private void validateTurn(Piece sourcePiece, Side turn) {
		if (!sourcePiece.isSameSide(turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	private void validateMove(Position source, Position target) {
		if (findByPosition(source).canNotMove(target) || isBlock(source, target) || canNotAttack(source, target)) {
			throw new IllegalArgumentException("해당 위치로 기물을 옮길 수 없습니다.");
		}

	}


	private Piece findByPosition(Position position) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(position))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("입력에 해당하는 말이 없습니다."));
	}

	private boolean canNotAttack(Position source, Position target) {
		return isPresentPiece(target) && findByPosition(source).isSameSide(findByPosition(target));
	}

	private boolean isBlock(Position source, Position target) {
		return pieces.stream()
				.anyMatch(piece -> piece.isBlock(source, target));
	}

	private boolean isPresentPiece(Position position) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}

	public List<Piece> getPieces() {
		return Collections.unmodifiableList(pieces);
	}
}