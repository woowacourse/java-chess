package chess.domain.chessGame;

import chess.domain.position.Position;

import java.util.Map;

public interface ChessGameState {
    Map<Position, String> move(String currentPosition, String nextPosition);

    ChessGameState start();

    void end();

    boolean isEnd();

    Map<Position, String> getPrintingBoard();
}

