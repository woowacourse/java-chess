package domain.board;

import domain.route.Route;
import domain.route.Path;
import domain.route.Pieces;
import domain.square.Square;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private final Map<Square, Piece> board;

    public Board(BoardCreator boardCreator) {
        this(boardCreator.create());
    }

    public Board(Map<Square, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public void init() {
//        Arrays.stream(Side.values()).forEach(this::initSide);
    }

    public boolean hasPiece(Square square) {
        return board.containsKey(square);
    }

    public void move(Square source, Square target) {
        Piece sourcePiece = findPiece(source);
        Route route = Route.create(source, target, this);
        sourcePiece.checkValidMove(source, target, route);

        board.put(source, Empty.instance());
        board.put(target, sourcePiece);
    }

    public Piece findPiece(Square square) {
        return board.get(square);
    }

    public Pieces findPieces(Path path) {
        List<Piece> pieces = board.entrySet().stream()
                .filter(entry -> path.contains(entry.getKey()))
                .map(Entry::getValue)
                .toList();

        return new Pieces(pieces);
    }

    public List<Piece> pieces() {
        return board.values().stream()
                .toList();
    }
}
