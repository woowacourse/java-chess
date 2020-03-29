package chess.domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.BlackPieces;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.WhitePieces;
import chess.domain.piece.WhitePiecesFactory;

/**
 *    체스 판을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class Board {
	private final Map<Position, Piece> board;

	private Board() {
		board = new HashMap<>();

		WhitePieces whitePieces = WhitePiecesFactory.create();
		BlackPieces blackPieces = BlackPiecesFactory.create();

		for (Rank rank : Rank.values()) {
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
	}

	public static Board getInstance() {
		return BoardLazyHolder.INSTANCE;
	}

	public boolean canMoveBy(List<Position> trace) {
		Position source = trace.remove(0);
		Position target = trace.remove(trace.size() - 1);

		if (trace.size() == 0) {
			return true;
		}
		return trace.stream()
			.anyMatch(position -> board.get(position) != null);
	}

	public void change(Position source, Position target) {
		Piece sourcePiece = board.get(source);
		board.put(source, null);
		board.put(target, sourcePiece);
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}

	private static class BoardLazyHolder {
		private final static Board INSTANCE = new Board();
	}
}
