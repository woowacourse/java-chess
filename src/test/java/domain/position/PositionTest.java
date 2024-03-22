package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    void UP_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.F, Rank.EIGHT);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(2);
        assertThat(positions).containsExactlyInAnyOrderElementsOf(List.of(
                new Position(File.F, Rank.SIX),
                new Position(File.F, Rank.SEVEN)
        ));
    }

    @Test
    void UP_RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.H, Rank.SEVEN);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(1);
        assertThat(positions).containsExactly(new Position(File.G, Rank.SIX));
    }

    @Test
    void RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.G, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void DOWN_RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.H, Rank.THREE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(1);
        assertThat(positions).containsExactly(new Position(File.G, Rank.FOUR));
    }

    @Test
    void DOWN_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.F, Rank.TWO);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(2);
        assertThat(positions).containsExactlyInAnyOrderElementsOf(List.of(
                new Position(File.F, Rank.FOUR),
                new Position(File.F, Rank.THREE)
        ));
    }

    @Test
    void DOWN_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.C, Rank.TWO);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(2);
        assertThat(positions).containsExactlyInAnyOrderElementsOf(List.of(
                new Position(File.E, Rank.FOUR),
                new Position(File.D, Rank.THREE)
        ));
    }

    @Test
    void LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.F, Rank.FIVE);
        Position target = new Position(File.C, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(2);
        assertThat(positions).containsExactlyInAnyOrderElementsOf(List.of(
                new Position(File.E, Rank.FIVE),
                new Position(File.D, Rank.FIVE)
        ));
    }

    @Test
    void UP_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.A, Rank.EIGHT);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(3);
        assertThat(positions).containsExactlyInAnyOrderElementsOf(List.of(
                new Position(File.D, Rank.FIVE),
                new Position(File.C, Rank.SIX),
                new Position(File.B, Rank.SEVEN)
        ));
    }

    @Test
    void UP_UP_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.D, Rank.SIX);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void UP_UP_RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.F, Rank.SIX);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void RIGHT_RIGHT_UP_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.G, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void RIGHT_RIGHT_DOWN_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.G, Rank.THREE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void DOWN_DOWN_RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.F, Rank.TWO);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void DOWN_DOWN_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.D, Rank.TWO);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void LEFT_LEFT_DOWN_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.C, Rank.THREE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }

    @Test
    void LEFT_LEFT_UP_방향으로_이동하는_경로를_반환한다() {
        Position source = new Position(File.E, Rank.FOUR);
        Position target = new Position(File.C, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);
        assertThat(positions).hasSize(0);
    }
}
