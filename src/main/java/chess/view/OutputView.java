package chess.view;

import chess.File;
import chess.Rank;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    public static void printBoard(final Board board) {
        final Map<Position, Piece> chessBoard = board.getBoard();

        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                final Position position = Position.of(file.value(), rank.value());
                System.out.printf(chessBoard.get(position).name());
            }
            System.out.println();
        }
        System.out.println();
    }
}
