package chess.domain.chessgame.state;

import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.winningstatus.WinningStatus;

public interface GameState {

    GameState start();

    void move(final SquareCoordinate from, final SquareCoordinate to);

    boolean isKingDead();

    GameState close();

    WinningStatus status();

    boolean isRunning();

    GameState save(RoomName roomName);

    GameState end();

    boolean isNotEnd();

    ChessBoard getChessBoard();
}
