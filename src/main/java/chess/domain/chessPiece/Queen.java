package chess.domain.chessPiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Queen extends ChessPiece {

    private static final Map<Color, Queen> cache;
    private static final String NAME = "Q";
    private static final Double VALUE = 9.0;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Queen::new));
    }

    private Queen(final Color color) {
        super(color, NAME, VALUE);
    }

    public static Queen from(final Color color) {
        return cache.get(color);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(Position.from("d1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(Position.from("d8"));
    }

    @Override
    public void canMove(final Position from, final Position to) {
        final int fileDistance = Math.abs(from.fileDistance(to));
        final int rankDistance = Math.abs(from.rankDistance(to));

        final boolean sameFile = from.isSameFile(to);
        final boolean sameRank = from.isSameRank(to);

        if ((!sameFile && !sameRank) && (fileDistance != rankDistance)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }
}
