package chess.domain.state;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public interface GameState {
	GameState start();

	GameState move(Position from, Position to);

	Map<Team, Double> status();

	GameState end();

	boolean isNotFinished();

	default Board getBoard() {
		throw new UnsupportedOperationException();
	}

	default Team getWinner() {
		throw new UnsupportedOperationException();
	}
}
