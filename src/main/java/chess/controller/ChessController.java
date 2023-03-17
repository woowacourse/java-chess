package chess.controller;

import chess.CommandLine;
import chess.domain.ChessGame;
import chess.domain.piece.Color;
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
        this.outputView.printGameStartMessage();
        ChessGame chessGame = new ChessGame();
        Color color = Color.WHITE;
        while (true) {
            try {
                List<String> tokens = this.inputView.readCommand();
                CommandLine commandLine = new CommandLine(tokens);
                if (commandLine.getCommand().equals("start")) {
                    chessGame.start();
                }
                if (commandLine.getCommand().equals("move")) {
                    chessGame.move(commandLine.getArguments(), color);
                    color = Color.reverse(color);
                }
                if (commandLine.getCommand().equals("end")) {
                    chessGame.end();
                    break;
                }
                this.outputView.printBoard(BoardDto.create(chessGame));
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
