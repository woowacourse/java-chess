package chess.domain.game;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();

    Map<Point, Piece> getPointPieces();

    GameState move(List<String> arguments);

    Color getTurnColor();
}
