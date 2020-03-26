package chess.domain;

import java.util.Set;

import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class GameManager {
	private Board board;
	private Color currentTurn;

	public GameManager(Board board) {
		this.board = board;
		this.currentTurn = Color.WHITE;
	}

	// @TODO 커스텀 exception 만들기
	public void move(Command command) {
		Position targetPosition = command.getTargetPosition();
		Position destination = command.getDestination();

		Piece target = board.findPieceBy(targetPosition);
		if (target.isNotSameColor(currentTurn)) {
			throw new IllegalArgumentException("자신의 턴이 아닙니다.");
		}
		Set<Position> movablePositions = target.findMovablePositions(targetPosition, board);
		if (!movablePositions.contains(destination)) {
			throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
		}
		board.movePiece(targetPosition, destination);
		nextTurn();
	}

	private void nextTurn() {
		currentTurn = currentTurn.reverse();
	}
}
