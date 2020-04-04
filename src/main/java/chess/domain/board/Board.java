package chess.domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.WhitePiecesFactory;

/**
 *    체스 판을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class Board {
	private final Map<Position, Piece> board;

	public Board() {
		board = new HashMap<>();

		for (Rank rank : Rank.values()) {
			makeBoardBy(rank);
		}
	}

	private void makeBoardBy(Rank rank) {
		Pieces whitePieces = WhitePiecesFactory.create();
		Pieces blackPieces = BlackPiecesFactory.create();

		for (File file : File.values()) {
			Position position = Position.of(file.getFile() + rank.getRow());
			if (whitePieces.hasPiece(position)) {
				board.put(position, whitePieces.getPiece(position));
			}
			if (blackPieces.hasPiece(position)) {
				board.put(position, blackPieces.getPiece(position));
			}
			if (!whitePieces.hasPiece(position) && !blackPieces.hasPiece(position)) {
				board.put(position, null);
			}
		}
	}

	public boolean canMoveBy(List<Position> trace) {
		Position source = trace.remove(0);
		Position target = trace.remove(trace.size() - 1);

		if (board.get(source).isPawn()) {
			return canPawnAttack(source, target) || canPawnMove(source, target);
		}
		if (trace.size() == 0) {
			return true;
		}
		return trace.stream()
			.allMatch(position -> board.get(position) == null);
	}

	private boolean canPawnAttack(Position source, Position target) {
		return Objects.nonNull(board.get(target))
			&& source.nextPosition(Direction.DIAGONAL_DIRECTION).stream()
			.anyMatch(position -> position.equals(target));
	}

	private boolean canPawnMove(Position source, Position target) {
		return Objects.isNull(board.get(target))
			&& source.nextPosition(Direction.PAWN_MOVE_DIRECTION).stream()
			.anyMatch(position -> position.equals(target));
	}

	public void change(Position source, Position target) {
		Piece sourcePiece = board.get(source);
		board.put(source, null);
		board.put(target, sourcePiece);
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}
}
