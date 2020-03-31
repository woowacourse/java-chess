package chess.domain;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.PiecesManager;

/**
 *    체스 게임을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class Game {
	private final PiecesManager piecesManager;
	private final Board board;

	public Game(PiecesManager piecesManager, Board board) {
		this.piecesManager = piecesManager;
		this.board = board;
	}

	public void movePieceFromTo(Position source, Position target) {
		List<Position> trace = piecesManager.movingTrace(source, target);

		boolean canNotMove = !piecesManager.canMove(source, target) || !board.canMoveBy(trace);
		if (canNotMove) {
			throw new UnsupportedOperationException("해당 위치로 움직일 수 없습니다.");
		}
		board.change(source, target);
		piecesManager.move(source, target);
	}

	public boolean isKingDie() {
		return piecesManager.isKingDie();
	}

	public double[] status() {
		return new double[] {piecesManager.whitePiecesStatus(), piecesManager.blackPiecesStatus()};
	}

	public Board getBoard() {
		return board;
	}
}
