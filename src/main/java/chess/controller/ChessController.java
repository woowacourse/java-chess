package chess.controller;

import chess.domain.Board;
import chess.dto.BoardDto;
import chess.view.OutputView;

public class ChessController {
    public void start(){
        OutputView outputView = OutputView.getInstance();
        Board board = new Board();

        outputView.printBoard(BoardDto.from(board));

    }

}
