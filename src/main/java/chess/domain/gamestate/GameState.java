package chess.domain.gamestate;

import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.Turn;
import java.util.List;

public interface GameState {

    GameState start();

    GameState move(Point source, Point destination, Turn turn);

    GameState end();

    GameState status();

    boolean isFinished();

    Team winner();

    List<Point> movablePoints(Point currentPoint, Turn turn);
}
