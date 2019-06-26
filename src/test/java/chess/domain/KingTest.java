package chess.domain;

import chess.domain.chess.Team;
import chess.domain.chess.unit.King;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    @Test
    void 우() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(2, 1))))
                .isEqualTo(true);

    }

    @Test
    void 우상() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(2, 2))))
                .isEqualTo(true);
    }

    @Test
    void 상() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(1, 2))))
                .isEqualTo(true);

    }

    @Test
    void 좌상() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(0, 2))))
                .isEqualTo(true);

    }

    @Test
    void 좌() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(0, 1))))
                .isEqualTo(true);

    }

    @Test
    void 좌하() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(0, 0))))
                .isEqualTo(true);

    }

    @Test
    void 하() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(1, 0))))
                .isEqualTo(true);

    }

    @Test
    void 우하() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(2, 0))))
                .isEqualTo(true);

    }

    @Test
    void 두칸_범위를_벗어난_경우() {
        Unit king = new King(Team.WHITE);

        assertThat(king.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(1, 3))))
                .isEqualTo(false);

    }

}
