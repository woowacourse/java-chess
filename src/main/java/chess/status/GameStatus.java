package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.Position;
import chess.chessboard.Side;

public interface GameStatus {

    boolean isMoveValid(ChessBoard chessBoard, Position from, Position to);

    GameStatus nextStatus(ChessBoard chessBoard);

    boolean isGameOver();

    Side getWinner();
}
