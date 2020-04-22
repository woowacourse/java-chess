package chess.domain.state;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;

// 구현한 애들-board + turn()
public interface ChessGameState {

	boolean isEnd();

	ChessGameState start();

	ChessGameState move(Position source, Position target);

	ChessGameState end();

	Board board();

	Score score(Team team);

	Turn turn();
}
