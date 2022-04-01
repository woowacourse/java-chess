package chess.domain.game;

import chess.domain.board.Point;
import chess.dto.Response;

import java.util.List;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();

    GameState move(List<Point> arguments);

    GameState status();

    Response getResponse();
}
