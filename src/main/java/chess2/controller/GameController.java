package chess2.controller;

import static chess.view.OutputView.print;

import chess2.domain2.game2.Game;
import chess2.domain2.game2.GameResult;
import chess2.domain2.game2.NewGame;
import chess2.dto2.BoardViewDto;
import chess2.dto2.MoveCommandDto;
import chess2.view2.InputView;
import chess2.view2.OutputView;

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
            GameResult gameResult = game.result();
            outputView.printStatus(gameResult);
        }
    }
}
