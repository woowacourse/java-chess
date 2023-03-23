package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.pieces.Position;
import org.junit.jupiter.api.Test;

import static chess.domain.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void findDirection_up() {
        Position current = new Position(Rank.FIVE, File.H);
        Position target = new Position(Rank.SIX, File.H);

        assertThat(findDirection(current, target)).isEqualTo(UP);
    }

    @Test
    void findDirection_down() {
        Position current = new Position(Rank.SIX, File.H);
        Position target = new Position(Rank.FIVE, File.H);

        assertThat(findDirection(current, target)).isEqualTo(DOWN);
    }

    @Test
    void findDirection_left() {
        Position current = new Position(Rank.SIX, File.B);
        Position target = new Position(Rank.SIX, File.A);

        assertThat(findDirection(current, target)).isEqualTo(LEFT);
    }

    @Test
    void findDirection_right() {
        Position current = new Position(Rank.SIX, File.A);
        Position target = new Position(Rank.SIX, File.B);

        assertThat(findDirection(current, target)).isEqualTo(RIGHT);
    }

    @Test
    void findDirection_upLeft() {
        Position current = new Position(Rank.FIVE, File.B);
        Position target = new Position(Rank.SIX, File.A);

        assertThat(findDirection(current, target)).isEqualTo(UP_LEFT);
    }

    @Test
    void findDirection_upRight() {
        Position current = new Position(Rank.FIVE, File.A);
        Position target = new Position(Rank.SIX, File.B);

        assertThat(findDirection(current, target)).isEqualTo(UP_RIGHT);
    }

    @Test
    void findDirection_downLeft() {
        Position current = new Position(Rank.SIX, File.B);
        Position target = new Position(Rank.FIVE, File.A);

        assertThat(findDirection(current, target)).isEqualTo(DOWN_LEFT);
    }

    @Test
    void findDirection_downRight() {
        Position current = new Position(Rank.SIX, File.A);
        Position target = new Position(Rank.FIVE, File.B);

        assertThat(findDirection(current, target)).isEqualTo(DOWN_RIGHT);
    }
}
