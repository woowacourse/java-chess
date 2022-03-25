package chess.domain.chessPiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Rook extends ChessPiece {

    private static final Map<Color, Rook> cache;
    private static final String NAME = "R";
    private static final Double VALUE = 5.0;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Rook::new));
    }

    private Rook(final Color color) {
        super(color, NAME, VALUE);
    }

    public static Rook from(final Color color) {
        return cache.get(color);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(Position.from("a1"), Position.from("h1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(Position.from("a8"), Position.from("h8"));
    }

    @Override
    public void canMove(final Position from, final Position to) {
        final boolean sameFile = from.isSameFile(to);
        final boolean sameRank = from.isSameRank(to);

        if (!sameFile && !sameRank) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }
}
