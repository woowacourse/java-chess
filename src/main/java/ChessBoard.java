import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void init() {
        initSide(Side.BLACK);
        initSide(Side.WHITE);
    }

    public void initSide(Side side) { // RNBQKBNR
        String defaultLine = getLine(side);
        board.put(new Position("a", defaultLine), new Rook(side));
        board.put(new Position("b", defaultLine), new Knight(side));
        board.put(new Position("c", defaultLine), new Bishop(side));
        board.put(new Position("d", defaultLine), new Queen(side));
        board.put(new Position("e", defaultLine), new King(side));
        board.put(new Position("f", defaultLine), new Bishop(side));
        board.put(new Position("g", defaultLine), new Knight(side));
        board.put(new Position("h", defaultLine), new Rook(side));
    }

    private static String getLine(Side side) {
        if (side == Side.BLACK) {
            return "8";
        }
        return "1";
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
