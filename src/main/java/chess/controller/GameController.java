package chess.controller;

import static chess.view.OutputView.print;

import chess.domain.game.Game;
import chess.domain.game.GameResult;
import chess.domain.game.NewGame;
import chess.dto.BoardViewDto;
import chess.dto.MoveCommandDto;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {

    private static final int EXIT_STATUS_CODE = 0;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public Game startGame() {
        outputView.printGameInstructions();
        if (!inputView.requestValidStartOrEndInput()) {
            System.exit(EXIT_STATUS_CODE);
        }
        return new NewGame().init();
    }

    public Game playGame(Game game) {
        printBoardDisplay(game);
        while (!game.isEnd()) {
            game = moveChessmen(game);
        }
        return game;
    }

    private Game moveChessmen(Game game) {
        try {
            MoveCommandDto validMoveInput = inputView.requestValidMoveInput();
            game = game.moveChessmen(validMoveInput);
            printBoardDisplay(game);
            return game;
        } catch (IllegalArgumentException e) {
            print(e.getMessage());
            return moveChessmen(game);
        }
    }

    private void printBoardDisplay(Game game) {
        BoardViewDto boardView = game.boardView();
        outputView.printBoard(boardView);
    }

    public void printGameOver(Game game) {
        outputView.printGameOverInstructions();
        while (inputView.requestValidStatusOrEndInput()) {
            GameResult gameResult = game.getResult();
            outputView.printStatus(gameResult);
        }
    }
}
