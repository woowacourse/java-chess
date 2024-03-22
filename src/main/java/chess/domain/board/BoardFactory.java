package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactory {

    public static final List<BiFunction<PieceColor, Position, Piece>> PIECES_ARRANGEMENT = List.of(
            Rook::new, Knight::new, Bishop::new, Queen::new,
            King::new, Bishop::new, Knight::new, Rook::new);

    private BoardFactory() {
    }

    public static Board createBoard() {
        Set<Piece> board = new HashSet<>();

        board.addAll(createPiecesWithoutPawn(Row.EIGHT, PieceColor.BLACK));
        board.addAll(createPawns(Row.SEVEN, PieceColor.BLACK));
        board.addAll(createPawns(Row.TWO, PieceColor.WHITE));
        board.addAll(createPiecesWithoutPawn(Row.ONE, PieceColor.WHITE));

        return new Board(board);
    }

    private static Set<Piece> createPiecesWithoutPawn(Row row, PieceColor pieceColor) {
        return IntStream.range(0, PIECES_ARRANGEMENT.size())
                .mapToObj(column -> PIECES_ARRANGEMENT.get(column).apply(
                        pieceColor,
                        new Position(row, Column.from(column))))
                .collect(Collectors.toSet());
    }

    private static Set<Piece> createPawns(Row row, PieceColor pieceColor) {
        return Arrays.stream(Column.values())
                .map(column -> new Pawn(pieceColor, new Position(row, column)))
                .collect(Collectors.toSet());
    }
}
