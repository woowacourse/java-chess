package chess.domain;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = Board.create();
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