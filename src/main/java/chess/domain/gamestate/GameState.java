package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;

public interface GameState {

    GameState start();

    GameState end();

    GameState move(Point source, Point destination, Team turn);

    GameState status();

    Board board();

    boolean isRunning();

    String winner();
}
