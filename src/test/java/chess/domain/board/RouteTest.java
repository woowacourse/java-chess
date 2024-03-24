package chess.domain.board;

class RouteTest {
    /*
    @DisplayName("경로의 길이는 7칸을 넘을 수 없다.")
    @Test
    void pathMaxLengthTest() {
        assertThatThrownBy(
                () -> new Route(List.of(
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로의 길이는 7칸을 넘을 수 없습니다.");
    }

    @DisplayName("경로의 길이는 1칸 이상이어야 한다.")
    @Test
    void pathMinLengthTest() {
        assertThatThrownBy(() -> new Route(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제자리 경로를 생성할 수 없습니다.");
    }

    @DisplayName("경로의 길이를 판별할 수 있다.")
    @Test
    void isSizeOfTest() {
        Route route = new Route(List.of(
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY)
        ));

        assertThat(route.isSizeOf(2)).isTrue();
    }

    @DisplayName("방향의 종류 개수를 판별할 수 있다.")
    @Test
    void categoryNumOfTest() {
        Route route = new Route(List.of(
                new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY)
        ));

        assertThat(route.categoryNumOf(2)).isTrue();
    }

    @DisplayName("대각선을 포함하고 있는지 확인할 수 있다.")
    @Nested
    class ContainsDiagonalTest {

        @DisplayName("대각선을 포함하고 있다면 참을 반환한다.")
        @Test
        void containsTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN, SquareState.EMPTY)
            ));

            assertThat(route.containsDiagonal()).isTrue();
        }

        @DisplayName("대각선을 포함하고 있지 않다면 거짓을 반환한다.")
        @Test
        void notContainsTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN, SquareState.EMPTY),
                    new Step(Direction.UP, SquareState.EMPTY)
            ));

            assertThat(route.containsDiagonal()).isFalse();
        }
    }

    @DisplayName("수직 또는 수평선을 포함하고 있는지 확인할 수 있다.")
    @Nested
    class ContainsOrthogonalTest {
        @DisplayName("수직 또는 수평선을 포함하고 있다면 참을 반환한다.")
        @Test
        void containsTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN, SquareState.EMPTY)
            ));
            assertThat(route.containsOrthogonal()).isTrue();
        }

        @DisplayName("수직 또는 수평선을 포함하고 있지 않다면 거짓을 반환한다.")
        @Test
        void notContainsTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY)
            ));
            assertThat(route.containsOrthogonal()).isFalse();
        }
    }

    @DisplayName("경로에 다른 기물이 없는지 확인한다.")
    @Nested
    class IsAllEmptyTest {
        @DisplayName("경로에 다른 기물이 없으면 참을 반환한다.")
        @Test
        void emptyTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY)
            ));
            assertThat(route.isAllEmpty()).isTrue();
        }

        @DisplayName("경로에 다른 기물이 있으면 거짓을 반환한다.")
        @Test
        void notEmptyTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.ALLY)
            ));

            assertThat(route.isAllEmpty()).isFalse();
        }
    }

    @DisplayName("목적지에 갈 수 있는지 확인한다.")
    @Nested
    class CanReachTest {

        @DisplayName("목적지가 비어있으면 도달할 수 있다.")
        @Test
        void emptyTargetTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY)
            ));

            assertThat(route.hasNoAllyAtTarget()).isTrue();
        }

        @DisplayName("목적지에 적 기물이 있으면 도달할 수 있다.")
        @Test
        void enemyTargetTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.ENEMY)
            ));

            assertThat(route.hasNoAllyAtTarget()).isTrue();
        }


        @DisplayName("목적지에 아군 기물이 있으면 도달할 수 없다.")
        @Test
        void allyTargetTest() {
            Route route = new Route(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.ALLY)
            ));

            assertThat(route.hasNoAllyAtTarget()).isFalse();
        }
    }

     */
}
