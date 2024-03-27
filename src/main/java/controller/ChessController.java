package controller;

import controller.command.Command;
import controller.command.EndOnCommand;
import db.DBService;
import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.dto.TurnDto;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        DBService dbService = new DBService();
        ChessBoard board = createChessBoard(dbService);

        outputView.printGameGuideMessage();
        Command command = readStartCommandUntilValid();
        while (command.isNotEnded()) {
            command.execute(board, outputView);
            command = readNextCommand(board);
        }
        command.execute(board, outputView);
        updateGameStatus(dbService, board);
    }

    private ChessBoard createChessBoard(DBService dbService) {
        if (dbService.isPreviousDataExist()) {
            TurnDto turnDto = dbService.loadPreviousTurn();
            return ChessBoardFactory.loadPreviousChessBoard(dbService.loadPreviousData(), turnDto.getTurn());
        }
        return ChessBoardFactory.createInitialChessBoard();
    }

    private Command readStartCommandUntilValid() {
        try {
            return inputView.readStartCommand();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return readStartCommandUntilValid();
        }
    }

    private Command readNextCommand(final ChessBoard board) {
        if (board.isKingNotExist()) {
            return new EndOnCommand();
        }
        return readCommandUntilValid();
    }

    private Command readCommandUntilValid() {
        try {
            return inputView.readCommand();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return readCommandUntilValid();
        }
    }

    private void updateGameStatus(final DBService dbService, final ChessBoard board) {
        if (board.isKingNotExist()) {
            dbService.deletePreviousData();
            return;
        }
        dbService.updatePiece(board.getPieces());
        dbService.updateTurn(board.getTurn());
    }
}
