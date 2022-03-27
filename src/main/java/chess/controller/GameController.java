package chess.controller;

import static chess.util.PieceGeneratorUtil.initAllChessmen;
import static chess.view.InputView.requestValidMoveInput;
import static chess.view.InputView.requestValidStartOrEndInput;
import static chess.view.InputView.requestValidStatusOrEndInput;
import static chess.view.OutputView.print;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameInstructions;
import static chess.view.OutputView.printGameOverInstructions;

import chess.domain.game.ActivePieces;
import chess.domain.game.Game;
import chess.domain.game.Running;
import chess.dto.BoardViewDto;
import chess.view.OutputView;

public class GameController {

    private static final int EXIT_STATUS_CODE = 0;

    public Game startGame() {
        printGameInstructions();
        if (!requestValidStartOrEndInput()) {
            System.exit(EXIT_STATUS_CODE);
        }
        return new Running(new ActivePieces(initAllChessmen()));
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
            game = game.moveChessmen(requestValidMoveInput());
            printBoardDisplay(game);
            return game;
        } catch (IllegalArgumentException e) {
            print(e.getMessage());
            return moveChessmen(game);
        }
    }

    private void printBoardDisplay(Game game) {
        printBoard(new BoardViewDto(game));
    }

    public void printGameOver(Game game) {
        printGameOverInstructions();
        while (requestValidStatusOrEndInput()) {
            OutputView.printStatus(game.getGameResult());
        }
    }
}
