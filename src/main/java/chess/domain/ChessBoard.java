package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.board.Position;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.WhitePiecesFactory;

public class ChessBoard {
	private Map<Position, Piece> pieces = new HashMap<>();

	public ChessBoard() {
		WhitePiecesFactory.create(pieces);
		BlackPiecesFactory.create(pieces);
	}

	public void moveFromTo(Color color, Position source, Position target) {
		Piece sourcePiece = pieces.get(source);
		Piece targetPiece = pieces.get(target);

		selectEmptySourcePosition(sourcePiece);
		selectOtherPieceSourcePosition(color, sourcePiece);
		selectSameColorTargetPosition(color, targetPiece);
		List<Position> positions = sourcePiece.movablePositions(source, pieces);
		selectNotMovablePosition(target, positions);

		// 이동함.
		pieces.put(source, null);
		pieces.put(target, sourcePiece);
	}

	private void selectNotMovablePosition(Position target, List<Position> positions) {
		if (!isMovable(positions, target)) {
			throw new UnsupportedOperationException("갈 수 없는 곳을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	private void selectSameColorTargetPosition(Color color, Piece targetPiece) {
		if (Objects.nonNull(targetPiece) && targetPiece.isSameColor(color)) {
			throw new UnsupportedOperationException("움직이려는 지점에 자신의 말이 있습니다!");
		}
	}

	private void selectOtherPieceSourcePosition(Color color, Piece sourcePiece) {
		if (!sourcePiece.isSameColor(color)) {
			throw new UnsupportedOperationException("상대방의 말을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	private void selectEmptySourcePosition(Piece sourcePiece) {
		if (Objects.isNull(sourcePiece)) {
			throw new UnsupportedOperationException("빈 칸을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	public Piece getPiece(String position) {
		return pieces.get(Position.of(position));
	}

	public boolean isMovable(List<Position> positions, Position target) {
		return positions.stream()
			.anyMatch(target::equals);
	}
}
