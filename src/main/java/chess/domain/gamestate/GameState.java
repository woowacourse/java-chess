package chess.domain.gamestate;

import chess.domain.board.Point;
import chess.domain.chessgame.Turn;

public interface GameState {

    GameState start();

    GameState move(Point source, Point destination, Turn turn);

    GameState end();

    GameState status();

    boolean isFinished();
}
