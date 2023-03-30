package chess.domain.chessGame;

import chess.domain.PieceDto;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface ChessGame {
    Map<Position, PieceDto> move(String currentPosition, String nextPosition);

    List<Double> calculateScore();

    ChessGame start();

    void end();

    boolean isEnd();

    boolean isReady();

    Color getThisTurn();

    Map<Position, PieceDto> getPrintingBoard();
}

