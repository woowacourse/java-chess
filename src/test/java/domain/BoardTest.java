package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Pawn;
import domain.piece.Piece;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    private static final Location WHITE_PAWN_START = Location.of(2, 2);
    private static final Location WHITE_PAWN_END = Location.of(2, 3);
    private static final Location BLACK_PAWN_START = Location.of(2, 7);
    private static final Location BLACK_PAWN_END = Location.of(2, 6);

    @Test
    @DisplayName("초기 상태의 체스판을 생성한다.")
    public void testCreate() {
        //given
        //when
        final Board board = new Board(new HashMap<>(), new ScoreCalculator());
        board.initialize();

        //then
        for (int column = 1; column <= 8; column++) {
            for (int row = 1; row <= 8; row++) {
                final Piece piece = BoardSetting.findPiece(Location.of(column, row));
                assertThat(board.findPiece(Location.of(column, row))).isEqualTo(piece);
            }
        }
    }

    @Nested
    @DisplayName("순서가 아닌 진영의 돌을 움직일 시 오류를 던진다.")
    class TestMoveInvalidLocation {

        @DisplayName("흰 진영 순서일 때 검은 진영을 움직인다.")
        @Test
        public void testMoveFailBlack() {
            final Board board = new Board(new HashMap<>(), new ScoreCalculator());
            board.initialize();

            assertThatThrownBy(
                () -> board.moveWhite(BLACK_PAWN_START, BLACK_PAWN_END)
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("검은 진영 순서일 때 흰 진영을 움직인다.")
        @Test
        public void testMoveFailWhite() {
            final Board board = new Board(new HashMap<>(), new ScoreCalculator());
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
            final Board board = new Board(new HashMap<>(), new ScoreCalculator());
            board.initialize();

            //when
            board.moveWhite(WHITE_PAWN_START, WHITE_PAWN_END);

            //then
            assertThat(board.findPiece(WHITE_PAWN_START).isNotEmpty()).isFalse();
            assertThat(board.findPiece(WHITE_PAWN_END)).isEqualTo(Pawn.makeWhite());
        }

        @Test
        @DisplayName("검은색 진영의 순서일 때 검은색 진영의 돌을 움직인다.")
        public void testMoveBlack() {
            //given
            final Board board = new Board(new HashMap<>(), new ScoreCalculator());
            board.initialize();

            //when
            board.moveBlack(BLACK_PAWN_START, BLACK_PAWN_END);

            //then
            assertThat(board.findPiece(BLACK_PAWN_START).isNotEmpty()).isFalse();
            assertThat(board.findPiece(BLACK_PAWN_END)).isEqualTo(Pawn.makeBlack());
        }
    }

    @Test
    @DisplayName("초기 상태에서 흰색 돌 점수를 계산한다.")
    public void testCalculateWhiteScore() {
        //given
        final Board board = new Board(new HashMap<>(), new ScoreCalculator());
        board.initialize();

        //when
        final double result = board.calculateWhiteScore();

        //then
        assertThat(result).isEqualTo(38D);
    }

    @Test
    @DisplayName("초기 상태에서 검은색 돌 점수를 계산한다.")
    public void testCalculateBlackScore() {
        //given
        final Board board = new Board(new HashMap<>(), new ScoreCalculator());
        board.initialize();

        //when
        final double result = board.calculateBlackScore();

        //then
        assertThat(result).isEqualTo(38D);
    }
}
