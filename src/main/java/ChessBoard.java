import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    public ChessBoard(BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String getSymbol(Position position) {
        Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
    }
}
