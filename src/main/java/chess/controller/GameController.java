package chess.controller;

import static chess.util.PieceGeneratorUtil.initAllChessmen;
import static chess.view.InputView.requestValidMoveInput;
import static chess.view.InputView.requestValidStartOrEndInput;
import static chess.view.InputView.requestValidStatusOrEndInput;
import static chess.view.OutputView.print;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameInstructions;
import static chess.view.OutputView.printGameOverInstructions;
import static chess.view.OutputView.printStatus;

import chess.domain.game.ActivePieces;
import chess.domain.game.ChessGame;
import chess.dto.BoardViewDto;

public class GameController {

    private static final int EXIT_STATUS_CODE = 0;

    public ChessGame startGame() {
        printGameInstructions();
        if (!requestValidStartOrEndInput()) {
            System.exit(EXIT_STATUS_CODE);
        }
        return new ChessGame(new ActivePieces(initAllChessmen()));
    }

    public void playGame(ChessGame game) {
        printBoardDisplay(game);
        while (!game.isEnd()) {
            moveChessmen(game);
        }
    }

    private void moveChessmen(ChessGame game) {
        try {
            game.moveChessmen(requestValidMoveInput());
            printBoardDisplay(game);
        } catch (IllegalArgumentException e) {
            print(e.getMessage());
            moveChessmen(game);
        }
    }

    private void printBoardDisplay(ChessGame game) {
        printBoard(new BoardViewDto(game));
    }

    public void endGame(ChessGame game) {
        printGameOverInstructions();
        while (requestValidStatusOrEndInput()) {
            printStatus(game.getGameResult());
        }
    }
}
