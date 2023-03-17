package chess.domain;

import chess.domain.piece.exception.WrongDirectionException;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = Board.create();
    }

    @Nested
    @DisplayName("폰")
    class Pawn {

        @Test
        @DisplayName("흰 폰 한 칸 앞으로 이동")
        void white_pawn_move_one_square() {
            Square current = Square.of(File.A, Rank.TWO);
            Square destination = Square.of(File.A, Rank.THREE);
            assertThatCode(() -> board.move(current, destination))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("검은 폰 한 칸 앞으로 이동")
        void black_pawn_move_one_square() {
            Square current = Square.of(File.A, Rank.SEVEN);
            Square destination = Square.of(File.A, Rank.SIX);
            assertThatCode(() -> board.move(current, destination))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적이 있는 경우에만 대각 이동 가능")
        void pawn_can_move_diagonal_when_enemy_exist() {
            board.move(Square.of(File.B, Rank.TWO), Square.of(File.B, Rank.FOUR));
            board.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE));
            assertThatCode(() -> board.move(Square.of(File.B, Rank.FOUR), Square.of(File.A, Rank.FIVE)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적이 없는 경우에만 대각 이동 불가능")
        void pawn_cannot_move_diagonal_when_enemy_exist() {
            assertThatThrownBy(() -> board.move(Square.of(File.B, Rank.TWO), Square.of(File.A, Rank.THREE)))
                    .isInstanceOf(WrongDirectionException.class);
        }

        @Test
        @DisplayName("더블 폰 푸시 이동")
        void double_pawn_push() {
            Square current = Square.of(File.A, Rank.TWO);
            Square destination = Square.of(File.A, Rank.FOUR);
            assertThatCode(() -> board.move(current, destination))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("더블 폰 푸시 이동 실패")
        void double_pawn_push_fail() {
            Square current = Square.of(File.A, Rank.TWO);
            Square nextMove = Square.of(File.A, Rank.THREE);
            Square destination = Square.of(File.A, Rank.FIVE);
            assertThatThrownBy(() -> {
                board.move(current, nextMove);
                board.move(nextMove, destination);
            }).isInstanceOf(WrongDirectionException.class);
        }

        @Test
        @DisplayName("뒤로 가기 불가")
        void back_move_fail() {
            Square current = Square.of(File.A, Rank.TWO);
            Square destination = Square.of(File.A, Rank.ONE);
            assertThatThrownBy(() -> board.move(current, destination))
                    .isInstanceOf(WrongDirectionException.class);
        }
     }

    @Test
    @DisplayName("빈자리 이동 시켰을 때 예외를 발생시킨다.")
    void test() {
        assertThatThrownBy(() -> board.move(Square.of(File.A, Rank.THREE), Square.of(File.A, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰 이동 성공")
    void test2() {
        assertThatCode(() -> board.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트 이동 성공")
    void test3() {
        assertThatCode(() -> board.move(Square.of(File.B, Rank.ONE), Square.of(File.A, Rank.THREE)))
                .doesNotThrowAnyException();
    }
}