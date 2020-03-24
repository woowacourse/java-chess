package chess;

import chess.domain.ChessBoard;
import chess.factory.ChessPieceFactory;
import chess.view.OutputView;

public class Application {
    public static void main(String []args) {
        ChessBoard chessBoard = new ChessBoard(ChessPieceFactory.blackTeamCreate(), ChessPieceFactory.whiteTeamCreate());
        OutputView.printBoard(chessBoard);
    }


}
