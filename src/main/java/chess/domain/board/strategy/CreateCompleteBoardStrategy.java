package chess.domain.board.strategy;

import static chess.domain.board.Column.A;
import static chess.domain.board.Column.B;
import static chess.domain.board.Column.C;
import static chess.domain.board.Column.D;
import static chess.domain.board.Column.E;
import static chess.domain.board.Column.F;
import static chess.domain.board.Column.G;
import static chess.domain.board.Column.H;
import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static java.util.Map.entry;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.factory.PieceFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateCompleteBoardStrategy implements CreateBoardStrategy {

    private static final TreeMap<Column, PieceType> lineOrder = new TreeMap<>(
            Map.ofEntries(entry(A, ROOK), entry(B, KNIGHT), entry(C, BISHOP),
                    entry(D, QUEEN), entry(E, KING), entry(F, BISHOP),
                    entry(G, KNIGHT), entry(H, ROOK)));

    public CreateCompleteBoardStrategy() {
    }

    @Override
    public Map<Position, Piece> createPieces() {
        final Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createPiecesWithPawn());
        pieces.putAll(createPiecesWithoutPawn());
        return pieces;
    }

    public Map<Position, Piece> createPiecesWithPawn() {
        final Map<Position, Piece> pieces = new HashMap<>(fillRowWith(Row.SECOND, Color.WHITE));
        pieces.putAll(fillRowWith(Row.SEVENTH, Color.BLACK));
        return pieces;
    }

    private Map<Position, Piece> fillRowWith(final Row row, final Color color) {
        return Arrays.stream(Column.values())
                .map(column -> new Position(column, row))
                .collect(Collectors.toMap(Function.identity(), p -> new Pawn(color)));
    }

    private Map<Position, Piece> createPiecesWithoutPawn() {
        final Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createLineOf(Row.FIRST, Color.WHITE));
        pieces.putAll(createLineOf(Row.EIGHTH, Color.BLACK));
        return pieces;
    }

    private Map<Position, Piece> createLineOf(final Row row, final Color color) {
        return lineOrder.entrySet().stream()
                .collect(Collectors.toMap(entry -> new Position(entry.getKey(), row),
                        entry -> PieceFactory.createPiece(entry.getValue(), color)));
    }

}
