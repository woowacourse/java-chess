package chess.controller;

import chess.command.Command;
import chess.command.CommandFactory;
import chess.command.CommandType;
import chess.domain.board.PieceProvider;
import chess.domain.game.Game;
import chess.domain.game.ReadyGame;
import chess.domain.game.Status;
import chess.history.GameHistory;
import chess.history.History;
import chess.history.MoveHistory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    private final Map<CommandType, Executor> executorMap = Map.of(
            CommandType.START, this::executeStartCommand,
            CommandType.MOVE, this::executeMoveCommand,
            CommandType.END, this::executeEndCommand,
            CommandType.STATUS, this::executeQueryCommand
    );
    
    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public int selectGame(GameHistory gameHistory) {
        try {
            String input = this.inputView.readStartOption();
            if (input.equals("new")) {
                return this.generateNewGame(gameHistory);
            }
            if (input.equals("continue")) {
                List<Integer> gameNumbers = gameHistory.getGameNumbers();
                this.outputView.printPlayableGameNumbers(gameNumbers);
                if (gameNumbers.isEmpty()) {
                    this.outputView.printNoPlayableGame();
                    return this.generateNewGame(gameHistory);
                }
                int gameNumber = this.inputView.readGameNumber();
                this.doestNotContainGameNumber(gameNumbers, gameNumber);
                return gameNumber;
            }
            throw new IllegalArgumentException("[OPTION ERROR] 올바른 옵션을 입력해주세요.");
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
            return this.selectGame(gameHistory);
        }
    }
    
    private void doestNotContainGameNumber(final List<Integer> gameNumbers, final int gameNumber) {
        if (!gameNumbers.contains(gameNumber)) {
            throw new IllegalArgumentException("[SELECT ERROR] 올바른 게임 번호를 입력해주세요.");
        }
    }
    
    private int generateNewGame(final GameHistory gameHistory) {
        gameHistory.add();
        return gameHistory.getLastGameID();
    }
    
    public void run(int id) {
        this.outputView.printGameStartMessage();
        Game chessGame = new ReadyGame();
        History moveHistory = MoveHistory.create(id);
        while (chessGame.isContinued()) {
            chessGame = this.runGame(chessGame, moveHistory);
        }
        if (chessGame.isOver()) {
            moveHistory.clear(id);
        }
    }
    
    
    private Game runGame(final Game chessGame, final History moveHistory) {
        try {
            Command command = this.parseCommand();
            Executor executor = this.executorMap.get(command.getType());
            return executor.execute(command, chessGame, moveHistory);
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
            return chessGame;
        }
    }
    
    private Command parseCommand() {
        List<String> commandLineLiteral = this.inputView.readCommand();
        return CommandFactory.generateCommand(commandLineLiteral);
    }
    
    
    private Game executeEndCommand(final Command command, final Game chessGame, final History moveHistory) {
        return command.update(chessGame);
    }
    
    private Game executeMoveCommand(final Command command, final Game chessGame, final History moveHistory) {
        Game updatedGame = command.update(chessGame);
        command.addHistory(moveHistory);
        this.printBoard(updatedGame);
        return updatedGame;
    }
    
    private Game executeStartCommand(final Command command, final Game chessGame, final History moveHistory) {
        Game updatedGame = command.update(chessGame);
        moveHistory.apply(updatedGame);
        this.printBoard(updatedGame);
        return updatedGame;
    }
    
    private Game executeQueryCommand(final Command command, final Game chessGame, final History moveHistory) {
        Status status = command.query(chessGame);
        this.outputView.printStatus(status);
        return chessGame;
    }
    
    private void printBoard(final Game chessGame) {
        PieceProvider board = chessGame.getBoard();
        String boardString = BoardMapper.map(board);
        this.outputView.printBoard(BoardDTO.create(boardString));
    }
    
}
