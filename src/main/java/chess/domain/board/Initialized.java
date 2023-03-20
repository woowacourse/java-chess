package chess.domain.board;

import static chess.domain.piece.PieceType.PAWN;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Initialized extends AbstractBoard {
    private static final int MULTIPLE_PAWN_COUNT = 2;
    private static final double MULTIPLE_PAWN_SCORE = 0.5;

    protected Initialized(final Map<Position, Piece> board, final Color turn) {
        super(board, turn);
    }

    @Override
    public final Board initialize() {
        throw new IllegalStateException("이미 초기화된 보드입니다.");
    }

    @Override
    public final boolean isInitialized() {
        return true;
    }

    @Override
    public final double score(final Color color) {
        return pieceScore(color) - multiplePawnScore(color);
    }

    private double pieceScore(final Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private double multiplePawnScore(final Color color) {
        final Map<File, Long> pawnCount = Arrays.stream(Rank.values())
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.of(rank, file)))
                .filter(position -> board.get(position).isSameColor(color))
                .filter(position -> board.get(position).isSameType(PAWN))
                .collect(Collectors.groupingBy(Position::file, Collectors.counting()));

        return pawnCount.values().stream()
                .filter(value -> value >= MULTIPLE_PAWN_COUNT)
                .mapToDouble(value -> value * MULTIPLE_PAWN_SCORE)
                .sum();
    }

    @Override
    public final Color winner() {
        if (isKingDead(Color.WHITE) || isKingDead(Color.BLACK)) {
            return calculateByKing();
        }
        return calculateByScore();
    }

    private boolean isKingDead(final Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .noneMatch(piece -> piece.isSameColor(color));
    }

    private Color calculateByKing() {
        if (isKingDead(Color.WHITE)) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    private Color calculateByScore() {
        if (score(Color.WHITE) == score(Color.BLACK)) {
            return Color.EMPTY;
        }
        if (score(Color.WHITE) > score(Color.BLACK)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
