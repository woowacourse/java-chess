package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @Test
    void 좌좌상_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(2,2)
                , Position.create(0,3))))
                .isEqualTo(true);

    }

    @Test
    void 좌좌하_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(2,2)
                , Position.create(0,1))))
                .isEqualTo(true);

    }

    @Test
    void 좌상상_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(0,3))))
                .isEqualTo(true);


    }

    @Test
    void 우상상_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(2,3))))
                .isEqualTo(true);

    }

    @Test
    void 우우상_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(3,2))))
                .isEqualTo(true);


    }

    @Test
    void 우우하_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(3,0))))
                .isEqualTo(true);


    }


    @Test
    void 좌하하_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(2,2)
                , Position.create(1,0))))
                .isEqualTo(true);


    }

    @Test
    void 우하하_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(2,2)
                , Position.create(3,0))))
                .isEqualTo(true);


    }

    @Test
    void 유효한_범위가_벗어나는_경우() {
        Unit knight = new Knight(Team.WHITE);

        assertThat(knight.validateDirection(Vector.of(Position.create(1,1)
                , Position.create(3,3))))
                .isEqualTo(false);
    }


}
