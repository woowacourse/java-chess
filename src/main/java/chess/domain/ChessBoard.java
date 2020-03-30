package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.board.Position;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.WhitePiecesFactory;

public class ChessBoard {
	public static final int KING_LIVE_COUNT = 1;

	private Map<Position, Piece> pieces;

	public ChessBoard(Map<Position, Piece> pieces) {
		this.pieces = pieces;
		WhitePiecesFactory.create(pieces);
		BlackPiecesFactory.create(pieces);
	}

	public void moveFromTo(Color color, Position source, Position target) {
		Piece sourcePiece = pieces.get(source);
		Piece targetPiece = pieces.get(target);

		validateEmptySourcePosition(sourcePiece);
		validateOtherPieceSourcePosition(color, sourcePiece);
		validateSameColorTargetPosition(color, targetPiece);
		List<Position> positions = sourcePiece.movablePositions(source, pieces);
		validateNotMovablePosition(target, positions);

		pieces.remove(source);
		pieces.put(target, sourcePiece);
	}

	private void validateEmptySourcePosition(Piece sourcePiece) {
		if (Objects.isNull(sourcePiece)) {
			throw new UnsupportedOperationException("source에 빈 칸을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	private void validateOtherPieceSourcePosition(Color color, Piece sourcePiece) {
		if (!sourcePiece.isSameColor(color)) {
			throw new UnsupportedOperationException("source에 상대방의 말을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	private void validateSameColorTargetPosition(Color color, Piece targetPiece) {
		if (Objects.nonNull(targetPiece) && targetPiece.isSameColor(color)) {
			throw new UnsupportedOperationException("target에 자신의 말이 있습니다!");
		}
	}

	private void validateNotMovablePosition(Position target, List<Position> positions) {
		if (!isMovable(positions, target)) {
			throw new UnsupportedOperationException("target에 갈 수 없는 곳을 선택하셨습니다! 다시 선택해주세요!");
		}
	}

	public boolean isMovable(List<Position> positions, Position target) {
		return positions.stream()
			.anyMatch(target::equals);
	}

	public boolean isKingNotDead(Color color) {
		long kingLiveCount = pieces.keySet().stream()
			.map(position -> pieces.get(position))
			.filter(Piece::isKing)
			.filter(piece -> piece.isSameColor(color))
			.count();

		return kingLiveCount == KING_LIVE_COUNT;
	}

	public Piece getPiece(String position) {
		return pieces.get(Position.of(position));
	}
}
