package chess;

import chess.domain.position.Position;

import java.util.Map;

public interface ChessGame {
    Map<Position, String> move(String currentPosition, String nextPosition); // move b1 b2
    ChessGame start();
    void end();
    boolean isEnd();
    Map<Position, String> getBoard();
}

