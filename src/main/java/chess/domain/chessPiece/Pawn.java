package chess.domain.chessPiece;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Pawn extends ChessPiece {

    private static final Map<Color, Pawn> cache;
    private static final String NAME = "P";
    private static final Double VALUE = 1.0;
    private static final String WHITE_INIT_FILE = "2";
    private static final String BLACK_INIT_FILE = "7";

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Pawn::new));
    }

    private Pawn(final Color color) {
        super(color, NAME, VALUE);
    }

    public static Pawn from(final Color color) {
        return cache.get(color);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        final List<Position> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add(Position.from((char) i + WHITE_INIT_FILE));
        }
        return list;
    }

    @Override
    public List<Position> getInitBlackPosition() {
        final List<Position> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add(Position.from((char) i + BLACK_INIT_FILE));
        }
        return list;
    }

    @Override
    public void canMove(final Position from, final Position to) {
        if (from.isSameRank(to)) {
            if (isBlack() && checkMove(from, to, 1, BLACK_INIT_FILE)) {
                return;
            }

            if (!isBlack() && checkMove(from, to, -1, WHITE_INIT_FILE)) {
                return;
            }
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }

        checkCrossMove(from, to);
    }

    private boolean checkMove(final Position from, final Position to, final int movableDistance,
                              final String initFile) {
        final int fileDistance = from.fileDistance(to);
        if (movableDistance == fileDistance) {
            return true;
        }
        if (from.isSameFile(initFile)) {
            return fileDistance == movableDistance * 2;
        }
        return false;
    }

    public void checkCrossMove(final Position from, final Position to) {
        final int fileDistance = from.fileDistance(to);
        if (Math.abs(from.rankDistance(to)) == 1) {
            if (isBlack() && fileDistance == 1) {
                return;
            }
            if (!isBlack() && fileDistance == -1) {
                return;
            }
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }
}