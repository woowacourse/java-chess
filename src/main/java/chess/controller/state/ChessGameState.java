package chess.controller.state;

import chess.domain.board.Board;

public interface ChessGameState {
    ChessGameState start();

    ChessGameState move(String from, String to);

    ChessGameState status();

    ChessGameState end();

    Board getBoard();

    boolean isEnded();
}
