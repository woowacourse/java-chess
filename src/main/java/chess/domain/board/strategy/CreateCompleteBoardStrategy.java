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
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateCompleteBoardStrategy implements CreateBoardStrategy {

    private static final TreeMap<Column, PieceType> lineOrder = new TreeMap<>(
            Map.ofEntries(entry(a, ROOK), entry(b, KNIGHT), entry(c, BISHOP),
                    entry(d, QUEEN), entry(e, KING), entry(f, BISHOP),
                    entry(g, KNIGHT), entry(h, ROOK)));

    public CreateCompleteBoardStrategy() {
    }

    @Override
    public Map<Position, Piece> createPieces() {
        final Map<Position, Piece> pieces = new HashMap<>();
        final List<Map.Entry<Row, Color>> rowAndColors = List.of(entry(Row.SECOND, Color.WHITE),
                entry(Row.SEVENTH, Color.BLACK));
        for (Entry<Row, Color> rowAndColor : rowAndColors) {
            pieces.putAll(fillRowWith(rowAndColor));
        }
        pieces.putAll(createPiecesWithoutPawn());
        return pieces;
    }

    private Map<Position, Piece> fillRowWith(final Entry<Row, Color> rowAndColor) {
        final Row row = rowAndColor.getKey();
        final Color color = rowAndColor.getValue();
        return Arrays.stream(Column.values())
                .map(column -> new Position(column, row))
                .collect(Collectors.toMap(Function.identity(), p -> new Pawn(color)));
    }

    private Map<Position, Piece> createPiecesWithoutPawn() {
        return Stream.of(entry(Row.FIRST, Color.WHITE), entry(Row.EIGHTH, Color.BLACK))
                .map(this::createLineOf)
                .flatMap(line -> line.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Position, Piece> createLineOf(final Entry<Row, Color> rowAndColor) {
        final Row row = rowAndColor.getKey();
        final Color color = rowAndColor.getValue();
        return lineOrder.entrySet().stream()
                .collect(Collectors.toMap(entry -> new Position(entry.getKey(), row),
                        entry -> PieceFactory.createPiece(entry.getValue(), color)));
    }

}
