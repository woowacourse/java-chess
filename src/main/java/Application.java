import chess.Board;
import chess.BoardCreator;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Map;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {
        Map<Position, Piece> generatePieces = BoardCreator.generate();
        Board board = new Board(generatePieces);

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Optional<Piece> piece = board.getPiece(column, row);
                if (piece.isEmpty()) {
                    System.out.print("-");
                } else {
                    System.out.print(piece.get());
                }

            }
            System.out.println();
        }
    }
}
