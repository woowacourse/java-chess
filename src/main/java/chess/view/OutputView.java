package chess.view;

import chess.domain.Piece.Piece;
import chess.domain.board.Board;
import chess.domain.position.Position;



public class OutputView {

    private static final int EDGE_START = 1;
    private static final int EDGE_END = 8;

    public static void printInitializedBoard(Board board) {
        for (int y = EDGE_END; EDGE_START <= y; y--) {
            for (int x = EDGE_START; x <= EDGE_END; x++) {
                Position position = Position.of(x,y);
                Piece piece = board.getPiece(position);
                System.out.print(piece);
            }
            System.out.println();
        }


    }

}
