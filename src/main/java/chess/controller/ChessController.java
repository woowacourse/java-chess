package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
import chess.domain.CommandLine;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    
    public ChessController() {
    }
    
    public void execute() {
        OutputView.printGameStartMessage();
        ChessGame chessGame = new ChessGame();
        while (!chessGame.isGameEnd()) {
            this.runGame(chessGame);
        }
    }
    
    private void runGame(final ChessGame chessGame) {
        try {
            CommandLine commandLine = this.getCommandLine();
            handleCommandLine(chessGame, commandLine);
            OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
        }
    }
    
    private CommandLine getCommandLine() {
        return new CommandLine(InputView.readCommand());
    }
    
    private void handleCommandLine(final ChessGame chessGame, final CommandLine commandLine) {
        if (commandLine.isStart()) {
            chessGame.start();
        }
        if (commandLine.isMove()) {
            chessGame.move(commandLine.getArguments());
        }
        if (commandLine.isEnd()) {
            chessGame.end();
        }
    }
}
