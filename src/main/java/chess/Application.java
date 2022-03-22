package chess;

import static chess.view.InputView.requestStartOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameStartAnnouncement;

import chess.domain.board.Board;
import chess.dto.BoardDto;

public class Application {

    public static void main(String[] args) {
        printGameStartAnnouncement();
        while (requestStartOrEndInput()) {
            Board board = new Board();
            BoardDto dto = new BoardDto(board);
            printBoard(dto);
        }
    }
}
