package state;

import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.position.PositionFixture.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("블랙 턴은 ")
class BlackTest {

    @DisplayName("플레이 중인 상태이다")
    @Test
    void isPlaying() {
        //given
        final Black black = new Black(Board.create(new InitialChessAlignment()));

        //when

        //then
        Assertions.assertThat(black.isPlaying()).isTrue();
    }

    @DisplayName("검정색 기물을 옮길 수 있다")
    @Test
    void move() {
        //given
        final Board board = Board.create(new InitialChessAlignment());
        final Black black = new Black(board);

        final Position source = A7;
        final Position destination = A6;

        //when
        black.move(source, destination);

        //then
        assertAll(
                () -> Assertions.assertThat(board.getPieces().containsKey(source)).isFalse(),
                () -> Assertions.assertThat(board.getPieces().containsKey(destination)).isTrue());
    }

    @DisplayName("흰색 기물을 옮기면 예외가 발생한다")
    @Test
    void cannotMove() {
        //given
        final Board board = Board.create(new InitialChessAlignment());
        final Black black = new Black(board);

        final Position source = A2;
        final Position destination = A3;

        //when

        //then
        Assertions.assertThatThrownBy(() -> black.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
