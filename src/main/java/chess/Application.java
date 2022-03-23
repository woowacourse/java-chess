package chess;

import static chess.view.InputView.requestMoveOrEndInput;
import static chess.view.InputView.requestStartOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameInstructions;

import chess.domain.ChessGame;
import chess.dto.BoardDto;

public class Application {

    public static void main(String[] args) {
        printGameInstructions();
        if (!requestStartOrEndInput()) {
            return;
        }

        ChessGame game = new ChessGame();
        while(!game.isEnd()) {
            printBoard(new BoardDto(game));
            game.moveChessmen(requestMoveOrEndInput());
        }
    }
}
