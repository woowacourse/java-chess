package chess.controller.command;

import chess.service.ChessGameService;

import java.util.List;

public interface Command {
    void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService);
    
    boolean isRunning();
}
