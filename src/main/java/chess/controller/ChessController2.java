package chess.controller;

import chess.command.Command2;
import chess.command.CommandFactory2;
import chess.command.CommandType;
import chess.domain.board.PieceProvider;
import chess.domain.game.Game2;
import chess.domain.game.ReadyGame;
import chess.domain.game.Status;
import chess.history.GameHistory;
import chess.history.History2;
import chess.history.MoveHistory2;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController2 {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    private final Map<CommandType, Executor2> executorMap = Map.of(
            CommandType.START, this::executeStartCommand,
            CommandType.MOVE, this::executeMoveCommand,
            CommandType.END, this::executeEndCommand,
            CommandType.STATUS, this::executeQueryCommand
    );
    
    public ChessController2(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public int selectGame(GameHistory gameHistory) {
        // Parse Start Options
        // new
        // continue
        try {
            String input = this.inputView.readStartOption();
            if (input.equals("new")) {
                gameHistory.add();
                return gameHistory.getLastGameID();
            }
            if (input.equals("continue")) {
                List<Integer> gameNumbers = gameHistory.getGameNumbers();
                this.outputView.printPlayableGameNumbers(gameNumbers);
                if (gameNumbers.isEmpty()) {
                    this.outputView.printNoPlayableGame();
                    gameHistory.add();
                    return gameHistory.getLastGameID();
                }
                int gameNumber = this.inputView.readGameNumber();
                if (!gameNumbers.contains(gameNumber)) {
                    throw new IllegalArgumentException("[GAME NUMBER ERROR] 올바른 게임 번호를 입력해주세요.");
                }
                return gameNumber;
            }
            throw new IllegalArgumentException("[START OPTION ERROR] 올바른 옵션을 입력해주세요.");
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
            return this.selectGame(gameHistory);
        }
    }
    
    public void run(int id) {
        this.outputView.printGameStartMessage();
        Game2 chessGame = new ReadyGame();
        History2 moveHistory = MoveHistory2.create(id);
        while (chessGame.isContinued()) {
            chessGame = this.runGame(chessGame, moveHistory);
        }
        if (chessGame.isOver()) {
            moveHistory.clear(id);
        }
    }
    
    private Game2 runGame(final Game2 chessGame, final History2 moveHistory) {
        try {
            Command2 command = this.parseCommand();
            Executor2 executor = this.executorMap.get(command.getType());
            return executor.execute(command, chessGame, moveHistory);
        } catch (Exception e) {
            this.outputView.printError(e.getMessage());
            return chessGame;
        }
    }
    
    private Command2 parseCommand() {
        List<String> commandLineLiteral = this.inputView.readCommand();
        return CommandFactory2.generateCommand(commandLineLiteral);
    }
    
    
    private Game2 executeEndCommand(final Command2 command, final Game2 chessGame, final History2 moveHistory) {
        return command.update(chessGame);
    }
    
    private Game2 executeMoveCommand(final Command2 command, final Game2 chessGame, final History2 moveHistory) {
        Game2 updatedGame = command.update(chessGame);
        command.addHistory(moveHistory);
        this.printBoard(updatedGame);
        return updatedGame;
    }
    
    private Game2 executeStartCommand(final Command2 command, final Game2 chessGame, final History2 moveHistory) {
        Game2 updatedGame = command.update(chessGame);
        moveHistory.apply(updatedGame);
        this.printBoard(updatedGame);
        return updatedGame;
    }
    
    private Game2 executeQueryCommand(final Command2 command, final Game2 chessGame, final History2 moveHistory) {
        Status status = command.query(chessGame);
        this.outputView.printStatus(status);
        return chessGame;
    }
    
    private void printBoard(final Game2 chessGame) {
        PieceProvider board = chessGame.getBoard();
        String boardString = BoardMapper.map(board);
        this.outputView.printBoard(BoardDTO.create(boardString));
    }
    
}
