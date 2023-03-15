package chess;

import chess.controller.ChessBoardDto;
import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        ChessBoard chessBoard = ChessBoardFactory.create();
        outputView.printChessBoard(new ChessBoardDto(chessBoard));
    }
}
