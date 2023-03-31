package chess.domain.chessGame;

import chess.domain.piece.PlayingCamp;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.util.List;
import java.util.Map;

public interface ChessGame {
    Map<Position, PieceDto> move(String currentPosition, String nextPosition);

    List<Double> calculateScore();

    ChessGame start();

    void end();

    boolean isEnd();

    boolean isReady();

    PlayingCamp getThisTurn();

    Map<Position, PieceDto> getPrintingBoard();
}

