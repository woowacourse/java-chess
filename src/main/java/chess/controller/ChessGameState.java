package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.service.ChessGameService;

import java.util.List;
import java.util.Objects;

public final class ChessGameState {
    private static final int COMMAND_INDEX = 0;
    private static final String END = "end";
    
    private final ChessGameService chessGameService;
    private Command command;
    
    public ChessGameState(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }
    
    public void playWithCurrentTurn(List<String> inputCommand) {
        command = CommandFactory.creaeteCommand(inputCommand.get(COMMAND_INDEX));
        command.playWithCurrentTurn(inputCommand, chessGameService);
    }
    
    public boolean isRunning() {
        if (Objects.isNull(command)) {
            return true;
        }
        
        return command.isRunning();
    }
    
    public void noticeKingDead() {
        if (chessGameService.isKingDead()) {
            command = CommandFactory.creaeteCommand(END);
        }
    }
}
