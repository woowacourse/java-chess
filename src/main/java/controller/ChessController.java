package controller;

import controller.command.YNCommand;
import dao.BoardDao;
import dao.Movement;
import domain.Board;
import domain.Turn;
import dto.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BoardDao boardDao;

    private final Map<YNCommand, Function<String, ChessGame>> savingGameCommandActions = Map.of(
            YNCommand.YES, this::saveNewGame,
            YNCommand.NO, ignored -> this.findChessGame()
    );

    public ChessController(InputView inputView, OutputView outputView, BoardDao boardDao) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.boardDao = boardDao;
    }

    public ChessGame findChessGame() {
        outputView.printStartingMessage();
        String id = inputView.getGameId();
        try {
            return findGame(id);
        } catch (IllegalArgumentException e) {
            return saveGame(id);
        }
    }

    private ChessGame saveGame(String id) {
        try {
            outputView.printAskingNewGame();
            YNCommand command = inputView.getYNCommand();
            return savingGameCommandActions.get(command).apply(id);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return saveGame(id);
        }
    }

    private ChessGame saveNewGame(String id) {
        boardDao.save(id);
        return new ChessGame(id, new Board());
    }

    private ChessGame findGame(String id) {
        List<Movement> movements = boardDao.findStatusById(id);
        Board board = loadBoard(movements);
        return new ChessGame(id, board);
    }

    private static Board loadBoard(List<Movement> movements) {
        Board board = new Board();
        Turn turn = Turn.WHITE;
        for (Movement movement : movements) {
            board.move(movement, turn);
            turn = turn.switchTurn();
        }
        return board;
    }
}
