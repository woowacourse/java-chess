import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void init() {
        board.put(new Position("a", "8"), Piece.BLACK_ROOK);
        board.put(new Position("h", "8"), Piece.BLACK_ROOK);
        board.put(new Position("a", "1"), Piece.WHITE_ROOK);
        board.put(new Position("h", "1"), Piece.WHITE_ROOK);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
