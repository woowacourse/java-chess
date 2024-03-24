package controller;

import domain.game.*;
import dto.BoardDto;
import dto.MoveRequestDto;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public GameController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        GameCommand gameCommand = inputGameStartCommand();
        if (gameCommand.isStartCommand()) {
            gameStart();
        }
    }

    private GameCommand inputGameStartCommand() {
        try {
            return inputView.inputGameStartCommand();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputGameStartCommand();
        }
    }

    private void gameStart() {
        outputView.printWelcomeMessage();
        boolean isContinuable = true;

        while (isContinuable) {
            BoardDto boardDto = BoardDto.from(chessGame.piecePositions());
            outputView.printBoard(boardDto);

            isContinuable = playTurn();
        }
    }

    private MoveRequestDto inputMoveRequest() {
        try {
            return inputView.inputMoveRequest();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());

            return inputMoveRequest();
        }
    }

    private boolean playTurn() {
        MoveRequestDto moveRequest = inputMoveRequest();
        if (moveRequest.isEndCommand()) {
            return false;
        }

        try {
            chessGame.play(moveRequest.sourcePosition(), moveRequest.destinationPosition());
            return true;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());

            return playTurn();
        }
    }
}
