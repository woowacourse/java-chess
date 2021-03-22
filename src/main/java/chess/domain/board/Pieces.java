package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Point;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.state.State;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Pieces {

    private static final int MAX_INDEX = 7;
    private static final int MIN_INDEX = 0;
    private static final int BLACK_GENERAL_ROW = 7;
    private static final int BLACK_PAWN_ROW = 6;
    private static final int WHITE_PAWN_ROW = 1;
    private static final int WHITE_GENERAL_ROW = 0;
    private static final int MAX_BLANK_ROW = 5;
    private static final int MIN_BLANK_ROW = 1;
    private static final int DUPLICATE_COUNT = 2;
    private static final int TOTAL_KING_SIZE = 2;
    private static final double DUPLICATE_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> pieces = new LinkedHashMap<>();

    public void init() {
        initGeneral(BLACK_GENERAL_ROW, Color.BLACK);
        initPawn(BLACK_PAWN_ROW, Color.BLACK);
        for (int x = MAX_BLANK_ROW; x > MIN_BLANK_ROW; x--) {
            initBlank(x);
        }
        initPawn(WHITE_PAWN_ROW, Color.WHITE);
        initGeneral(WHITE_GENERAL_ROW, Color.WHITE);
    }

    private void initGeneral(final int row, final Color color) {
        pieces.put(Position.of(row, 0), new Rook(color, Position.of(row, 0)));
        pieces.put(Position.of(row, 1), new Knight(color, Position.of(row, 1)));
        pieces.put(Position.of(row, 2), new Bishop(color, Position.of(row, 2)));
        pieces.put(Position.of(row, 3), new Queen(color, Position.of(row, 3)));
        pieces.put(Position.of(row, 4), new King(color, Position.of(row, 4)));
        pieces.put(Position.of(row, 5), new Bishop(color, Position.of(row, 5)));
        pieces.put(Position.of(row, 6), new Knight(color, Position.of(row, 6)));
        pieces.put(Position.of(row, 7), new Rook(color, Position.of(row, 7)));
    }

    private void initPawn(final int row, final Color color) {
        for (int y = 0; y <= MAX_INDEX; y++) {
            pieces.put(Position.of(row, y), new Pawn(color, Position.of(row, y)));
        }
    }

    private void initBlank(final int x) {
        for (int y = MIN_INDEX; y <= MAX_INDEX; y++) {
            pieces.put(Position.of(x, y), new Blank());
        }
    }

    public void movePiece(final Position sourcePosition, final Position targetPosition, final State state) {
        Piece sourcePiece = pieces.get(sourcePosition);
        Piece targetPiece = pieces.get(targetPosition);

        validateSourcePiece(sourcePiece, state);
        validateAttackPiece(sourcePiece, targetPiece);

        pieces.put(targetPosition, sourcePiece.move(targetPosition, pieces()));
        pieces.put(sourcePosition, new Blank());
    }

    private void validateSourcePiece(final Piece sourcePiece, final State state) {
        if (!state.isSameColor(sourcePiece)) {
            throw new IllegalArgumentException("움직이려 하는 기물은 상대방의 기물입니다.");
        }
    }

    private void validateAttackPiece(final Piece sourcePiece, final Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("공격하려는 기물은 자신의 기물입니다.");
        }
    }

    public double score(final Color color) {
        double score = pieces.values()
            .stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::score)
            .sum();

        return score - duplicatePawn(color) * DUPLICATE_PAWN_SCORE;
    }

    private long duplicatePawn(final Color color) {
        long count = 0;
        List<Piece> pawns = pieces.values()
            .stream()
            .filter(piece -> piece.isSameColor(color))
            .filter(piece -> piece instanceof Pawn)
            .collect(Collectors.toList());

        for (int column = MIN_INDEX; column <= MAX_INDEX; column++) {
            Point point = Point.from(column);
            long pawnCount = pawns.stream()
                .filter(piece -> piece.isSameColumn(point))
                .count();
            count += duplicateCount(pawnCount);
        }
        return count;
    }

    private long duplicateCount(final long pawnCount) {
        if (pawnCount >= DUPLICATE_COUNT) {
            return pawnCount;
        }
        return 0;
    }

    public Map<Position, Piece> pieces() {
        return new LinkedHashMap<>(pieces);
    }

    public boolean isKillKing() {
        long kingCount = pieces.values()
            .stream()
            .filter(piece -> piece instanceof King)
            .count();

        return kingCount != TOTAL_KING_SIZE;
    }
}
