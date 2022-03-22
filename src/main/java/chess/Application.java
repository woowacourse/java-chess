package chess;

import static chess.view.OutputView.printBoard;

import chess.domain.board.Board;
import chess.dto.BoardDto;

public class Application {

    public static void main(String[] args) {
        Board board = new Board();
        BoardDto dto = new BoardDto(board);
        printBoard(dto);
    }
}
