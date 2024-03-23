package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Pawn(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @Disabled
    @DisplayName("흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findMovablePathWhite() {
        Coordinate start = new Coordinate(2, 'c');
        Coordinate destination = new Coordinate(3, 'c');
        Pawn pawn = new Pawn(Team.WHITE);

        List<Coordinate> result = pawn.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(
                new Coordinate(3, 'c'),
                new Coordinate(4, 'c'));
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @Disabled
    @DisplayName("검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findMovablePathBlack() {
        Coordinate start = new Coordinate(7, 'c');
        Coordinate destination = new Coordinate(3, 'c');
        Pawn pawn = new Pawn(Team.BLACK);

        List<Coordinate> result = pawn.findMovablePath(start, destination);

        List<Coordinate> expected = List.of(
                new Coordinate(6, 'c'),
                new Coordinate(5, 'c'));
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("흰색 폰은")
    @Nested
    class WhitePawnTest {

        private final Pawn sut = new Pawn(Team.WHITE);

        @DisplayName("target 좌표에 아군 기물이 있다면, 이동할 수 없다.")
        @Test
        void attackTeam() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(1, 'e');
            Coordinate target = new Coordinate(2, 'e');
            pieces.put(source, sut);
            pieces.put(target, new Pawn(Team.WHITE));
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아군 기물은 공격할 수 없습니다.");
        }

        @DisplayName("제자리 이동을 할 수 없다.")
        @Test
        void sameCoordinate() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, source, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("제자리 이동은 할 수 없습니다.");
        }

        @DisplayName("아군 방향으로 1칸 전진할 수 없다.")
        @Test
        void oneBackStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(2, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        @DisplayName("적진 방향으로 1칸 전진할 수 있다.")
        @Test
        void oneStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(4, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatCode(() -> sut.validateMovable(source, target, board))
                    .doesNotThrowAnyException();
        }

        @DisplayName("초기 위치에 있을 경우, 적진 방향으로 2칸 움직일 수 있다.")
        @Test
        void twoStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(2, 'e');
            Coordinate target = new Coordinate(4, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatCode(() -> sut.validateMovable(source, target, board))
                    .doesNotThrowAnyException();
        }

        @DisplayName("초기 위치가 아닌 경우, 적진 방향으로 2칸 움직일 수 없다.")
        @Test
        void noTwoStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(5, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("초기 상태의 폰이 아닌 경우, 2칸 이동할 수 없습니다.");
        }

        /***
         * ........ 8
         * ........ 7
         * ........ 6
         * ........ 5
         * ...T.... 4  T: target
         * ...O.... 3  O: Obstacle
         * ...P.... 2  P: Pawn(initial)
         * ........ 1
         * --------
         * abcdefgh
         */
        @DisplayName("초기 위치지만, 경로에 다른 기물이 있다면 적진 방향으로 2칸 움직일 수 없다.")
        @Test
        void noTwoStepBecauseStuck() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate obstacle = new Coordinate(3, 'd');
            Coordinate target = new Coordinate(4, 'd');
            pieces.put(source, sut);
            pieces.put(obstacle, new Pawn(Team.WHITE));
            pieces.put(target, new Pawn(Team.BLACK));
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
        }

        /***
         * ........ 8
         * ........ 7
         * ........ 6
         * ........ 5
         * ........ 4
         * ....T... 3  T: target(enemy)
         * ...P.... 2  P: Pawn(initial)
         * ........ 1
         * --------
         * abcdefgh
         */
        @DisplayName("적진 방향 양쪽 대각선에 적군 기물이 있으면 움직일 수 있다.")
        @Test
        void goDiagonal() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate enemy = new Coordinate(3, 'e');
            pieces.put(source, sut);
            pieces.put(enemy, new Pawn(Team.BLACK));
            Board board = new Board(pieces);

            assertThatCode(() -> sut.validateMovable(source, enemy, board))
                    .doesNotThrowAnyException();
        }

        @DisplayName("적진 방향 양쪽 대각선에 적군 기물이 없다면 움직일 수 없다.")
        @Test
        void noDiagonal() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate target = new Coordinate(3, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }

    @DisplayName("검정색 폰은")
    @Nested
    class BlackPawnTest {

        private final Pawn sut = new Pawn(Team.BLACK);

        @DisplayName("target 좌표에 아군 기물이 있다면 이동할 수 없다.")
        @Test
        void attackTeam() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(6, 'e');
            pieces.put(source, sut);
            pieces.put(target, new Pawn(Team.BLACK));
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아군 기물은 공격할 수 없습니다.");
        }

        @DisplayName("제자리 이동을 할 수 없다.")
        @Test
        void sameCoordinate() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, source, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("제자리 이동은 할 수 없습니다.");
        }

        @DisplayName("아군 방향으로 1칸 전진할 수 없다.")
        @Test
        void oneBackStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(8, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        @DisplayName("적진 방향으로 1칸 전진할 수 있다.")
        @Test
        void oneStepBlack() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(6, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatCode(() -> sut.validateMovable(source, target, board))
                    .doesNotThrowAnyException();
        }

        @DisplayName("초기 위치에 있을 경우, 적진 방향으로 2칸 움직일 수 있다.")
        @Test
        void twoStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(5, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatCode(() -> sut.validateMovable(source, target, board))
                    .doesNotThrowAnyException();
        }

        @DisplayName("초기 위치가 아닌 경우, 적진 방향으로 2칸 움직일 수 없다.")
        @Test
        void noTwoStep() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(6, 'e');
            Coordinate target = new Coordinate(4, 'e');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("초기 상태의 폰이 아닌 경우, 2칸 이동할 수 없습니다.");
        }

        /***
         * ........ 8
         * ...P.... 7  P: Pawn(initial)
         * ...O.... 6  O: Obstacle
         * ...T.... 5  T: target
         * ........ 4
         * ........ 3
         * ........ 2
         * ........ 1
         * --------
         * abcdefgh
         */
        @DisplayName("초기 위치지만, 경로에 다른 기물이 있다면 적진 방향으로 2칸 움직일 수 없다.")
        @Test
        void noTwoStepBecauseStuck() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'd');
            Coordinate obstacle = new Coordinate(6, 'd');
            Coordinate target = new Coordinate(5, 'd');
            pieces.put(source, sut);
            pieces.put(obstacle, new Pawn(Team.BLACK));
            pieces.put(target, new Pawn(Team.WHITE));
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
        }

        /***
         * ........ 8
         * ....P... 7 P: Pawn(initial)
         * .....T.. 6 T: target(enemy)
         * ........ 5
         * ........ 4
         * ........ 3
         * ........ 2
         * ........ 1
         * --------
         * abcdefgh
         */
        @DisplayName("적진 방향 양쪽 대각선에 적군 기물이 있으면 움직일 수 있다.")
        @Test
        void goDiagonal() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate enemy = new Coordinate(6, 'f');
            pieces.put(source, sut);
            pieces.put(enemy, new Pawn(Team.WHITE));
            Board board = new Board(pieces);

            assertThatCode(() -> sut.validateMovable(source, enemy, board))
                    .doesNotThrowAnyException();
        }

        @DisplayName("적진 방향 양쪽 대각선에 적군 기물이 없다면 움직일 수 없다.")
        @Test
        void noDiagonal() {
            HashMap<Coordinate, Piece> pieces = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(6, 'f');
            pieces.put(source, sut);
            Board board = new Board(pieces);

            assertThatThrownBy(() -> sut.validateMovable(source, target, board))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }
}
