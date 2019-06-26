package chess.domain.piece;

import chess.domain.Path;
import chess.domain.Player;
import chess.domain.Position;
import chess.exception.NotFoundPathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlackPawnTest {
    @Test
    void 흑색_폰이_왼쪽_아래로_갈_수_있는_경우() {
        BlackPawn pawn = BlackPawn.valueOf(Player.BLACK, Position.getPosition(2, 6));
        Position end = Position.getPosition(1, 5);

        Path path = new Path();

        assertThat(pawn.getAttackablePath(end)).isEqualTo(path);
    }

    @Test
    void 흑색_폰이_오른쪽_아래로_갈_수_있는_경우() {
        BlackPawn pawn = BlackPawn.valueOf(Player.BLACK, Position.getPosition(2, 6));
        Position end = Position.getPosition(3, 5);

        Path path = new Path();

        assertThat(pawn.getAttackablePath(end)).isEqualTo(path);
    }

    @Test
    void 흑색_폰이_아래로_갈_수_있는_경우() {
        BlackPawn pawn = BlackPawn.valueOf(Player.BLACK, Position.getPosition(1, 7));
        Position end = Position.getPosition(1, 6);

        Path path = new Path();

        assertThat(pawn.getMovablePath(end)).isEqualTo(path);
    }

    @Test
    void 흑색_폰이_두칸_이동할_수_있는_경우() {
        BlackPawn pawn = BlackPawn.valueOf(Player.BLACK, Position.getPosition(3, 7));
        Position end = Position.getPosition(3, 5);

        Path path = new Path();
        path.add(Position.getPosition(3, 6));

        assertThat(pawn.getMovablePath(end)).isEqualTo(path);
    }

    @Test
    void 흑색_폰이_End로_갈_수_없는_경우() {
        BlackPawn pawn = BlackPawn.valueOf(Player.BLACK, Position.getPosition(2, 7));
        Position end = Position.getPosition(4, 7);

        assertThrows(NotFoundPathException.class, () -> pawn.getMovablePath(end));
    }
}
