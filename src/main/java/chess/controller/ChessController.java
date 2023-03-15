package chess.controller;

import chess.CommandLine;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void execute() {
        outputView.printGameStartMessage();
        ChessGame chessGame = new ChessGame();
        while (true) {
            List<String> tokens = inputView.readCommand();
            CommandLine commandLine = new CommandLine(tokens);
            if (commandLine.getCommand().equals("start")) {
                chessGame.start();
            }
            if (commandLine.getCommand().equals("end")) {
                break;
            }
            outputView.printBoard(BoardDto.create(chessGame));
        }
    }
}
