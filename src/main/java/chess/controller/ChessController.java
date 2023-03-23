package chess.controller;

import chess.command.Command;
import chess.command.CommandFactory;
import chess.command.CommandType;
import chess.command.QueryCommand;
import chess.command.UpdateCommand;
import chess.domain.board.PieceProvider;
import chess.domain.game.ChessGame;
import chess.domain.game.Status;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    private final Map<CommandType, Executor> executorMap = Map.of(
            CommandType.START, this::executeCommand,
            CommandType.MOVE, this::executeCommand,
            CommandType.END, this::executeEndCommand,
            CommandType.STATUS, this::executeQueryCommand
    );
    
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
            Executor executor = this.executorMap.get(command.getType());
            executor.execute(command, chessGame);
            
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
        }
    }
    
    private Command parseCommand() {
        List<String> commandLineLiteral = this.inputView.readCommand();
        return CommandFactory.generateCommand(commandLineLiteral);
    }
    
    
    private void executeEndCommand(final UpdateCommand command, final ChessGame chessGame) {
        command.update(chessGame);
    }
    
    private void executeCommand(final UpdateCommand command, final ChessGame chessGame) {
        command.update(chessGame);
        this.printBoard(chessGame);
    }
    
    private void executeQueryCommand(final QueryCommand command, final ChessGame chessGame) {
        Status status = command.query(chessGame);
        this.outputView.printStatus(status);
    }
    
    private void printBoard(final ChessGame chessGame) {
        PieceProvider board = chessGame.getBoard();
        String boardString = BoardMapper.map(board);
        this.outputView.printBoard(BoardDTO.create(boardString));
    }
    
}
