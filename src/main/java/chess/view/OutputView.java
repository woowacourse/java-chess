package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.Position;

import java.util.LinkedHashMap;


public class OutputView {

    private static final int COLUMN_SIZE = 8;

    public static void printInitializedBoard(Board board) {
        LinkedHashMap<Position, Piece> initializedBoard = board.getBoard();
        int count = 0;
        for (Position position : initializedBoard.keySet()) {
            System.out.print(initializedBoard.get(position));
            count += 1;
            if (count % COLUMN_SIZE == 0) {
                System.out.println();
            }
        }

    }

}
