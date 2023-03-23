package chess.controller;

import chess.controller.dto.BoardDto;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.CommandLine;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessController {
    
    public ChessController() {
    }
    
    public void execute() {
        OutputView.printGameStartMessage();
        ChessGame chessGame = new ChessGame();
        while (!chessGame.isStop()) {
            runGame(chessGame);
        }
        if (chessGame.isCatch()) {
            OutputView.printResultWhenKingCatch(chessGame.getTurn().reverse());
            return;
        }
        showStatus(chessGame);
    }
    
    private void runGame(final ChessGame chessGame) {
        try {
            CommandLine commandLine = getCommandLine();
            handleCommandLine(chessGame, commandLine);
            OutputView.printBoard(BoardDto.create(chessGame.getBoard()));
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
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
        if (commandLine.isStatus()) {
            showStatus(chessGame);
        }
        if (commandLine.isEnd()) {
            chessGame.end();
        }
    }

    private void showStatus(ChessGame chessGame) {
        Map<Color, Double> status = chessGame.status();
        OutputView.printStatus(status.get(Color.WHITE), status.get(Color.BLACK));
    }
}
