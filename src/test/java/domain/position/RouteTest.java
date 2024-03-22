package domain.position;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RouteTest {
    @Nested
    class 경로_생성 {
        @Test
        void UP_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.F, Rank.EIGHT);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(2)
                    .containsExactlyInAnyOrderElementsOf(List.of(
                            new Position(File.F, Rank.SIX),
                            new Position(File.F, Rank.SEVEN)
                    ));
        }

        @Test
        void UP_RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.H, Rank.SEVEN);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(1)
                    .containsExactly(new Position(File.G, Rank.SIX));
        }

        @Test
        void RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.G, Rank.FIVE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void DOWN_RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.H, Rank.THREE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(1)
                    .containsExactly(new Position(File.G, Rank.FOUR));
        }

        @Test
        void DOWN_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.F, Rank.TWO);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(2)
                    .containsExactlyInAnyOrderElementsOf(List.of(
                            new Position(File.F, Rank.FOUR),
                            new Position(File.F, Rank.THREE)
                    ));
        }

        @Test
        void DOWN_LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.C, Rank.TWO);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(2)
                    .containsExactlyInAnyOrderElementsOf(List.of(
                            new Position(File.E, Rank.FOUR),
                            new Position(File.D, Rank.THREE)
                    ));
        }

        @Test
        void LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.C, Rank.FIVE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(2)
                    .containsExactlyInAnyOrderElementsOf(List.of(
                            new Position(File.E, Rank.FIVE),
                            new Position(File.D, Rank.FIVE)
                    ));
        }

        @Test
        void UP_LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.A, Rank.EIGHT);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(3)
                    .containsExactlyInAnyOrderElementsOf(List.of(
                            new Position(File.D, Rank.FIVE),
                            new Position(File.C, Rank.SIX),
                            new Position(File.B, Rank.SEVEN)
                    ));
        }

        @Test
        void UP_UP_LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.D, Rank.SIX);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void UP_UP_RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.F, Rank.SIX);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void RIGHT_RIGHT_UP_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.G, Rank.FIVE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void RIGHT_RIGHT_DOWN_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.G, Rank.THREE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void DOWN_DOWN_RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.F, Rank.TWO);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void DOWN_DOWN_LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.D, Rank.TWO);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void LEFT_LEFT_DOWN_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.C, Rank.THREE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void LEFT_LEFT_UP_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.C, Rank.FIVE);

            Route route = Route.create(resource, target);
            assertThat(route.getRoute())
                    .hasSize(0);
        }

        @Test
        void LEFT_LEFT_LEFT_UP_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.E, Rank.FOUR);
            Position target = new Position(File.B, Rank.FIVE);

            assertThatThrownBy(() -> Route.create(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("올바르지 않은 방향입니다.");
        }
    }
}
