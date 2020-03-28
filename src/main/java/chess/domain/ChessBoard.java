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
		Piece sourcePiece = findPieceBy(source);

		validateTurn(sourcePiece, turn);
		validateMove(source, target);
		checkIfPawn(sourcePiece, target);
		pieces.removeIf(piece -> piece.isSamePosition(target));
		sourcePiece.move(target);
	}

	private void checkIfPawn(Piece sourcePiece, Position target) {
		if (sourcePiece.isPawn() && sourcePiece.canMove(target) && isPresentPiece(target)) {
			throw new IllegalArgumentException("폰은 앞에 있는 말을 공격할 수 없습니다.");
		}
	}

	private void validateTurn(Piece sourcePiece, Side turn) {
		if (!sourcePiece.isSameSide(turn)) {
			throw new IllegalArgumentException("본인의 말만 움직일 수 있습니다.");
		}
	}

	private void validateMove(Position source, Position target) {
		if (canPawnAttack(source, target)) {
			return;
		}
		if (findPieceBy(source).canNotMove(target) || isBlock(source, target) || canNotAttack(source, target)) {
			throw new IllegalArgumentException("해당 위치로 기물을 옮길 수 없습니다.");
		}

	}

	private boolean canPawnAttack(Position source, Position target) {
		Piece sourcePiece = findPieceBy(source);
		Piece targetPiece = findPieceBy(target);

		return sourcePiece.isPawn() && source.isInDiagonal(target) && source.isInDistance(1,
				target) && sourcePiece.isForwardAttack(
				target) && isPresentPiece(target) && !sourcePiece.isSameSide(targetPiece);
	}

	private Piece findPieceBy(Position position) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(position))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("입력에 해당하는 말이 없습니다."));
	}

	private boolean canNotAttack(Position source, Position target) {
		return isPresentPiece(target) && findPieceBy(source).isSameSide(findPieceBy(target));
	}

	private boolean isBlock(Position source, Position target) {
		return pieces.stream()
				.anyMatch(piece -> piece.isBlock(source, target));
	}

	private boolean isPresentPiece(Position position) {
		return pieces.stream()
				.anyMatch(piece -> piece.isSamePosition(position));
	}

	public boolean isEnd() {
		return pieces.stream()
				.filter(piece -> piece.getClass() == King.class)
				.count() != 2;
	}

	public double calculateScore(Side side) {
		return pieces.stream()
				.filter(piece -> piece.isSameSide(side))
				.mapToDouble(Piece::getScore)
				.sum() + calculatePawnInSameCol(side);
	}

	public double calculatePawnInSameCol(Side side) {
		return Arrays.stream(Column.values())
				.mapToLong(col -> countColumnPawn(col, side))
				.filter(i -> i == 1)
				.count() * 0.5;
	}

	public long countColumnPawn(Column column, Side side) {
		return pieces.stream()
				.filter(piece -> piece.isPawn() && piece.isSameSide(side) && piece.isSameCol(column))
				.count();
	}

	public List<Piece> getPieces() {
		return Collections.unmodifiableList(pieces);
	}
}