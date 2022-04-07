package chess.domain.state;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessBoard;
import chess.domain.Team;

public interface GameState {
    GameState start();

    GameState end();

    GameState move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);

    GameState status();

    Team findWinner();

    boolean isFinished();

    boolean isEnd();

    ChessBoard getChessBoard();
}
