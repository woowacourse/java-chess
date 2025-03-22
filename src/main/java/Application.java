import chess.Board;
import chess.BoardCreator;
import chess.Position;
import chess.piece.Piece;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<Position, Piece> generatePieces = BoardCreator.generate();
        Board board = new Board(generatePieces);

    }
}
