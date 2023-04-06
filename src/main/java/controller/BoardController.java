package controller;

import controller.command.Command;
import dao.BoardDao;
import domain.Board;
import domain.Turn;
import dto.ChessGame;
import exception.GameFinishedException;
import view.InputView;
import view.OutputView;

public class BoardController {
    private final String id;
    private final Board board;
    private final BoardDao boardDao;
    private final OutputView outputView;
    private final InputView inputView;

    public BoardController(ChessGame chessGame, BoardDao boardDao, OutputView outputView, InputView inputView) {
        this.id = chessGame.getId();
        this.board = chessGame.getBoard();
        this.boardDao = boardDao;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void initializeBoard() {
        try {
            outputView.printAskingBootingCommandMessage();
            Command command = inputView.getGameCommand();
            if (command.isStarting()) {
                printBoardStatus();
            }
            if (command.isEnding()) {
                throw new GameFinishedException();
            }
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            initializeBoard();
        }
    }

    public void executeByCommand(Turn turn) {
        try {
            Command command = inputView.getGameCommand();
            command.execute(id, board, boardDao, turn, outputView);
            printBoardStatus();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            executeByCommand(turn);
        }
    }

    private void printBoardStatus() {
        outputView.printStatus(board.findCurrentStatus());
    }

    public void checkmate(Turn turn) {
        outputView.printWinner(turn);
    }
}
