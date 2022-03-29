package controller;

import static domain.classification.InputCase.*;
import static view.InputView.*;

import domain.ChessBoard;
import domain.ChessBoardGenerator;
import domain.classification.InputCase;
import domain.dto.StatusDto;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public final class ChessController {

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        InputCase input = interactPlayStart();

        playStart(chessBoard, input);
    }

    private InputCase interactPlayStart() {
        try {
            return InputView.responseUserStartCommand();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return interactPlayStart();
        }
    }

    private void playStart(ChessBoard chessBoard, final InputCase input) {
        if (input.equals(START)) {
            OutputView.printBoard(chessBoard);
            interactPlayTurn(chessBoard);
        }
    }

    private void interactPlayTurn(ChessBoard chessBoard) {
        try {
            playTurn(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            interactPlayTurn(chessBoard);
        }
    }

    private void playTurn(ChessBoard chessBoard) {
        final InputCase input = responseUserCommand();
        if (input.equals(END)) {
            return;
        }
        playStatus(input, chessBoard);
        playMove(input, chessBoard);
        if (checkExistOneKing(chessBoard)) {
            return;
        }
        playTurn(chessBoard);
    }

    private void playStatus(final InputCase input, ChessBoard chessBoard) {
        if (!input.equals(STATUS)) {
            return;
        }
        OutputView.printStatus(new StatusDto(chessBoard));
        playTurn(chessBoard);
    }

    private void playMove(final InputCase input, ChessBoard chessBoard) {
        if (!input.equals(MOVE)){
            return;
        }
        Position source = InputView.responseSource();
        Position target = InputView.responseTarget();
        chessBoard.move(source, target);
        OutputView.printBoard(chessBoard);
    }

    private boolean checkExistOneKing(ChessBoard chessBoard) {
        if (!chessBoard.checkKingExist()) {
            OutputView.printWinner(chessBoard.calculateWhoWinner());
            return true;
        }
        return false;
    }
}
