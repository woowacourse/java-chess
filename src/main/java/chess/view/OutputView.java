package chess.view;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;

import java.util.List;

public class OutputView {

    public void printChessBoard(final ChessBoard chessBoard) {
        List<Piece> pieces = chessBoard.findAllPieces();
        String chessBoardExpression = ChessBoardExpression.toExpression(pieces);
        System.out.println(chessBoardExpression);
    }
}
