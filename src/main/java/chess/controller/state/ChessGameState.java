package chess.controller.state;

import chess.domain.board.Board;

public interface ChessGameState {
    ChessGameState start();

    ChessGameState move(String from, String to);

    ChessGameState status();

    ChessGameState end();

    Turn getTurn();

    Board getBoard();

    boolean isEnded();
}
