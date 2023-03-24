package chess.domain.chessGame;

import chess.domain.PieceDto;
import chess.domain.position.Position;

import java.util.Map;

public interface ChessGameState {
    Map<Position, PieceDto> move(String currentPosition, String nextPosition);

    ChessGameState start();

    void end();

    boolean isEnd();

    Map<Position, PieceDto> getPrintingBoard();
}

