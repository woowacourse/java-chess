package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface ChessGameState {
    ChessGameState start();

    void validateMove(String currentPosition, String nextPosition, Piece movingPiece);

    ChessGameState end();

    boolean isPlaying();

    boolean isEnd();

    Map<Position, String> getPrintingBoard(Board board);

    Map<Color, Double> getScores(Board board);

    String getName();
}

