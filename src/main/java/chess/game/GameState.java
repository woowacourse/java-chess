package chess.game;

import chess.board.Point;
import chess.piece.Piece;

import java.util.Map;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();

    Map<Point, Piece> getPointPieces();
}
