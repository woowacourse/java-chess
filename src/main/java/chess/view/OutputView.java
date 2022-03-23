package chess.view;

import chess.domain.board.Board;
import chess.view.dto.BoardDto;

public class OutputView {
    public static void printBoard(Board board) {
        BoardDto boardDto = new BoardDto(board);
        System.out.println(boardDto.getBoardText());
    }
}
