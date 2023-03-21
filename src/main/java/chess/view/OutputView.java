package chess.view;

import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.renderer.PieceOutputRenderer;

public class OutputView {

    public void printChessBoard(Chessboard chessboard) {
        System.out.println();
        for (Rank rank : Rank.values()) {
            printRankAt(chessboard, rank);
        }
    }

    private void printRankAt(Chessboard chessboard, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            Piece piece = chessboard.getPieceAt(new Square(file, rank));
            stringBuilder.append(PieceOutputRenderer.getPieceName(piece));
        }
        System.out.println(stringBuilder);
    }
}
