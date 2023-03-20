package chess.domain.chessGame;

import chess.domain.position.Position;
import java.util.Map;

public interface ChessGame {
    Map<Position, String> move(String currentPosition, String nextPosition);

    ChessGame start();

    void end();
    boolean isPlaying();

    Map<Position, String> getPrintingBoard();
}

