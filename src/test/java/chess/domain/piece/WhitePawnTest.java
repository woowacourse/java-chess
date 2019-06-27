package chess.domain.piece;

import chess.domain.Path;
import chess.domain.Player;
import chess.domain.Position;
import chess.exception.NotFoundPathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WhitePawnTest {
    @Test
    void 백색_폰이_오른쪽_위로_갈_수_있는_경우() {
        WhitePawn pawn = WhitePawn.valueOf(Player.WHITE, Position.getPosition(1, 2));
        Position end = Position.getPosition(2, 3);

        Path path = new Path();

        assertThat(pawn.getAttackablePath(end)).isEqualTo(path);
    }

    @Test
    void 백색_폰이_왼쪽_위로_갈_수_있는_경우() {
        WhitePawn pawn = WhitePawn.valueOf(Player.WHITE, Position.getPosition(2, 2));
        Position end = Position.getPosition(1, 3);

        Path path = new Path();

        assertThat(pawn.getAttackablePath(end)).isEqualTo(path);
    }

    @Test
    void 백색_폰이_위로_갈_수_있는_경우() {
        WhitePawn pawn = WhitePawn.valueOf(Player.WHITE, Position.getPosition(1, 2));
        Position end = Position.getPosition(1, 4);

        Path path = new Path();
        path.add(Position.getPosition(1, 3));

        assertThat(pawn.getMovablePath(end)).isEqualTo(path);
    }

    @Test
    void 백색_폰이_두칸_이동할_수_있는_경우() {
        WhitePawn pawn = WhitePawn.valueOf(Player.WHITE, Position.getPosition(2, 2));
        Position end = Position.getPosition(2, 4);

        Path path = new Path();
        path.add(Position.getPosition(2, 3));

        assertThat(pawn.getMovablePath(end)).isEqualTo(path);
    }

    @Test
    void 백색_폰이_End로_갈_수_없는_경우() {
        WhitePawn pawn = WhitePawn.valueOf(Player.WHITE, Position.getPosition(1, 1));
        Position end = Position.getPosition(4, 7);

        assertThrows(NotFoundPathException.class, () -> pawn.getMovablePath(end));
    }
}
