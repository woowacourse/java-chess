package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @Test
    void 우() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(2,1))))
                .isEqualTo(true);

    }

    @Test
    void 우상() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(2,2))))
                .isEqualTo(true);
    }

    @Test
    void 상() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(1,2))))
                .isEqualTo(true);

    }

    @Test
    void 좌상() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(0,2))))
                .isEqualTo(true);

    }

    @Test
    void 좌() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(0,1))))
                .isEqualTo(true);

    }

    @Test
    void 좌하() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(0,0))))
                .isEqualTo(true);

    }

    @Test
    void 하() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(1,0))))
                .isEqualTo(true);

    }

    @Test
    void 우하() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(2,0))))
                .isEqualTo(true);

    }

    @Test
    void 범위를_벗어난_경우() {
        Unit queen = new Queen(Team.WHITE);

        assertThat(queen.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(2,5))))
                .isEqualTo(false);
    }


}
