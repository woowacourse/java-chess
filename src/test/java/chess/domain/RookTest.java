package chess.domain;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Rook;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;
import chess.domain.geometric.Vector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    void 상() {
        Unit rook = new Rook(Team.WHITE);

        assertThat(rook.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(1, 3))))
                .isEqualTo(true);
    }

    @Test
    void 하() {
        Unit rook = new Rook(Team.WHITE);

        assertThat(rook.validateDirection(Vector.of(Position.create(1, 3)
                , Position.create(1, 1))))
                .isEqualTo(true);
    }

    @Test
    void 좌() {
        Unit rook = new Rook(Team.WHITE);

        assertThat(rook.validateDirection(Vector.of(Position.create(3, 1)
                , Position.create(1, 1))))
                .isEqualTo(true);
    }

    @Test
    void 우() {
        Unit rook = new Rook(Team.WHITE);

        assertThat(rook.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(3, 1))))
                .isEqualTo(true);
    }

    @Test
    void 경로_벗어남() {
        Unit rook = new Rook(Team.WHITE);

        assertThat(rook.validateDirection(Vector.of(Position.create(1, 1)
                , Position.create(3, 7))))
                .isEqualTo(false);

    }
}
