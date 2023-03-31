package chess.model.domain.board;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import chess.model.domain.piece.Piece;
import chess.model.domain.piece.PieceType;
import chess.model.domain.position.Position;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class Score {

    private static final Map<PieceType, Score> PIECE_SCORE_MAPPER;

    private static final int DUPLICATE_SAME_FILE_PAWN_LIMIT = 2;
    private static final Score ZERO = new Score(0);
    private static final double PAWN_DECREASE = 0.5;

    static {
        final Map<PieceType, Score> pieceScoreMapper = new EnumMap<>(PieceType.class);
        pieceScoreMapper.put(PieceType.PAWN, new Score(1));
        pieceScoreMapper.put(PieceType.KNIGHT, new Score(2.5));
        pieceScoreMapper.put(PieceType.BISHOP, new Score(3));
        pieceScoreMapper.put(PieceType.ROOK, new Score(5));
        pieceScoreMapper.put(PieceType.QUEEN, new Score(9));
        pieceScoreMapper.put(PieceType.KING, new Score(0));
        PIECE_SCORE_MAPPER = Collections.unmodifiableMap(pieceScoreMapper);
    }

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public static Score calculateScore(final Map<Position, Piece> board) {
        final Score subtrahend = new Score(sumPawnCountInSameFileOver2(board) * PAWN_DECREASE);
        return board.values()
                .stream()
                .map(piece -> PIECE_SCORE_MAPPER.get(piece.getPieceType()))
                .reduce(ZERO, Score::sum)
                .subtract(subtrahend);
    }

    private static long sumPawnCountInSameFileOver2(final Map<Position, Piece> board) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getPieceType().isPawn())
                .collect(groupingBy(entry -> entry.getKey().file(), counting()))
                .values()
                .stream()
                .filter(count -> count >= Score.DUPLICATE_SAME_FILE_PAWN_LIMIT)
                .reduce(0L, Long::sum);
    }

    public Score sum(final Score addition) {
        return new Score(addition.value + this.value);
    }

    public Score subtract(final Score subtrahend) {
        return new Score(this.value - subtrahend.value);
    }

    public Score multiply(final double value) {
        return new Score(this.value * value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public double getValue() {
        return value;
    }
}
