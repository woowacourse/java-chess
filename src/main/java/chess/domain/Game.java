package chess.domain;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Pieces;

/**
 *    체스 게임을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class Game {
	private final Pieces pieces;
	private final Board board;

	public Game(Pieces pieces, Board board) {
		this.pieces = pieces;
		this.board = board;
	}

	public void movePieceFromTo(Position source, Position target) {
		List<Position> trace = pieces.movingTrace(source, target);

		if (pieces.canMove(source, target) && board.canMoveBy(trace)) {
			board.change(source, target);
			pieces.move(source, target);
		}
	}

	public Board getBoard() {
		return board;
	}
}
