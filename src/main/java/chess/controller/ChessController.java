package chess.controller;

import chess.command.Command;
import chess.command.CommandFactory;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Status;
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
            if (command.isStatus()) {
                this.queryCommand(chessGame, command);
                return;
            }
            this.executeCommand(chessGame, command);
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
        }
    }
    
    private void executeCommand(final ChessGame chessGame, final Command command) {
        command.execute(chessGame);
        this.printBoard(command, chessGame);
    }
    
    private void queryCommand(final ChessGame chessGame, final Command command) {
        Status status = command.query(chessGame);
        
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
