package chess.domain.chessGame;

import chess.domain.position.Position;
import java.util.Map;

public interface ChessGame {
    ChessGame start();

    ChessGame move(String currentPosition, String nextPosition);

    ChessGame end();

    boolean isPlaying();

    Map<Position, String> getPrintingBoard();
}

