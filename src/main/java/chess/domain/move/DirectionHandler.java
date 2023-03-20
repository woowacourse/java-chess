package chess.domain.move;

import chess.domain.move.enums.*;
import chess.domain.position.Position;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DirectionHandler {

    public static MoveEnum findByPosition(final Position source, final Position target) {
        final int dx = diffFile(source, target);
        final int dy = diffRank(source, target);

        List<Class<? extends MoveEnum>> moveList = Arrays.asList(VerticalMove.class, HorizontalMove.class, DiagonalMove.class, KnightMove.class);

        for (Class<? extends MoveEnum> move: moveList) {
            MoveEnum foundMove = findByMovePosition(move, dx, dy);
            if (foundMove != null) {
                return foundMove;
            }
        }
        throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
    }

    private static int diffFile(final Position source, final Position target) {
        return target.file() - source.file();
    }

    private static int diffRank(final Position source, final Position target) {
        return target.rank() - source.rank();
    }

    private static <E extends MoveEnum> E findByMovePosition(Class<E> moveClass, final int dx, final int dy) {
        try {
            Method moveMethod = moveClass.getMethod("values");
            E[] moveValues = (E[]) moveMethod.invoke(null);

            for (E moveValue: moveValues) {
                if (moveValue.isMove(dx, dy)) {
                    return moveValue;
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
