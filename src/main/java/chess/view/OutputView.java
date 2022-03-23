package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Optional;

public class OutputView {

    private OutputView() {
    }

    public static void printBoard(final Board board) {
        for (int row : Row.valuesByDescending()) {
            for (Column column : Column.values()) {
                Position position = Position.of(column.name() + row);
                printSymbol(board, position);
            }
            System.out.println();
        }
    }

    private static void printSymbol(Board board, Position position) {
        Optional<Piece> wrappedPiece = board.piece(position);
        if (wrappedPiece.isPresent()) {
            System.out.print(wrappedPiece.get().symbol());
            return;
        }
        System.out.print(".");
    }


}
