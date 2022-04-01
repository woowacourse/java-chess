package chess.domain.board.strategy;

import static chess.domain.board.Column.a;
import static chess.domain.board.Column.b;
import static chess.domain.board.Column.c;
import static chess.domain.board.Column.d;
import static chess.domain.board.Column.e;
import static chess.domain.board.Column.f;
import static chess.domain.board.Column.g;
import static chess.domain.board.Column.h;
import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static java.util.Map.Entry;
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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateCompleteBoardStrategy implements CreateBoardStrategy {

    private static final List<Entry<Column, PieceType>> lineOrder =
            List.of(entry(a, ROOK), entry(b, KNIGHT), entry(c, BISHOP),
                    entry(d, QUEEN), entry(e, KING), entry(f, BISHOP),
                    entry(g, KNIGHT), entry(h, ROOK));

    public CreateCompleteBoardStrategy() {
    }

    @Override
    public Map<Position, Piece> createPieces() {
        final Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createPawns());
        pieces.putAll(createPiecesWithoutPawn());
        return pieces;
    }

    private Map<Position, Piece> createPawns() {
        final Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(fillRowWith(Row.SECOND, Color.WHITE));
        pieces.putAll(fillRowWith(Row.SEVENTH, Color.BLACK));
        return pieces;
    }

    private Map<Position, Piece> fillRowWith(final Row row, final Color color) {
        return Arrays.stream(Column.values())
                .map(column -> new Position(column, row))
                .collect(Collectors.toMap(Function.identity(), p -> new Pawn(color)));
    }

    private Map<Position, Piece> createPiecesWithoutPawn() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createLineOf(Row.FIRST, Color.WHITE));
        pieces.putAll(createLineOf(Row.EIGHTH, Color.BLACK));
        return pieces;
    }

    private Map<Position, Piece> createLineOf(final Row row, final Color color) {
        return lineOrder.stream()
                .collect(Collectors.toMap(entry -> new Position(entry.getKey(), row),
                        entry -> PieceFactory.createPiece(entry.getValue(), color)));
    }
}
