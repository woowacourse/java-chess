package chess.domain;

import static chess.domain.piece.Color.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.dao.SQLConnector;
import chess.domain.dao.TurnDao;
import chess.domain.dto.TurnDto;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class GameManager {
	private static final String TURN_MISS_MATCH_MESSAGE = "자신의 턴이 아닙니다.";
	private static final String NOT_MOVABLE_MESSAGE = "이동할 수 없는 위치입니다.";

	private Board board;
	private TurnDao currentTurn;

	public GameManager(Board board) throws SQLException {
		this.board = board;
		this.currentTurn = new TurnDao(new SQLConnector());
		this.currentTurn.editTurn(TurnDto.of(WHITE));
	}

	public GameManager() throws SQLException {
		this.board = new Board(new HashMap<>());
		this.currentTurn = new TurnDao(new SQLConnector());
		this.currentTurn.findTurn();
	}

	public void move(Position targetPosition, Position destination) throws SQLException {
		validateMove(targetPosition, destination);

		board.movePiece(targetPosition, destination);
		nextTurn();
	}

	private void validateMove(Position targetPosition, Position destination) throws SQLException {
		Piece target = board.findPieceBy(targetPosition);
		validateTurn(target);
		validateMovablePosition(target, targetPosition, destination);
	}

	private void validateTurn(Piece target) throws SQLException {
		if (target.isNotSameColor(currentTurn.findTurn().getColor())) {
			throw new IllegalArgumentException(TURN_MISS_MATCH_MESSAGE);
		}
	}

	private void validateMovablePosition(Piece target, Position targetPosition, Position destination) throws
		SQLException {
		Set<Position> movablePositions = target.findMovablePositions(targetPosition,
			board.getPieces());
		if (!movablePositions.contains(destination)) {
			throw new IllegalArgumentException(NOT_MOVABLE_MESSAGE);
		}
	}

	private void nextTurn() throws SQLException {
		TurnDto turn = currentTurn.findTurn();
		Color reverse = turn.getColor().reverse();
		currentTurn.editTurn(TurnDto.of(reverse));
	}

	public Map<Color, Double> calculateEachScore() throws SQLException {
		ScoreRule scoreRule = new ScoreRule();
		return scoreRule.calculateScore(board);
	}

	public Color getCurrentTurn() throws SQLException {
		return currentTurn.findTurn().getColor();
	}

	public boolean isKingAlive() throws SQLException {
		return board.isKingAliveOf(currentTurn.findTurn().getColor());
	}

	public Board getBoard() {
		return board;
	}

	public void resetGame() throws SQLException {
		board.deleteAll();
		board = BoardFactory.create();
		this.currentTurn.editTurn(TurnDto.of(WHITE));
	}
}
