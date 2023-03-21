package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.Board;
import domain.board.Line;
import domain.path.PathValidator;
import domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    private static final Location WHITE_PAWN_START = Location.of(1, 1);
    private static final Location WHITE_PAWN_END = Location.of(1, 2);
    private static final Location BLACK_PAWN_START = Location.of(1, 6);
    private static final Location BLACK_PAWN_END = Location.of(1, 5);

    @Test
    @DisplayName("초기 상태의 체스판을 생성한다.")
    public void testCreate() {
        //given
        //when
        final Board board = new Board(new PathValidator());
        board.initialize();

        //then
        assertThat(board).extracting("lines")
            .asList()
            .containsExactly(
                Line.whiteBack(),
                Line.whiteFront(),
                Line.empty(),
                Line.empty(),
                Line.empty(),
                Line.empty(),
                Line.blackFront(),
                Line.blackBack()
            );
    }

    @Nested
    @DisplayName("순서가 아닌 진영의 돌을 움직일 시 오류를 던진다.")
    class TestMoveInvalidLocation {

        @DisplayName("흰 진영 순서일 때 검은 진영을 움직인다.")
        @Test
        public void testMoveFailBlack() {
            final Board board = new Board(new PathValidator());
            board.initialize();

            assertThatThrownBy(
                () -> board.moveWhite(BLACK_PAWN_START, BLACK_PAWN_END)
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("검은 진영 순서일 때 흰 진영을 움직인다.")
        @Test
        public void testMoveFailWhite() {
            final Board board = new Board(new PathValidator());
            board.initialize();

            assertThatThrownBy(
                () -> board.moveBlack(WHITE_PAWN_START, WHITE_PAWN_END)
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("순서인 진영의 돌을 움직인다.")
    class TestMoveValidateLocation {

        @Test
        @DisplayName("흰생 진영의 순서일 때 흰색 진영의 돌을 움직인다.")
        public void testMoveWhite() {
            //given
            final Board board = new Board(new PathValidator());
            board.initialize();

            //when
            board.moveWhite(WHITE_PAWN_START, WHITE_PAWN_END);

            //then
            assertThat(board.findSquare(WHITE_PAWN_START).isNotEmpty()).isFalse();
            assertThat(board.findSquare(WHITE_PAWN_END).getPiece()).isEqualTo(Pawn.makeWhite());
        }

        @Test
        @DisplayName("검은색 진영의 순서일 때 검은색 진영의 돌을 움직인다.")
        public void testMoveBlack() {
            //given
            final Board board = new Board(new PathValidator());
            board.initialize();

            //when
            board.moveBlack(BLACK_PAWN_START, BLACK_PAWN_END);

            //then
            assertThat(board.findSquare(BLACK_PAWN_START).isNotEmpty()).isFalse();
            assertThat(board.findSquare(BLACK_PAWN_END).getPiece()).isEqualTo(Pawn.makeBlack());
        }
    }
}
