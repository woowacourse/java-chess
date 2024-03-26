package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RouteTest {

    @DisplayName("Route를 생성할 수 있다.")
    @Nested
    class constructTest {
        @DisplayName("방향들의 길이와 상태들의 길이가 동일해야 한다.")
        @Test
        void stepSizeTest() {
            assertThatThrownBy(() -> new Route(
                    List.of(Direction.DOWN, Direction.DOWN),
                    List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("방향의 개수와 상태의 개수가 다릅니다.");
        }
        @DisplayName("경로의 길이는 7칸을 넘을 수 없다.")
        @Test
        void pathMaxLengthTest() {
            assertThatThrownBy(() -> new Route(
                            List.of(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
                            List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
                    ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("경로의 길이는 7칸을 넘을 수 없습니다.");
        }

        @DisplayName("경로의 길이는 1칸 이상이어야 한다.")
        @Test
        void pathMinLengthTest() {
            assertThatThrownBy(() -> new Route(Collections.emptyList(), Collections.emptyList()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("제자리 경로를 생성할 수 없습니다.");
        }

    }

    @DisplayName("경로의 길이를 판별할 수 있다.")
    @Test
    void isSizeOfTest() {
        Route route = new Route(
            List.of(Direction.DOWN, Direction.DOWN),
            List.of(SquareState.EMPTY, SquareState.EMPTY)
        );

        assertThat(route.isSizeOf(2)).isTrue();
    }

    @DisplayName("방향의 종류 개수를 판별할 수 있다.")
    @Test
    void categoryNumOfTest() {
        Route route = new Route(
                List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT, Direction.DOWN),
                List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
        );

        assertThat(route.isDirectionsCount(2)).isTrue();
    }

    @DisplayName("대각선을 포함하고 있는지 확인할 수 있다.")
    @Nested
    class ContainsDiagonalTest {

        @DisplayName("대각선을 포함하고 있다면 참을 반환한다.")
        @Test
        void containsTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT, Direction.DOWN),
                    List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
            );

            assertThat(route.containsDiagonal()).isTrue();
        }

        @DisplayName("대각선을 포함하고 있지 않다면 거짓을 반환한다.")
        @Test
        void notContainsTest() {
            Route route = new Route(
                    List.of(Direction.DOWN, Direction.UP),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );

            assertThat(route.containsDiagonal()).isFalse();
        }
    }

    @DisplayName("수직 또는 수평선을 포함하고 있는지 확인할 수 있다.")
    @Nested
    class ContainsOrthogonalTest {
        @DisplayName("수직 또는 수평선을 포함하고 있다면 참을 반환한다.")
        @Test
        void containsTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT, Direction.DOWN),
                    List.of(SquareState.EMPTY, SquareState.EMPTY, SquareState.EMPTY)
            );
            assertThat(route.containsOrthogonal()).isTrue();
        }

        @DisplayName("수직 또는 수평선을 포함하고 있지 않다면 거짓을 반환한다.")
        @Test
        void notContainsTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );
            assertThat(route.containsOrthogonal()).isFalse();
        }
    }

    @DisplayName("도착 지점을 제외한 경로에 기물이 있는지 확인한다.")
    @Nested
    class HasPieceDirectionFinderExcludedTargetTest {
        @DisplayName("도착 지점을 제외한 경로에 기물이 있으면 참을 반환한다.")
        @Test
        void trueTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.ALLY, SquareState.EMPTY)
            );
            assertThat(route.hasPiecePathExclusive()).isTrue();
        }

        @DisplayName("도착 지점을 제외한 경로에 기물이 없으면 거짓을 반환한다.")
        @Test
        void falseTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );

            assertThat(route.hasPiecePathExclusive()).isFalse();
        }
    }

    @DisplayName("경로에 다른 기물이 없는지 확인한다.")
    @Nested
    class IsAllEmptyTest {
        @DisplayName("경로에 다른 기물이 없으면 참을 반환한다.")
        @Test
        void emptyTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );
            assertThat(route.isAllEmpty()).isTrue();
        }

        @DisplayName("경로에 다른 기물이 있으면 거짓을 반환한다.")
        @Test
        void notEmptyTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ALLY)
            );

            assertThat(route.isAllEmpty()).isFalse();
        }
    }

    @DisplayName("목적지에 갈 수 있는지 확인한다.")
    @Nested
    class CanReachTest {

        @DisplayName("목적지가 비어있으면 도달할 수 있다.")
        @Test
        void emptyTargetTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.EMPTY)
            );

            assertThat(route.hasNoAllyAtTarget()).isTrue();
        }

        @DisplayName("목적지에 적 기물이 있으면 도달할 수 있다.")
        @Test
        void enemyTargetTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ENEMY)
            );

            assertThat(route.hasNoAllyAtTarget()).isTrue();
        }


        @DisplayName("목적지에 아군 기물이 있으면 도달할 수 없다.")
        @Test
        void allyTargetTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ALLY)
            );

            assertThat(route.hasNoAllyAtTarget()).isFalse();
        }
    }

    @DisplayName("도착 지점에 적이 있는지 확인한다.")
    @Nested
    class isTargetHasEnemyTest {
        @DisplayName("도착 지점이 적이 있음을 확인한다.")
        @Test
        void trueTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ENEMY)
            );

            assertThat(route.isTargetHasEnemy()).isTrue();
        }

        @DisplayName("도착 지점이 적이 없음을 확인한다.")
        @Test
        void enemyTargetTest() {
            Route route = new Route(
                    List.of(Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ALLY)
            );

            assertThat(route.isTargetHasEnemy()).isFalse();
        }
    }

    @DisplayName("루트의 모든 방향이 위를 향하는지 확인한다.")
    @Nested
    class isUpsideTest {
        @DisplayName("루트의 모든 방향이 위를 향함을 확인한다.")
        @Test
        void trueTest() {
            Route route = new Route(
                    List.of(Direction.UP, Direction.UP_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ENEMY)
            );

            assertThat(route.isUpside()).isTrue();
        }

        @DisplayName("루트의 모든 방향이 위를 향하지 않음을 확인한다.")
        @Test
        void enemyTargetTest() {
            Route route = new Route(
                    List.of(Direction.DOWN, Direction.UP_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ALLY)
            );

            assertThat(route.isUpside()).isFalse();
        }
    }

    @DisplayName("루트의 모든 방향이 아래를 향하는지 확인한다.")
    @Nested
    class isDownsideTest {
        @DisplayName("루트의 모든 방향이 아래를 향함을 확인한다.")
        @Test
        void trueTest() {
            Route route = new Route(
                    List.of(Direction.DOWN, Direction.DOWN_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ENEMY)
            );

            assertThat(route.isDownside()).isTrue();
        }

        @DisplayName("루트의 모든 방향이 아래를 향하지 않음을 확인한다.")
        @Test
        void enemyTargetTest() {
            Route route = new Route(
                    List.of(Direction.DOWN, Direction.UP_LEFT),
                    List.of(SquareState.EMPTY, SquareState.ALLY)
            );

            assertThat(route.isDownside()).isFalse();
        }
    }
}
