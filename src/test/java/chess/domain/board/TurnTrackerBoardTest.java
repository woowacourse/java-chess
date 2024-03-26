package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTrackerBoardTest {

    @Test
    @DisplayName("게임의 순서를 책임질 색과 보드를 가지는 보드를 생성할 수 있다.")
    void Given_TurnTrackerBoard_When_CreateWithColorBoard_Then_DoesNotAnyThrowException() {
        //given, when, then
        assertThatCode(() -> new TurnTrackerBoard(BoardFactory.create(), Color.WHITE))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("백색 순서에서 흑색 말을 이동시킬 경우 예외가 발생한다.")
    void Given_TurnTrackerBoardWithWhiteColor_When_MoveBlackPiece_Then_Exception() {
        //given
        TurnTrackerBoard turnTrackerBoard = new TurnTrackerBoard(BoardFactory.create(), Color.WHITE);
        //when, then
        assertThatThrownBy(() -> turnTrackerBoard.move(new Position(2, 7), new Position(2, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대 팀의 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("백색 순서에서 이동할 경우 흑색 말을 이동할 수 있다.")
    void Given_TurnTrackerBoardWithWhiteColor_When_MoveWhitePieceAndBlackPiece_Then_DoesNotAnyThrowException() {
        //given
        TurnTrackerBoard turnTrackerBoard = new TurnTrackerBoard(BoardFactory.create(), Color.WHITE);
        //when
        TurnTrackerBoard blackTurnTrackerBoard = turnTrackerBoard.move(new Position(1, 2), new Position(1, 3));
        //when, then
        assertThatCode(() -> blackTurnTrackerBoard.move(new Position(2, 7), new Position(2, 5)))
                .doesNotThrowAnyException();
    }
}
