package domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(new LinkedHashMap<>());
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
        List<Rank> ranks = initPosition.getHorizontals();
        File file = initPosition.vertical(side);
        ranks.forEach(horizontal -> board.put(new Position(horizontal, file), pieceSupplier.get()));
    }

    public boolean hasPiece(Position position) {
        return board.containsKey(position);
    }

    public Piece piece(Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
