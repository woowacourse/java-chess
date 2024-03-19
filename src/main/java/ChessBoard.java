import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void init() {
        Arrays.stream(Side.values()).forEach(this::initSide);
    }

    private void initSide(Side side) {
        initPiece(InitPosition.ROOK, side, () -> new Rook(side));
        initPiece(InitPosition.KNIGHT, side, () -> new Knight(side));
        initPiece(InitPosition.BISHOP, side, () -> new Bishop(side));
        initPiece(InitPosition.QUEEN, side, () -> new Queen(side));
        initPiece(InitPosition.KING, side, () -> new King(side));
        initPiece(InitPosition.PAWN, side, () -> new Pawn(side));
    }

    private void initPiece(InitPosition initPosition, Side side, Supplier<Piece> pieceSupplier) {
        List<Horizontal> horizontals = initPosition.getHorizontals();
        Vertical vertical = initPosition.vertical(side);
        horizontals.forEach(horizontal -> board.put(new Position(horizontal, vertical), pieceSupplier.get()));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
