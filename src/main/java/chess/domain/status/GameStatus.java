package chess.domain.status;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public interface GameStatus {

    void validateMove(ChessBoard chessBoard, Position from, Position to);

    GameStatus nextStatus(ChessBoard chessBoard);

    boolean isGameOver();

    Color getWinner();

    Color getTurn();
}
