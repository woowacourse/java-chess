package chess.view;

import chess.controller.ChessBoardDto;

public class OutputView {

    public void printChessBoard(ChessBoardDto chessBoardDto){
        for (String oneFile : chessBoardDto.getBoard()) {
            System.out.println(oneFile);
        }
    }
}
