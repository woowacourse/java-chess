package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.exception.WrongDirectionException;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = Board.create();
    }

    @Test
    @DisplayName("흰색 기물의 위치와 흰색을 입력받으면 true를 리턴한다.")
    void return_true_when_white_piece_and_white_color() {
        Assertions.assertThat(board.isPieceTurn(Square.of(File.A, Rank.TWO), Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("흰색 기물의 위치와 검은색을 입력받으면 false를 리턴한다.")
    void return_false_when_white_piece_and_black_color() {
        Assertions.assertThat(board.isPieceTurn(Square.of(File.A, Rank.TWO), Color.BLACK)).isFalse();
    }

    @Test
    @DisplayName("검은색 기물의 위치와 검은색을 입력받으면 true를 리턴한다.")
    void return_true_when_black_piece_and_black_color() {
        Assertions.assertThat(board.isPieceTurn(Square.of(File.A, Rank.SEVEN), Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("검은색 기물의 위치와 흰색을 입력받으면 false를 리턴한다.")
    void return_false_when_black_piece_and_white_color() {
        Assertions.assertThat(board.isPieceTurn(Square.of(File.A, Rank.SEVEN), Color.WHITE)).isFalse();
    }

    @Test
    @DisplayName("빈자리 이동 시켰을 때 예외를 발생시킨다.")
    void empty_space_move_fail() {
        assertThatThrownBy(() -> board.move(Square.of(File.A, Rank.THREE), Square.of(File.A, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
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
        @DisplayName("적이 없는 경우에는 대각 이동 불가능")
        void pawn_cannot_move_diagonal_when_enemy_exist() {
            assertThatThrownBy(() -> board.move(Square.of(File.B, Rank.TWO), Square.of(File.A, Rank.THREE)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰은 적이 있을 때만 대각선으로 이동할 수 있습니다.");
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

    @Nested
    @DisplayName("룩")
    class Rook {

        private Square whiteLeftRook;

        @BeforeEach
        void setup() {
            whiteLeftRook = Square.of(File.A, Rank.ONE);
            board.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
            board.move(Square.of(File.B, Rank.SEVEN), Square.of(File.B, Rank.FIVE));
            board.move(Square.of(File.A, Rank.FOUR), Square.of(File.B, Rank.FIVE));
            board.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE));
        }

        @Test
        @DisplayName("이동 성공")
        void move_success() {
            assertThatCode(() -> board.move(whiteLeftRook, Square.of(File.A, Rank.FOUR)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적 제거 성공")
        void move_success_when_enemy_exists() {
            assertThatCode(() -> board.move(whiteLeftRook, Square.of(File.A, Rank.FIVE)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("아군 위치로는 이동 실패")
        void move_fail_when_ally_exists() {
            assertThatThrownBy(() -> board.move(whiteLeftRook, Square.of(File.B, Rank.ONE)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("잘못된 위치로는 이동 실패")
        void move_fail() {
            assertThatThrownBy(() -> board.move(whiteLeftRook, Square.of(File.B, Rank.THREE)))
                    .isInstanceOf(WrongDirectionException.class);
        }

        @Test
        @DisplayName("길막인 경우 이동 실패")
        void move_fail_when_block() {
            assertThatThrownBy(() -> board.move(whiteLeftRook, Square.of(File.A, Rank.SIX)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 다른 기물을 뛰어 넘을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("나이트")
    class Knight {

        private Square whiteLeftKnight;

        @BeforeEach
        void setup() {
            whiteLeftKnight = Square.of(File.B, Rank.ONE);
        }

        @Test
        @DisplayName("이동 성공")
        void move_success() {
            assertThatCode(() -> board.move(whiteLeftKnight, Square.of(File.A, Rank.THREE)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적 제거 성공")
        void move_success_when_enemy_exists() {
            assertThatCode(() -> {
                board.move(whiteLeftKnight, Square.of(File.A, Rank.THREE));
                board.move(Square.of(File.B, Rank.SEVEN), Square.of(File.B, Rank.FIVE));
                board.move(Square.of(File.A, Rank.THREE), Square.of(File.B, Rank.FIVE));
            }).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("아군 위치로는 이동 실패")
        void move_fail_when_ally_exists() {
            assertThatThrownBy(() -> board.move(whiteLeftKnight, Square.of(File.D, Rank.TWO)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("잘못된 위치로는 이동 실패")
        void move_fail() {
            assertThatThrownBy(() -> board.move(whiteLeftKnight, Square.of(File.B, Rank.THREE)))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }

    @Nested
    @DisplayName("비숍")
    class Bishop {

        private Square whiteLeftBishop;

        @BeforeEach
        void setup() {
            whiteLeftBishop = Square.of(File.C, Rank.ONE);
            board.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
            board.move(Square.of(File.H, Rank.SEVEN), Square.of(File.H, Rank.SIX));
        }

        @Test
        @DisplayName("이동 성공")
        void move_success() {
            assertThatCode(() -> board.move(whiteLeftBishop, Square.of(File.F, Rank.FOUR)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적 제거 성공")
        void move_success_when_enemy_exists() {
            assertThatCode(() -> board.move(whiteLeftBishop, Square.of(File.H, Rank.SIX)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("아군 위치로는 이동 실패")
        void move_fail_when_ally_exists() {
            assertThatThrownBy(() -> board.move(whiteLeftBishop, Square.of(File.B, Rank.TWO)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("잘못된 위치로는 이동 실패")
        void move_fail() {
            assertThatThrownBy(() -> board.move(whiteLeftBishop, Square.of(File.D, Rank.THREE)))
                    .isInstanceOf(WrongDirectionException.class);
        }

        @Test
        @DisplayName("길막인 경우 이동 실패")
        void move_fail_when_block() {
            assertThatThrownBy(() -> board.move(whiteLeftBishop, Square.of(File.A, Rank.THREE)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 다른 기물을 뛰어 넘을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("킹")
    class King {

        private Square whiteKing;

        @BeforeEach
        void setup() {
            whiteKing = Square.of(File.E, Rank.ONE);
            board.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
            board.move(Square.of(File.E, Rank.SEVEN), Square.of(File.E, Rank.FIVE));
            board.move(Square.of(File.D, Rank.FOUR), Square.of(File.E, Rank.FIVE));
            board.move(Square.of(File.F, Rank.EIGHT), Square.of(File.B, Rank.FOUR));
        }

        @Test
        @DisplayName("이동 성공")
        void move_success() {
            assertThatCode(() -> board.move(whiteKing, Square.of(File.D, Rank.TWO)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적 제거 성공")
        void move_success_when_enemy_exists() {
            assertThatCode(() -> {
                board.move(whiteKing, Square.of(File.D, Rank.TWO));
                board.move(Square.of(File.B, Rank.FOUR), Square.of(File.C, Rank.THREE));
                board.move(Square.of(File.D, Rank.TWO), Square.of(File.C, Rank.THREE));
            }).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("아군 위치로는 이동 실패")
        void move_fail_when_ally_exists() {
            assertThatThrownBy(() -> board.move(whiteKing, Square.of(File.D, Rank.ONE)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("잘못된 위치로는 이동 실패")
        void move_fail() {
            assertThatThrownBy(() -> board.move(whiteKing, Square.of(File.C, Rank.THREE)))
                    .isInstanceOf(WrongDirectionException.class);
        }
    }

    @Nested
    @DisplayName("퀸")
    class Queen {

        private Square whiteQueen;

        @BeforeEach
        void setup() {
            whiteQueen = Square.of(File.C, Rank.TWO);
            board.move(Square.of(File.C, Rank.TWO), Square.of(File.C, Rank.FOUR));
            board.move(Square.of(File.D, Rank.SEVEN), Square.of(File.D, Rank.FIVE));
            board.move(Square.of(File.D, Rank.ONE), Square.of(File.C, Rank.TWO));
            board.move(Square.of(File.D, Rank.FIVE), Square.of(File.C, Rank.FOUR));
        }

        @Test
        @DisplayName("이동 성공")
        void move_success() {
            assertThatCode(() -> board.move(whiteQueen, Square.of(File.F, Rank.FIVE)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("적 제거 성공")
        void move_success_when_enemy_exists() {
            assertThatCode(() -> board.move(whiteQueen, Square.of(File.C, Rank.FOUR)))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("아군 위치로는 이동 실패")
        void move_fail_when_ally_exists() {
            assertThatThrownBy(() -> board.move(whiteQueen, Square.of(File.D, Rank.TWO)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("아군 기물이 있는 곳으로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("잘못된 위치로는 이동 실패")
        void move_fail() {
            assertThatThrownBy(() -> board.move(whiteQueen, Square.of(File.A, Rank.THREE)))
                    .isInstanceOf(WrongDirectionException.class);
        }

        @Test
        @DisplayName("길막인 경우 이동 실패")
        void move_fail_when_block() {
            assertThatThrownBy(() -> board.move(whiteQueen, Square.of(File.C, Rank.SIX)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 기물은 다른 기물을 뛰어 넘을 수 없습니다.");
        }
    }
}
