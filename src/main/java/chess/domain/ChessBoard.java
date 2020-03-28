package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

		validateBlock(source, target);
		validateTurn(sourcePiece, turn);
		validateAction(sourcePiece, target);
		pieces.removeIf(piece -> piece.isSamePosition(target));
		sourcePiece.move(target);
	}

	private void validateAction(Piece sourcePiece, Position target) {
		if (isPresentPiece(target)) {
			validateMove(sourcePiece, findByPosition(target));
			return;
		}
		validateAttack(sourcePiece, target);
	}

	private void validateTurn(Piece source, Side turn) {
		if (!source.isSameSide(turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	private void validateMove(Piece source, Piece target) {
		if (source.canNotAttack(target)) {
			throw new IllegalArgumentException("해당 위치로 기물을 옮길 수 없습니다.");
		}
	}

	private void validateAttack(Piece source, Position target) {
		if (source.canNotMove(target)) {
			throw new IllegalArgumentException("해당 위치로 기물을 옮길 수 없습니다.");
		}
	}

	private void validateBlock(Position source, Position target) {
		if (isBlock(source, target)) {
			throw new IllegalArgumentException("해당 위치로 가는 길이 막혀있습니다.");
		}
	}

	private boolean isBlock(Position source, Position target) {
		return pieces.stream()
				.anyMatch(piece -> piece.isBlock(source, target));
	}

	private Piece findByPosition(Position position) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(position))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("입력에 해당하는 말이 없습니다."));
	}

	private boolean isPresentPiece(Position position) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}

	public ChessStatus createStatus() {
		return new ChessStatus(pieces);
	}

	public boolean isEnd() {
		return calculateKingPiece() != Side.size();
	}

	private int calculateKingPiece() {
		return (int) pieces.stream()
				.filter(Piece::isKing)
				.count();
	}

	public List<Piece> getPieces() {
		return Collections.unmodifiableList(pieces);
	}
}
