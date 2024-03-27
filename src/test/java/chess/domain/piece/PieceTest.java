package chess.domain.piece;

import static chess.domain.pixture.PieceFixture.BLACK_QUEEN;
import static chess.domain.pixture.PieceFixture.WHITE_BISHOP;
import static chess.domain.pixture.PieceFixture.WHITE_KING;
import static chess.domain.pixture.PieceFixture.WHITE_KNIGHT;
import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
import static chess.domain.pixture.PieceFixture.WHITE_QUEEN;
import static chess.domain.pixture.PieceFixture.WHITE_ROOK;
import static chess.domain.pixture.PositionFixture.WHITE_PAWN_FIRST_MOVE_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTest {

    @Nested
    class PawnMove {

        // p** <- (1,3)
        // p** <- (1,4)
        // P**
        @Test
        @DisplayName("폰이 직선으로 이동할 경우 경로에 어떠한 말도 없다면 이동 가능하다.")
        void canMoveStraight() {
            assertAll(
                    () -> assertThat(WHITE_PAWN.getPiece()
                            .canMove(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), Position.of(1, 3),
                                    Map.of(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(),
                                            WHITE_PAWN.getPiece()))).isTrue(),

                    () -> assertThat(WHITE_PAWN.getPiece()
                            .canMove(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), Position.of(1, 4),
                                    Map.of(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(),
                                            WHITE_PAWN.getPiece()))).isTrue());
        }

        // ****
        // *p*p <- target
        // **P* <- source
        @Test
        @DisplayName("폰이 대각선 이동할 경우 도착 위치에 상대방의 말이 있다면 이동 가능하다.")
        void canMoveDiagonal() {
            Position source = Position.of(3, 1);
            assertAll(
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(2, 2),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(2, 2), BLACK_QUEEN.getPiece()))).isTrue(),

                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(4, 2),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(4, 2), BLACK_QUEEN.getPiece()))).isTrue());
        }

        // ****
        // p*** <- target(p, P)
        // p*** <- source
        @Test
        @DisplayName("폰이 직선으로 이동할 경우 도착 위치에 말이 있다면 이동이 불가능하다.")
        void canNotMoveStraightWhenPieceExistInTarget() {
            Position source = Position.of(1, 1);
            assertAll(
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 2),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(1, 2), WHITE_QUEEN.getPiece()))).isFalse(),

                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 2),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(1, 2), BLACK_QUEEN.getPiece()))).isFalse());
        }


        // p*** <- target(p, P)
        // q***
        // p*** <- source
        @Test
        @DisplayName("폰이 직선으로 이동할 경우 도착 위치 전의 경로에 말이 있다면 이동이 불가능하다.")
        void canNotMoveStraightWhenPieceExistInPath() {
            Position source = Position.of(1, 1);
            assertAll(
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 3),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(1, 2), BLACK_QUEEN.getPiece()))).isFalse(),
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 3),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(1, 2), WHITE_QUEEN.getPiece()))).isFalse());
        }

        // *p*p <- target
        // **p* <- source
        @Test
        @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 같은 색의 말인 경우 이동이 불가능하다.")
        void canNotMoveDiagonalWhenTargetIsSameColor() {
            Position source = Position.of(3, 1);
            assertAll(
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(2, 2),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(2, 2), WHITE_QUEEN.getPiece()))).isFalse(),
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(4, 2),
                            Map.of(source, WHITE_PAWN.getPiece(),
                                    Position.of(4, 2), WHITE_QUEEN.getPiece()))).isFalse());
        }

        // *E*E <- target(EMPTY)
        // **p* <- source
        @Test
        @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 비어있을 경우 이동이 불가능하다.")
        void canNotMoveDiagonalWhenTargetIsEmpty() {
            assertAll(
                    () -> assertThat(WHITE_PAWN.getPiece().canMove(Position.of(3, 1), Position.of(2, 2),
                            Map.of(Position.of(3, 1), WHITE_PAWN.getPiece()))).isFalse(),

                    () -> assertThat(
                            WHITE_PAWN.getPiece().canMove(Position.of(3, 1), Position.of(4, 2),
                                    Map.of(Position.of(3, 1), WHITE_PAWN.getPiece()))).isFalse());
        }
    }

    @Nested
    class KnightMove {

        /*
         * ........
         * ........
         * ..E.E...
         * .E...E..
         * ...k....
         * .E...E..
         * ..E.E...
         * ........
         * */
        @ParameterizedTest
        @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
        @DisplayName("나이트는 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsEmpty(int file, int rank) {
            assertThat(WHITE_KNIGHT.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
        }

        /*
         * ........
         * ........
         * ..*.*...
         * .*...*..
         * ...k....
         * .*...*..
         * ..*.*...
         * ........
         * */
        @ParameterizedTest
        @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
        @DisplayName("나이트는 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsOtherColor(int file, int rank) {
            assertThat(WHITE_KNIGHT.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
        }

        /*
         * ........
         * ........
         * ..k.k...
         * .k...k..
         * ...k....
         * .k...k..
         * ..k.k...
         * ........
         * */
        @ParameterizedTest
        @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
        @DisplayName("나이트는 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenTargetIsSameColor(int file, int rank) {
            assertThat(WHITE_KNIGHT.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(4, 4), WHITE_KNIGHT.getPiece(),
                            Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
        }
    }

    @Nested
    class BishopMove {

        /*
         * .......*
         * *.....*.
         * .*...*...
         * ..*.*...
         * ...k....
         * ..*.*...
         * .*...*..
         * *.....*.
         * */
        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7"})
        @DisplayName("비숍은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsEmpty(int file, int rank) {
            assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
        }

        /*
         * .......R
         * R.....R.
         * .R...R...
         * ..R.R...
         * ...k....
         * ..R.R...
         * .R...R..
         * R.....R.
         * */
        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7"})
        @DisplayName("비숍은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsOtherColor(int file, int rank) {
            assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
        }

        /*
         * .......r
         * r.....r.
         * .r...r...
         * ..r.r...
         * ...k....
         * ..r.r...
         * .r...r..
         * r.....r.
         * */
        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7"})
        @DisplayName("비숍은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenTargetIsSameColor(int file, int rank) {
            assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(4, 4), WHITE_BISHOP.getPiece(),
                            Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
        }

        /*
         * .......r
         * ........
         * .....P...
         * ........
         * ...k....
         * ........
         * ........
         * ........
         * */
        @Test
        @DisplayName("비숍은 이동 경로에 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenPieceExistIn() {
            assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(8, 8),
                    Map.of(Position.of(6, 6), WHITE_QUEEN.getPiece()))).isFalse();
        }
    }

    @Nested
    class RookMove {

        /*
         * ...E....
         * ...E....
         * ...E.....
         * ...E....
         * EEErEEEE
         * ...E....
         * ...E....
         * ...E....
         * */
        @ParameterizedTest
        @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("룩은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsEmpty(int file, int rank) {
            assertThat(WHITE_ROOK.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
        }

        /*
         * ...R....
         * ...R....
         * ...R.....
         * ...R....
         * RRRrRRRR
         * ...R....
         * ...R....
         * ...R....
         * */
        @ParameterizedTest
        @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("룩은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsOtherColor(int file, int rank) {
            assertThat(WHITE_ROOK.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
        }

        /*
         * ...p....
         * ...p....
         * ...p.....
         * ...p....
         * ppprpppp
         * ...p....
         * ...p....
         * ...p....
         * */
        @ParameterizedTest
        @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("룩은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenTargetIsSameColor(int file, int rank) {
            assertThat(WHITE_ROOK.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(4, 4), WHITE_ROOK.getPiece(),
                            Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
        }

        /*
         * ........
         * ........
         * .........
         * ........
         * ...r...p
         * ........
         * ........
         * ........
         * */
        @Test
        @DisplayName("룩은 이동 경로에 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenPieceExistIn() {
            assertThat(WHITE_ROOK.getPiece().canMove(Position.of(4, 4), Position.of(8, 4),
                    Map.of(Position.of(5, 4), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }

    @Nested
    class QueenMove {

        /*
         * ...E...E
         * E..E..E.
         * .E.E.E...
         * ..EEE...
         * EEEqEEEE
         * ..EEE...
         * .E.E.E..
         * E..E..E.
         * */
        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7",
                "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("퀸은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsEmpty(int file, int rank) {
            assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
        }

        /*
         * ...R...R
         * R..R..R.
         * .R.R.R...
         * ..RRR...
         * RRRqRRRR
         * ..RRR...
         * .R.R.R..
         * R..R..R.
         * */
        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7",
                "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("퀸은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsOtherColor(int file, int rank) {
            assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
        }

        /*
         * ...r...r
         * r..r..r.
         * .r.r.r...
         * ..rrr...
         * rrrqrrrr
         * ..rrr...
         * .r.r.r..
         * r..r..r.
         * */
        @ParameterizedTest
        @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
                "7,1", "6,2", "3,5", "2,6", "1,7",
                "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
                "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
        @DisplayName("퀸은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenTargetIsSameColor(int file, int rank) {
            assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(4, 4), piece,
                            Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
        }

        /*
         * .......Q
         * ........
         * .....R..
         * ........
         * ...q....
         * ........
         * ........
         * ........
         * */
        @Test
        @DisplayName("퀸은 이동 경로에 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenPieceExistIn() {
            assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(8, 8),
                    Map.of(Position.of(6, 6), WHITE_QUEEN.getPiece()))).isFalse();
        }
    }

    @Nested
    class KingMove {

        /*
         * ........
         * ........
         * ........
         * ..EEE...
         * ..EkE...
         * ..EEE...
         * ........
         * ........
         * */
        @ParameterizedTest
        @CsvSource({"3,3", "5,5",
                "3,5",
                "5,4", "3,4",
                "4,3", "4,5",})
        @DisplayName("킹은 도착 위치가 비어있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsEmpty(int file, int rank) {
            assertThat(WHITE_KING.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
        }

        /*
         * ........
         * ........
         * ........
         * ..QQQ...
         * ..QkQ...
         * ..QQQ...
         * ........
         * ........
         * */
        @ParameterizedTest
        @CsvSource({"3,3", "5,5",
                "3,5", "5,3",
                "5,4", "3,4",
                "4,3", "4,5",})
        @DisplayName("킹은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
        void canMoveWhenTargetIsOtherColor(int file, int rank) {
            assertThat(WHITE_KING.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
        }

        /*
         * ........
         * ........
         * ........
         * ..qqq...
         * ..qkq...
         * ..qqq...
         * ........
         * ........
         * */
        @ParameterizedTest
        @CsvSource({"3,3", "5,5",
                "3,5", "5,3",
                "5,4", "3,4",
                "4,3", "4,5",})
        @DisplayName("킹은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
        void canNotMoveWhenTargetIsSameColor(int rank, int file) {
            assertThat(WHITE_KING.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                    Map.of(Position.of(4, 4), WHITE_KING.getPiece(),
                            Position.of(file, rank), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse();
        }
    }
}

