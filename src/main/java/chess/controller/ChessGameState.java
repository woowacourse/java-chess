package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.controller.command.StartCommand;
import chess.service.ChessGameService;

import java.util.Collections;
import java.util.List;

public final class ChessGameState {
    private static final int COMMAND_INDEX = 0;
    private static final String END = "end";
    
    private final ChessGameService chessGameService;
    private Command command;
    
    public ChessGameState(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
        command = new StartCommand();
    }
    
    public void playWithCurrentTurn(List<String> inputCommand) {
        command = CommandFactory.creaeteCommand(inputCommand.remove(COMMAND_INDEX));
        command.playWithCurrentTurn(inputCommand, chessGameService);
    }
    
    public boolean isRunning() {
        return command.isRunning();
    }
    
    public void noticeKingDead() {
        if (chessGameService.isKingDied()) {
            command = CommandFactory.creaeteCommand(END);
            command.playWithCurrentTurn(Collections.emptyList(), chessGameService);
            chessGameService.initChessGame();
        }
    }
}
