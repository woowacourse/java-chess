package domain.board;

import static domain.board.position.File.A;
import static domain.board.position.File.B;
import static domain.board.position.File.C;
import static domain.board.position.File.D;
import static domain.board.position.File.E;
import static domain.board.position.File.F;
import static domain.board.position.File.G;
import static domain.board.position.File.H;
import static domain.board.position.Rank.EIGHT;
import static domain.board.position.Rank.FIVE;
import static domain.board.position.Rank.FOUR;
import static domain.board.position.Rank.ONE;
import static domain.board.position.Rank.SEVEN;
import static domain.board.position.Rank.SIX;
import static domain.board.position.Rank.THREE;
import static domain.board.position.Rank.TWO;
import static domain.piece.Color.BLACK;
import static domain.piece.Color.WHITE;

import domain.board.position.File;
import domain.board.position.Position;
import domain.board.position.Rank;
import domain.piece.Bishop;
import domain.piece.Empty;
import domain.piece.InitPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public enum BoardInitiator {

    WHITE_KING(List.of(E), List.of(ONE), new King(WHITE)),
    WHITE_QUEEN(List.of(D), List.of(ONE), new Queen(WHITE)),
    WHITE_BISHOP(List.of(C, F), List.of(ONE), new Bishop(WHITE)),
    WHITE_KNIGHT(List.of(B, G), List.of(ONE), new Knight(WHITE)),
    WHITE_ROOK(List.of(A, H), List.of(ONE), new Rook(WHITE)),
    WHITE_PAWN(List.of(A, B, C, D, E, F, G, H), List.of(TWO), new InitPawn(WHITE)),

    BLACK_KING(List.of(E), List.of(EIGHT), new King(BLACK)),
    BLACK_QUEEN(List.of(D), List.of(EIGHT), new Queen(BLACK)),
    BLACK_BISHOP(List.of(C, F), List.of(EIGHT), new Bishop(BLACK)),
    BLACK_KNIGHT(List.of(B, G), List.of(EIGHT), new Knight(BLACK)),
    BLACK_ROOK(List.of(A, H), List.of(EIGHT), new Rook(BLACK)),
    BLACK_PAWN(List.of(A, B, C, D, E, F, G, H), List.of(SEVEN), new InitPawn(BLACK)),

    EMPTY(List.of(A, B, C, D, E, F, G, H), List.of(THREE, FOUR, FIVE, SIX), Empty.INSTANCE),
    ;

    private final List<File> files;
    private final List<Rank> rank;
    private final Piece piece;

    BoardInitiator(final List<File> files, final List<Rank> rank, final Piece piece) {
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
            squares.put(position, value.piece);
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

    public static Map<Position, Piece> createEmpty() {
        final LinkedHashMap<Position, Piece> squares = new LinkedHashMap<>();
        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
                squares.put(new Position(File.of(fileIndex), Rank.of(rankIndex)), Empty.INSTANCE);
            }
        }
        return squares;
    }

    @SafeVarargs
    public static Map<Position, Piece> create(final Entry<Position, Piece>... entries) {
        final Map<Position, Piece> squares = createEmpty();
        for (final Entry<Position, Piece> entry : entries) {
            squares.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(squares);
    }
}
