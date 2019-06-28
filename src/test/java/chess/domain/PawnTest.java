package chess.domain;

import chess.domain.chess.game.Team;
import chess.domain.chess.unit.Pawn;
import chess.domain.geometric.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PawnTest {
    @Test
    void 직선_경로_내역() {
        Pawn pawn = new Pawn(Team.WHITE);

        Assertions.assertThat(pawn.validateDirection(Position.create(2, 2)
                , Position.create(2, 3), false))
                .isEqualTo(true);
    }

    @Test
    void 대각선_경로_내역() {
        Pawn pawn = new Pawn(Team.WHITE);

        Assertions.assertThat(pawn.validateDirection(Position.create(2, 2)
                , Position.create(3, 3), true))
                .isEqualTo(true);
    }

    @Test
    void 최초로_두_칸_움직일_경우() {
        Pawn pawn = new Pawn(Team.WHITE);

        Assertions.assertThat(pawn.validateDirection(Position.create(1, 1)
                , Position.create(1, 3), false))
                .isEqualTo(true);
    }

    @Test
    void 최초가_아닐_때_두_칸_움직이는_경우() {
        Pawn pawn = new Pawn(Team.WHITE);

        Assertions.assertThat(pawn.validateDirection(Position.create(1, 2)
                , Position.create(1, 4), false))
                .isEqualTo(false);
    }

    @Test
    void 뒤로_갈_경우() {
        Pawn pawn = new Pawn(Team.WHITE);

        Assertions.assertThat(pawn.validateDirection(Position.create(1, 2)
                , Position.create(1, 1), false))
                .isEqualTo(false);
    }
}
