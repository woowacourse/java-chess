package domain.board;

import static domain.piece.info.Color.BLACK;
import static domain.piece.info.Color.WHITE;
import static domain.piece.info.File.*;
import static domain.piece.info.Rank.*;

import domain.piece.Bishop;
import domain.piece.Empty;
import domain.piece.InitPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public enum BoardInitiator {

    WHITE_KING(List.of(E), List.of(ONE), () -> new King(WHITE)),
    WHITE_QUEEN(List.of(D), List.of(ONE), () -> new Queen(WHITE)),
    WHITE_BISHOP(List.of(C, F), List.of(ONE), () -> new Bishop(WHITE)),
    WHITE_KNIGHT(List.of(B, G), List.of(ONE), () -> new Knight(WHITE)),
    WHITE_ROOK(List.of(A, H), List.of(ONE), () -> new Rook(WHITE)),
    WHITE_PAWN(List.of(A, B, C, D, E, F, G, H), List.of(TWO), () -> new InitPawn(WHITE)),

    BLACK_KING(List.of(E), List.of(EIGHT), () -> new King(BLACK)),
    BLACK_QUEEN(List.of(D), List.of(EIGHT), () -> new Queen(BLACK)),
    BLACK_BISHOP(List.of(C, F), List.of(EIGHT), () -> new Bishop(BLACK)),
    BLACK_KNIGHT(List.of(B, G), List.of(EIGHT), () -> new Knight(BLACK)),
    BLACK_ROOK(List.of(A, H), List.of(EIGHT), () -> new Rook(BLACK)),
    BLACK_PAWN(List.of(A, B, C, D, E, F, G, H), List.of(SEVEN), () -> new InitPawn(BLACK)),

    EMPTY(List.of(A, B, C, D, E, F, G, H), List.of(THREE, FOUR, FIVE, SIX), () -> Empty.INSTANCE),
    ;


    private final List<File> files;
    private final List<Rank> rank;
    private final Supplier<Piece> piece;

    BoardInitiator(final List<File> files, final List<Rank> rank, final Supplier<Piece> piece) {
        this.files = files;
        this.rank = rank;
        this.piece = piece;
    }

    public static Map<Position, Piece> init() {
        final Map<Position, Piece> squares = new LinkedHashMap<>();
        for (final BoardInitiator value : values()) {
            setPositionOnSquare(value, squares);
        }

        return squares;
    }

    private static void setPositionOnSquare(final BoardInitiator value, final Map<Position, Piece> squares) {
        final List<Position> positions = makePositions(value.files, value.rank);

        for (final Position position : positions) {
            squares.put(position, value.piece.get());
        }
    }

    private static List<Position> makePositions(final List<File> files, final List<Rank> ranks) {
        final List<Position> positions = new ArrayList<>();
        for (final File file : files) {
            setRankOfPosition(ranks, file, positions);
        }
        return positions;
    }

    private static void setRankOfPosition(final List<Rank> ranks, final File file, final List<Position> positions) {
        for (final Rank rank : ranks) {
            positions.add(new Position(file, rank));
        }
    }
}
