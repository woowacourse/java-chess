import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String getSymbol(final Position position) {
        Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
    }
}
