package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;
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

    @DisplayName("흰색 폰은")
    @Nested
    class WhitePawnTest {

        private final Pawn sut = new Pawn(Team.WHITE);

        @DisplayName("target 좌표에 아군 기물이 있다면, 이동할 수 없다.")
        @Test
        void attackTeam() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(1, 'e');
            Coordinate target = new Coordinate(2, 'e');
            piecesMap.put(source, sut);
            piecesMap.put(target, new Pawn(Team.WHITE));
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아군 기물은 공격할 수 없습니다.");
        }

        @DisplayName("제자리 이동을 할 수 없다.")
        @Test
        void sameCoordinate() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, source, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("제자리 이동은 할 수 없습니다.");
        }

        @DisplayName("아군 방향으로 1칸 전진할 수 없다.")
        @Test
        void oneBackStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(2, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        @DisplayName("적진 방향으로 1칸 전진할 수 있다.")
        @Test
        void oneStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(4, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatCode(() -> sut.validateMovable(source, target, pieces))
                    .doesNotThrowAnyException();
        }

        @DisplayName("1칸 전진하는 위치에 적군 기물이 있다면 움직일 수 없다.")
        @Test
        void noOneStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(4, 'e');
            piecesMap.put(source, sut);
            piecesMap.put(target, new Pawn(Team.BLACK));

            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
        }

        @DisplayName("초기 위치에 있을 경우, 적진 방향으로 2칸 움직일 수 있다.")
        @Test
        void twoStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(2, 'e');
            Coordinate target = new Coordinate(4, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatCode(() -> sut.validateMovable(source, target, pieces))
                    .doesNotThrowAnyException();
        }

        @DisplayName("초기 위치가 아닌 경우, 적진 방향으로 2칸 움직일 수 없다.")
        @Test
        void noTwoStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            Coordinate target = new Coordinate(5, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
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
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate obstacle = new Coordinate(3, 'd');
            Coordinate target = new Coordinate(4, 'd');
            piecesMap.put(source, sut);
            piecesMap.put(obstacle, new Pawn(Team.WHITE));
            piecesMap.put(target, new Pawn(Team.BLACK));
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
        }

        @DisplayName("초기 위치지만, target에 적군 기물이 존재하면 2칸 움직일 수 없다.")
        @Test
        void noTwoStepBecauseCantAttack() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate target = new Coordinate(4, 'd');
            piecesMap.put(source, sut);
            piecesMap.put(target, new Pawn(Team.BLACK));
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
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
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate enemy = new Coordinate(3, 'e');
            piecesMap.put(source, sut);
            piecesMap.put(enemy, new Pawn(Team.BLACK));
            Pieces pieces = new Pieces(piecesMap);

            assertThatCode(() -> sut.validateMovable(source, enemy, pieces))
                    .doesNotThrowAnyException();
        }

        @DisplayName("적진 방향 양쪽 대각선에 적군 기물이 없다면 움직일 수 없다.")
        @Test
        void noDiagonal() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(2, 'd');
            Coordinate target = new Coordinate(3, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
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
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(6, 'e');
            piecesMap.put(source, sut);
            piecesMap.put(target, new Pawn(Team.BLACK));
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아군 기물은 공격할 수 없습니다.");
        }

        @DisplayName("제자리 이동을 할 수 없다.")
        @Test
        void sameCoordinate() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(3, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, source, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("제자리 이동은 할 수 없습니다.");
        }

        @DisplayName("아군 방향으로 1칸 전진할 수 없다.")
        @Test
        void oneBackStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(8, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        @DisplayName("적진 방향으로 1칸 전진할 수 있다.")
        @Test
        void oneStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(6, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatCode(() -> sut.validateMovable(source, target, pieces))
                    .doesNotThrowAnyException();
        }


        @DisplayName("1칸 전진하는 위치에 적군 기물이 있다면 움직일 수 없다.")
        @Test
        void noOneStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(4, 'e');
            Coordinate target = new Coordinate(3, 'e');
            piecesMap.put(source, sut);
            piecesMap.put(target, new Pawn(Team.WHITE));

            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
        }

        @DisplayName("초기 위치에 있을 경우, 적진 방향으로 2칸 움직일 수 있다.")
        @Test
        void twoStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(5, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatCode(() -> sut.validateMovable(source, target, pieces))
                    .doesNotThrowAnyException();
        }

        @DisplayName("초기 위치가 아닌 경우, 적진 방향으로 2칸 움직일 수 없다.")
        @Test
        void noTwoStep() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(6, 'e');
            Coordinate target = new Coordinate(4, 'e');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
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
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'd');
            Coordinate obstacle = new Coordinate(6, 'd');
            Coordinate target = new Coordinate(5, 'd');
            piecesMap.put(source, sut);
            piecesMap.put(obstacle, new Pawn(Team.BLACK));
            piecesMap.put(target, new Pawn(Team.WHITE));
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("기물로 막혀있어 이동할 수 없습니다.");
        }

        @DisplayName("초기 위치지만, target에 적군 기물이 존재하면 2칸 움직일 수 없다.")
        @Test
        void noTwoStepBecauseCantAttack() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'd');
            Coordinate target = new Coordinate(5, 'd');
            piecesMap.put(source, sut);
            piecesMap.put(target, new Pawn(Team.WHITE));
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
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
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate enemy = new Coordinate(6, 'f');
            piecesMap.put(source, sut);
            piecesMap.put(enemy, new Pawn(Team.WHITE));
            Pieces pieces = new Pieces(piecesMap);

            assertThatCode(() -> sut.validateMovable(source, enemy, pieces))
                    .doesNotThrowAnyException();
        }

        @DisplayName("적진 방향 양쪽 대각선에 적군 기물이 없다면 움직일 수 없다.")
        @Test
        void noDiagonal() {
            HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
            Coordinate source = new Coordinate(7, 'e');
            Coordinate target = new Coordinate(6, 'f');
            piecesMap.put(source, sut);
            Pieces pieces = new Pieces(piecesMap);

            assertThatThrownBy(() -> sut.validateMovable(source, target, pieces))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }
}
