package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Rook extends ChessPiece {

    private static final Map<Color, Rook> cache;
    private static final Double VALUE = 5.0;

    static {
        cache = Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        Rook::new));
    }

    private Rook(final Color color) {
        super(color);
    }

    public static Rook from(final Color color) {
        return cache.get(color);
    }

    @Override
    public void checkMovablePosition(final Position from, final Position to, final ChessPiece chessPiece) {
        final boolean sameFile = from.isSameFile(to);
        final boolean sameRank = from.isSameRank(to);

        if (!sameFile && !sameRank) {
            throw new IllegalArgumentException(INVALID_TARGET_POSITION);
        }

        checkTargetPosition(chessPiece);
    }

    @Override
    public double value() {
        return VALUE;
    }
}
