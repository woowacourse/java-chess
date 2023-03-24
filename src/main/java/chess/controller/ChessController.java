package chess.controller;

import chess.command.Command;
import chess.command.CommandFactory;
import chess.command.CommandType;
import chess.command.MoveCommand;
import chess.command.QueryCommand;
import chess.command.UpdateCommand;
import chess.domain.board.PieceProvider;
import chess.domain.game.ChessGame;
import chess.domain.game.Game;
import chess.domain.game.Status;
import chess.history.MoveHistory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {
    
    private final InputView inputView;
    private final OutputView outputView;
    private final MoveHistory history;
    
    private final Map<CommandType, Executor> executorMap = Map.of(
            CommandType.START, this::executeStartCommand,
            CommandType.MOVE, this::executeMoveCommand,
            CommandType.END, this::executeEndCommand,
            CommandType.STATUS, this::executeQueryCommand
    );
    
    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.history = new MoveHistory();
    }
    
    public void run() {
        this.outputView.printGameStartMessage();
        Game chessGame = new ChessGame();
        while (chessGame.isContinued()) {
            this.runGame(chessGame);
        }
        if (chessGame.isOver()) {
            this.history.reset();
        }
    }
    
    private void runGame(final Game chessGame) {
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
    
    
    private void executeEndCommand(final UpdateCommand command, final Game chessGame) {
        command.update(chessGame);
    }
    
    private void executeMoveCommand(final UpdateCommand command, final Game chessGame) {
        command.update(chessGame);
        this.history.add((MoveCommand) command);
        this.printBoard(chessGame);
    }
    
    private void executeStartCommand(final UpdateCommand command, final Game chessGame) {
        command.update(chessGame);
        this.history.getCommands().forEach(c -> c.update(chessGame));
        this.printBoard(chessGame);
    }
    
    private void executeQueryCommand(final QueryCommand command, final Game chessGame) {
        Status status = command.query(chessGame);
        this.outputView.printStatus(status);
    }
    
    private void printBoard(final Game chessGame) {
        PieceProvider board = chessGame.getBoard();
        String boardString = BoardMapper.map(board);
        this.outputView.printBoard(BoardDTO.create(boardString));
    }
    
}
