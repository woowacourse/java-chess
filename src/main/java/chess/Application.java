package chess;

import static chess.view.InputView.requestStatusOrEndInput;
import static chess.view.InputView.requestValidMoveInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameOverInstructions;
import static chess.view.OutputView.printStatus;

import chess.controller.GameController;
import chess.domain.ChessGame;
import chess.dto.BoardDto;

public class Application {

    private static final GameController controller = new GameController();

    public static void main(String[] args) {
        ChessGame game = controller.startGame();
        printBoard(new BoardDto(game));
        while (!game.isEnd()) {
            game.moveChessmen(requestValidMoveInput());
            printBoard(new BoardDto(game));
        }
        printGameOverInstructions();
        while (requestStatusOrEndInput()) {
            printStatus(game.getGameResult());
        }
    }
}
