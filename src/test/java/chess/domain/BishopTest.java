package chess.domain;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Bishop;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @Test
    void 대각선_우상() {
        Unit bishop = new Bishop(Team.WHITE);

        assertThat(bishop.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(3, 3))))
                .isEqualTo(true);
    }

    @Test
    void 대각선_우하() {
        Unit bishop = new Bishop(Team.WHITE);

        assertThat(bishop.validateDirection(Vector.of(Position.create(3, 3)
                , Position.create(5, 1))))
                .isEqualTo(true);
    }

    @Test
    void 대각선_좌상() {
        Unit bishop = new Bishop(Team.WHITE);

        assertThat(bishop.validateDirection(Vector.of(Position.create(3, 3)
                , Position.create(1, 5))))
                .isEqualTo(true);
    }

    @Test
    void 대각선_좌하() {
        Unit bishop = new Bishop(Team.WHITE);

        assertThat(bishop.validateDirection(Vector.of(Position.create(3, 3)
                , Position.create(1, 1))))
                .isEqualTo(true);
    }

    @Test
    void 범위를_벗어난_경우_종단() {
        Unit bishop = new Bishop(Team.WHITE);

        assertThat(bishop.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(1, 3))))
                .isEqualTo(false);
    }

    @Test
    void 범위를_벗어난_경우_횡단() {
        Unit bishop = new Bishop(Team.WHITE);

        assertThat(bishop.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(3, 1))))
                .isEqualTo(false);
    }

    @Test
    void unit_class() {
        System.out.println(UnitInformation.BISHOP.name());
    }

}
