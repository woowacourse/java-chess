package controller;

import dao.BoardDao;
import dao.Movement;
import domain.Board;
import domain.Turn;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BoardDao boardDao;

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
            if (command.isYes()) {
                boardDao.save(id);
                return new ChessGame(id, new Board());
            }
            return findChessGame();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return saveGame(id);
        }
    }

    private ChessGame findGame(String id) {
        Board board = new Board();
        List<Movement> movements = boardDao.findStatusById(id);
        Turn turn = Turn.WHITE;
        for (Movement movement : movements) {
            board.move(movement.getStartingPoint(), movement.getDestinationPoint(), turn);
            turn = turn.switchTurn();
        }
        return new ChessGame(id, board);
    }
}
