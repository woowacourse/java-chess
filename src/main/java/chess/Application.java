package chess;

import static chess.view.InputView.requestStartOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameStartAnnouncement;

import chess.domain.ChessGame;
import chess.dto.BoardDto;

public class Application {

    public static void main(String[] args) {
        printGameStartAnnouncement();
        while (requestStartOrEndInput()) {
            ChessGame game = new ChessGame();
            BoardDto dto = new BoardDto(game);
            printBoard(dto);
        }
    }
}
