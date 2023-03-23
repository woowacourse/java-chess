package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.exception.PieceMessage;
import chess.factory.BoardFactory;
import chess.factory.BoardFactoryForTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

    @Test
    @DisplayName("체스 보드 생성")
    void create_success() {
        // when
        Board board = BoardFactory.createBoard();

        // then
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @ParameterizedTest(name = "{0} to {1} is not move")
    @CsvSource(value = {"c3, c3", "c7,c7"})
    @DisplayName("같은 위치로 이동하는 경우 예외가 발생한다.")
    void throws_exception_when_not_move(final String start, final String end) {
        // given
        Board board = BoardFactory.createBoard();
        Position startPosition = Position.from(start);
        Position endPosition = Position.from(end);

        // when & then
        assertThatThrownBy(
                () -> board.switchPosition(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.NOT_MOVE.getMessage());
    }

    @ParameterizedTest(name = "{0} to {1} is moving to team")
    @CsvSource(value = {"c2,b2", "c7,b7"})
    @DisplayName("같은 팀의 위치로 이동하는 경우 예외가 발생한다.")
    void throws_exception_when_move_to_team(final String start, final String end) {
        // given
        Board board = BoardFactory.createBoard();
        Position startPosition = Position.from(start);
        Position endPosition = Position.from(end);

        // when & then
        assertThatThrownBy(
                () -> board.switchPosition(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("체스 말이 정상적으로 움직인다.")
    void move_success() {
        // given
        Board board = BoardFactory.createBoard();
        Position startPosition = Position.from("a2");
        Position endPosition = Position.from("a3");
        // when
        board.switchPosition(startPosition, endPosition);

        // then
        assertThat(board.findPieceFromPosition(Position.from("a3")).getName()).isEqualTo("p");
    }

    @Test
    @DisplayName("lower 폰은 대각선에 있는 적을 공격할 수 있다.")
    void lower_pawn_attack_success() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("b7"), Position.from("b5"));

        // when & then
        assertDoesNotThrow(() -> board.switchPosition(Position.from("a4"), Position.from("b5")));
    }

    @Test
    @DisplayName("upper 폰은 대각선에 있는 적을 공격할 수 있다.")
    void upper_pawn_attack_success() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("b7"), Position.from("b5"));
        board.switchPosition(Position.from("a1"), Position.from("a2"));

        // when & then
        assertDoesNotThrow(() -> board.switchPosition(Position.from("b5"), Position.from("a4")));
    }

    @Test
    @DisplayName("lower 폰은 빈공간에서는 대각선으로 움직일 수 없다.")
    void lower_pawn_does_not_move_diagonal_when_space_is_empty() {
        // given
        Board board = BoardFactory.createBoard();

        // when & then
        assertThatThrownBy(() -> board.switchPosition(Position.from("a2"), Position.from("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.PAWN_INVALID_MOVE.getMessage());
    }

    @Test
    @DisplayName("upper 폰은 빈공간에서는 대각선으로 움직일 수 없다.")
    void upper_pawn_does_not_move_diagonal_when_space_is_empty() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a3"));

        // when & then
        assertThatThrownBy(() -> board.switchPosition(Position.from("a7"), Position.from("b6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.PAWN_INVALID_MOVE.getMessage());
    }

    @Test
    @DisplayName("lower 폰은 직진 공격을 할 수 없다.")
    void lower_pawn_does_not_attack_forward_enemy() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("a7"), Position.from("a5"));

        // when & then
        assertThatThrownBy(() -> board.switchPosition(Position.from("a4"), Position.from("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.PAWN_INVALID_MOVE.getMessage());
    }

    @Test
    @DisplayName("upper 폰은 직진 공격을 할 수 없다.")
    void upper_pawn_does_not_attack_forward_enemy() {
        // given
        Board board = BoardFactory.createBoard();
        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("a7"), Position.from("a5"));
        board.switchPosition(Position.from("a1"), Position.from("a3"));

        // when & then
        assertThatThrownBy(() -> board.switchPosition(Position.from("a5"), Position.from("a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PieceMessage.PAWN_INVALID_MOVE.getMessage());
    }

    @Test
    @DisplayName("소문자 팀 점수 계산 (초기 세팅)")
    void returns_sum_of_lower_team_score() {
        // given
        Board board = BoardFactory.createBoard();

        // when
        double score = board.getScoreOfLowerTeam();

        // then
        assertThat(score).isEqualTo(38);
    }

    @Test
    @DisplayName("대문자 팀 점수 계산 (초기 세팅)")
    void returns_sum_of_upper_team_score() {
        // given
        Board board = BoardFactory.createBoard();

        // when
        double score = board.getScoreOfUpperTeam();

        // then
        assertThat(score).isEqualTo(38);
    }

    @Test
    @DisplayName("소문자 팀 점수 계산 (같은 Column에 폰이 겹치는 경우가 있는 경우 0.5로 계산한다.)")
    void returns_sum_of_lower_team_score_when_pawn_has_same_column() {
        // given
        Board board = BoardFactory.createBoard();

        board.switchPosition(Position.from("a2"), Position.from("a4"));
        board.switchPosition(Position.from("b7"), Position.from("b5"));
        board.switchPosition(Position.from("a4"), Position.from("b5"));
        board.switchPosition(Position.from("d7"), Position.from("d5"));
        board.switchPosition(Position.from("b5"), Position.from("b6"));
        board.switchPosition(Position.from("c7"), Position.from("b6"));
        board.switchPosition(Position.from("a1"), Position.from("a7"));

        // when
        double result = board.getScoreOfLowerTeam();

        // then
        assertThat(result).isEqualTo(37.0);
        assertThat(board.getScoreOfUpperTeam()).isEqualTo(36.0);
    }

    @Test
    @DisplayName("대문자 팀 점수 계산 (같은 Column에 폰이 겹치는 경우가 있는 경우 0.5로 계산한다.)")
    void returns_sum_of_upper_team_score_when_pawn_has_same_column() {
        // given
        Board board = BoardFactory.createBoard();
        double expectedResult = 36;

        board.switchPosition(Position.from("a2"), Position.from("a3"));
        board.switchPosition(Position.from("a7"), Position.from("a5"));
        board.switchPosition(Position.from("b2"), Position.from("b4"));
        board.switchPosition(Position.from("a5"), Position.from("b4"));
        board.switchPosition(Position.from("c2"), Position.from("c3"));
        board.switchPosition(Position.from("d7"), Position.from("d5"));
        board.switchPosition(Position.from("c3"), Position.from("c4"));
        board.switchPosition(Position.from("d5"), Position.from("c4"));

        // when
        double result = board.getScoreOfUpperTeam();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("3단계 요구 사항에 나온 맵 점수 테스트")
    void returns_score_of_example_map() {
        // given
        Board board = BoardFactoryForTest.createBoard();

        // when
        double scoreOfUpperTeam = board.getScoreOfUpperTeam();
        double scoreOfLowerTeam = board.getScoreOfLowerTeam();

        // then
        assertAll(
                () -> assertThat(scoreOfUpperTeam).isEqualTo(20),
                () -> assertThat(scoreOfLowerTeam).isEqualTo(19.5)
        );
     }

     @Test
     @DisplayName("King이 한명이라도 죽으면 게임은 멈춘다.")
     void chess_done_when_king_is_dead() {
         // given
         Board board = BoardFactory.createBoard();
         board.switchPosition(Position.from("e2"), Position.from("e4"));
         board.switchPosition(Position.from("a7"), Position.from("a5"));
         board.switchPosition(Position.from("h2"), Position.from("h4"));
         board.switchPosition(Position.from("a8"), Position.from("a6"));
         board.switchPosition(Position.from("f2"), Position.from("f4"));
         board.switchPosition(Position.from("a6"), Position.from("e6"));
         board.switchPosition(Position.from("e4"), Position.from("e5"));
         board.switchPosition(Position.from("e6"), Position.from("e5"));
         board.switchPosition(Position.from("a2"), Position.from("a3"));
         board.switchPosition(Position.from("e5"), Position.from("e1"));

         // when
         boolean isKingDead = board.isKingDead();

         // then
         assertThat(isKingDead).isTrue();
      }
}
