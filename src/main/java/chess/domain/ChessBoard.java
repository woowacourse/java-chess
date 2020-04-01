package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;

public class ChessBoard {
	private List<Piece> pieces;

	public ChessBoard(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void move(Position source, Position target) {
		Piece sourcePiece = findPieceBy(source);
		validateBlock(source, target);
		validateAction(sourcePiece, target);

		pieces.removeIf(piece -> piece.isSamePosition(target));
		sourcePiece.move(target);
	}

	private void validateAction(Piece sourcePiece, Position target) {
		if (isPresent(target)) {
			validateAttack(sourcePiece, findPieceBy(target));
			return;
		}
		validateMove(sourcePiece, target);
	}

	private void validateAttack(Piece sourcePiece, Piece targetPiece) {
		if (sourcePiece.canNotAttack(targetPiece)) {
			throw new IllegalArgumentException("해당 위치로 기물을 옮길 수 없습니다.");
		}
	}

	private void validateMove(Piece sourcePiece, Position target) {
		if (sourcePiece.canNotMove(target)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
	}

	private void validateBlock(Position source, Position target) {
		if (isBlock(source, target)) {
			throw new IllegalArgumentException("이동하려는 경로가 막혀있습니다.");
		}
	}

	private boolean isBlock(Position source, Position target) {
		return pieces.stream()
				.anyMatch(piece -> piece.isBlock(source, target));
	}

	public Piece findPieceBy(Position position) {
		return pieces.stream()
				.filter(piece -> piece.isSamePosition(position))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
	}

	public boolean isPresent(Position position) {
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
				.filter(piece -> piece instanceof Pawn && piece.isSameSide(side) && piece.isSameCol(column))
				.count();
	}

	public boolean isRightTurn(Position source, Side turn) {
		return !findPieceBy(source).isSameSide(turn);
	}

	public List<Piece> getPieces() {
		return Collections.unmodifiableList(pieces);
	}
}
