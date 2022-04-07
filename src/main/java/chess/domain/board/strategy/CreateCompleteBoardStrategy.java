package chess.domain.board.strategy;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

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

    private static final List<PieceType> lineOrder =
            List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

    public CreateCompleteBoardStrategy() {
    }

    @Override
    public Map<Position, Piece> createPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createPawns());
        pieces.putAll(createPiecesWithoutPawn());
        return pieces;
    }

    private Map<Position, Piece> createPawns() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(fillRowWith(Row.SECOND, Color.WHITE));
        pieces.putAll(fillRowWith(Row.SEVENTH, Color.BLACK));
        return pieces;
    }

    private Map<Position, Piece> fillRowWith(Row row, Color color) {
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

    private Map<Position, Piece> createLineOf(Row row, Color color) {
        int columnIndex = 1;
        Map<Position, Piece> line = new HashMap<>();
        for (PieceType pieceType : lineOrder) {
            Position position = new Position(Column.from(columnIndex++), row);
            Piece piece = PieceFactory.createPiece(pieceType, color);
            line.put(position, piece);
        }
        return line;
    }
}
