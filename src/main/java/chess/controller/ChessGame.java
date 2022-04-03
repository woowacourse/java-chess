package chess.controller;

import chess.controller.state.ChessGameState;
import chess.controller.state.Ready;
import chess.controller.state.Turn;
import chess.domain.board.Board;
import chess.dto.MoveDto;
import chess.dto.ScoreDto;

public class ChessGame {
    private ChessGameState chessGameState;

    public void start() {
        chessGameState = new Ready().start();
    }

    public void move(MoveDto moveDto) {
        chessGameState = chessGameState.move(moveDto.getFrom(), moveDto.getTo());
    }

    public ScoreDto status() {
        return chessGameState.status();
    }

    public void end() {
        chessGameState = chessGameState.end();
    }

    public boolean isEnded(){
        return chessGameState.isEnded();
    }

    public Turn getTurn() {
        return chessGameState.getTurn();
    }

    public Board getBoard() {
        return chessGameState.getBoard();
    }
}
