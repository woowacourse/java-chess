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

	Board getBoard();

	default Team getWinner() {
		throw new UnsupportedOperationException("게임이 끝났을때만 승자를 구할수 있어요.");
	}
}
