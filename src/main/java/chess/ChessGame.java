package chess;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Board;
import chess.domain.Status;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.domain.state.BoardRepository;
import chess.domain.state.ChessGameState;
import chess.domain.state.Playing;
import chess.domain.state.Score;

public class ChessGame {
	private ChessGameState state;

	public ChessGame(ChessGameState state) {
		this.state = state;
	}

	public void start() {
		state = state.start();
	}

	public void move(Position source, Position target) {
		state = state.move(source, target);
	}

	public void end() {
		state = state.end();
	}

	public Board board() {
		return state.board();
	}

	public boolean isEnd() {
		return state.isEnd();
	}

	public Turn turn() {
		return state.turn();
	}

	public Status status() {
		Score white = state.score(Team.WHITE);
		Score black = state.score(Team.BLACK);
		return new Status(white, black);
	}

	public static ChessGame createGameByMoves(Map<Integer, List<String>> moves) {
		Objects.requireNonNull(moves);

		ChessGame game = new ChessGame(new Playing(BoardRepository.create(), new Turn(Team.WHITE)));
		for (List<String> value : moves.values()) {
			game.move(Position.of(value.get(0)), Position.of(value.get(1)));
		}
		return game;
	}
}
