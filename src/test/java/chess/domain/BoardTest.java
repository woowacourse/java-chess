package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("피스 이동 기능")
    void move() {
        board.move(new Position("a", "2"), new Position("a", "3")); // map의 요소가 바뀜
        assertThat(board.unwrap().get(new Position("a", "2"))).isEqualTo(new Blank());
        assertThat(board.unwrap().get(new Position("a", "3"))).isEqualTo(new Pawn(false));
        OutputView.printCurrentBoard(board.unwrap());
    }

    @ParameterizedTest
    @DisplayName("룩, 비숍, 퀸, 나이트, 킹 이동이 불가능한 경우")
    @ValueSource(strings = {"a,1,a,7", "c,1,h,7", "d,1,d,8", "d,1,a,4", "b,1,c,2", "e,1,d,1"})
    void checkPath(final String input) {
        final String[] inputs = input.split(",");
        assertThatThrownBy(() -> board.move(new Position(inputs[0], inputs[1]), new Position(inputs[2], inputs[3])))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("폰 이동이 불가능한 경우")
    @ValueSource(strings = {"a,2,a,3", "a,2,a,4"})
    void checkPawnPath(final String input) {
        board.unwrap().put(new Position("a", "3"), new Queen(false));
        final String[] inputs = input.split(",");
        assertThatThrownBy(() -> board.move(new Position(inputs[0], inputs[1]), new Position(inputs[2], inputs[3])))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 점수 확인하는 기능")
    void checkScore() {
        boolean isBlack = true;
        assertThat(board.calculateScore(!isBlack)).isEqualTo(38);

        board.unwrap().put(new Position("a", "3"), new Pawn(false));
        assertThat(board.calculateScore(!isBlack)).isEqualTo(38);
        OutputView.printCurrentBoard(board.unwrap());
    }

    @Test
    @DisplayName("킹이 잡혔는지 확인하는 기능")
    void checkDieKing() {
        assertThat(board.isKingDead()).isFalse();
        board.unwrap().put(new Position("e", "1"), new Blank());
        assertThat(board.isKingDead()).isTrue();
    }

    @Test
    @DisplayName("턴에 맞는 위치를 선택했는지 검증하는 기능")
    void isRightTurn() {
        final boolean isBlack = true;
        assertThatThrownBy(() -> board.validateRightTurn(new Position("a", "2"), isBlack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 턴에 맞는 말을 움직이세요.");
    }

    //TODO : blank 오류 확인
//    @Test
//    void name() {
//        board.move(new Position("a", "2"), new Position("a", "4"));
//        assertThatThrownBy(() -> board.move(new Position("a", "3"), new Position("a", "4")))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessage("비어 있는 칸입니다.");
//    }3
}