package chess.domain;

import static chess.domain.piece.Color.*;

import java.util.Map;
import java.util.Set;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class GameManager {
	private Board board;
	private Color currentTurn;

	public GameManager(Board board) {
		this.board = board;
		this.currentTurn = WHITE;
	}

	// @TODO 커스텀 exception 만들기
	public void move(Command command) {
		Position targetPosition = command.getTargetPosition();
		Position destination = command.getDestination();

		validateMove(targetPosition, destination);

		board.movePiece(targetPosition, destination);
		nextTurn();
	}

	private void validateMove(Position targetPosition, Position destination) {
		Piece target = board.findPieceBy(targetPosition);
		validateTurn(target);
		validateMovablePosition(target, targetPosition, destination);
	}

	private void validateTurn(Piece target) {
		if (target.isNotSameColor(currentTurn)) {
			throw new IllegalArgumentException("자신의 턴이 아닙니다.");
		}
	}

	private void validateMovablePosition(Piece target, Position targetPosition, Position destination) {
		Set<Position> movablePositions = target.findMovablePositions(targetPosition, board);
		if (!movablePositions.contains(destination)) {
			throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
		}
	}

	private void nextTurn() {
		currentTurn = currentTurn.reverse();
	}

	public Map<Color, Double> calculateEachScore() {
		ScoreRule scoreRule = new ScoreRule(board.getPieces());
		return scoreRule.calculateScore();
	}

	public Color getCurrentTurn() {
		return currentTurn;
	}

	public boolean isKingAlive() {
		return board.isKingAliveOf(currentTurn);
	}
}
