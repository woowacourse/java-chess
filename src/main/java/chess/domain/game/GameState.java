package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.request.Response;

import java.util.List;

public interface GameState {

    GameState start(Board board, Color turnColor);

    GameState finish();

    boolean isRunnable();

    GameState move(List<Point> arguments);

    GameState status();

    Response getResponse();
}
