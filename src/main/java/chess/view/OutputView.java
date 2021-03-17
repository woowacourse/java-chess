package chess.view;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Piece;

public class OutputView {

    public static final String EMPTY = ".";

    public static void printBoard(Board board) {
        for (Rank rank : Rank.values()) {
            printPiece(board, rank);
            System.out.println();
        }
    }

    private static void printPiece(Board board, Rank rank) {
        for (File file : File.values()) {
            Position position = Position.of(makePositionFormat(rank, file));
            if(!board.containsPosition(position)) {
                System.out.print(EMPTY);
                continue;
            }

            Piece piece = board.pieceAt(position);
            System.out.print(piece.getName());
        }
    }

    private static String makePositionFormat(Rank rank, File file) {
        return file.getFile() + rank.getRank();
    }
}
