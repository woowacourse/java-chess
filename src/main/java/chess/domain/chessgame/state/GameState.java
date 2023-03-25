package chess.domain.chessgame.state;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;

public interface GameState {

    GameState start();

    void move(final SquareCoordinate from, final SquareCoordinate to);

    GameState end();

    boolean isNotEnd();

    ChessBoard getChessBoard();
}
