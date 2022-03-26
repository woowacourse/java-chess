package chess;

import static chess.view.InputView.requestMoveInput;
import static chess.view.InputView.requestStartOrEndInput;
import static chess.view.InputView.requestStatusOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameInstructions;
import static chess.view.OutputView.printGameOverInstructions;
import static chess.view.OutputView.printStatus;

import chess.domain.ChessGame;
import chess.domain.piece.piece.ChessmenInitializer;
import chess.dto.BoardDto;

public class Application {

    public static void main(String[] args) {
        printGameInstructions();
        if (!requestStartOrEndInput()) {
            return;
        }

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();

        ChessGame game = ChessGame.of(chessmenInitializer.init());

        printBoard(new BoardDto(game));
        while(!game.isEnd()) {
            game.moveChessmen(requestMoveInput());
            printBoard(new BoardDto(game));
        }
        printGameOverInstructions();
        while(requestStatusOrEndInput()) {
            printStatus(game.calculateGameResult());
        }
    }
}
