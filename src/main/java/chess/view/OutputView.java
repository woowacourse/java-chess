package chess.view;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public class OutputView {

    public static void printBoard(final Board board) {
        final Map<Position, Piece> chessBoard = board.getBoard();

        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                final Position position = Position.of(file, rank);
                System.out.printf(chessBoard.get(position).name());
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
