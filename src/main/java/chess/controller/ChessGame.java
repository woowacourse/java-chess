package chess.controller;

import chess.controller.state.ChessGameState;
import chess.controller.state.Ready;
import chess.domain.board.Board;
import chess.dto.MoveDto;

public class ChessGame {
    private ChessGameState chessGameState;

    public void start() {
        chessGameState = new Ready();
        chessGameState = chessGameState.start();
    }

    public void move(MoveDto moveDto) {
        chessGameState = chessGameState.move(moveDto.getFrom(), moveDto.getTo());
    }

    public void status() {
        chessGameState.status();
    }

    public void end() {
        chessGameState = chessGameState.end();
    }

    public Board getBoard() {
        return chessGameState.getBoard();
    }
}
