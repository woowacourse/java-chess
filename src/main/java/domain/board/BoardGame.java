package domain.board;

import java.util.HashMap;
import java.util.Map;

import domain.command.MoveCommand;
import domain.piece.Piece;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BoardGame {
	private Board board;

	public BoardGame(Board board) {
		this.board = board;
	}

	public BoardGame() {
		this(BoardFactory.create());
	}

	public void move(MoveCommand moveCommand, Team turn) {
		Position sourcePosition = moveCommand.getSourcePosition();
		Position targetPosition = moveCommand.getTargetPosition();

		Piece piece = board.findPiece(sourcePosition)
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_SOURCE_POSITION));
		piece.move(targetPosition, turn, board);
	}

	public Map<Team, Double> calculateScore() {
		Map<Team, Double> score = new HashMap<>();
		score.put(Team.WHITE, board.calculateScoreByTeam(Team.WHITE));
		score.put(Team.BLACK, board.calculateScoreByTeam(Team.BLACK));
		return score;
	}

	public boolean isKingAlive() {
		return board.isKingAlive();
	}

	public Board getBoard() {
		return board;
	}
}
