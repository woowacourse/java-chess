package chess.controller;

import chess.CommandFactory;
import chess.command.Command;
import chess.domain.Board;
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
    
    public void run() {
        this.outputView.printGameStartMessage();
        ChessGame chessGame = new ChessGame();
        while (chessGame.isNotEnd()) {
            this.runGame(chessGame);
        }
    }
    
    private void runGame(final ChessGame chessGame) {
        try {
            Command command = this.parseCommand();
            command.execute(chessGame);
            this.printBoard(command, chessGame);
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
        }
    }
    
    private Command parseCommand() {
        List<String> commandLineLiteral = this.inputView.readCommand();
        return CommandFactory.generateCommand(commandLineLiteral);
    }
    
    private void printBoard(Command command, ChessGame chessGame) {
        if (command.isNotEnd()) {
            Board board = chessGame.getBoard();
            String boardString = BoardMapper.map(board);
            this.outputView.printBoard(BoardDTO.create(boardString));
        }
    }
    
}
