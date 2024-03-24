package domain;

import domain.piece.Empty;
import domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(ChessBoardCreator chessBoardCreator) {
        this(chessBoardCreator.create());
    }

    public ChessBoard(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public void init() {
//        Arrays.stream(Side.values()).forEach(this::initSide);
    }

    public boolean hasPiece(Position position) {
        return board.containsKey(position);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        MovePath movePath = MovePath.create(source, target, this);
        sourcePiece.checkValidMove(source, target, movePath);

        board.put(source, Empty.instance());
        board.put(target, sourcePiece);
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }

    public Pieces findPieces(Path path) {
        List<Piece> pieces = board.entrySet().stream()
                .filter(entry -> path.contains(entry.getKey()))
                .map(Entry::getValue)
                .toList();

        return new Pieces(pieces);
    }
}
