package chess.controller;

import chess.CommandLine;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    
    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void execute() {
        this.outputView.printGameStartMessage();
        ChessGame chessGame = new ChessGame();
        while (!chessGame.isGameEnd()) {
            this.runGame(chessGame);
        }
    }
    
    private void runGame(final ChessGame chessGame) {
        try {
            CommandLine commandLine = this.getCommandLine();
            this.handleCommandLine(chessGame, commandLine);
            this.outputView.printBoard(BoardDto.create(chessGame.getBoard()));
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
        }
    }
    
    private CommandLine getCommandLine() {
        return new CommandLine(this.inputView.readCommand());
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
