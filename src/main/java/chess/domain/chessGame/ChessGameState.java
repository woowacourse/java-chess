package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public interface ChessGameState { // TODO chessGame이 State를 변수로 가지고있게 리팩토링 필요
    ChessGameState start();

    void validateMove(String currentPosition, String nextPosition, Piece movingPiece);

    ChessGameState end();

    boolean isPlaying();

    Map<Position, String> getPrintingBoard(Board board);
    Map<Color, Double> getScores(Board board);
}

