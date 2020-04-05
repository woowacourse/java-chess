package chess.domain;

import chess.controller.dto.TileDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessRunnerTest {
    ChessRunner chessRunner;

    @BeforeEach
    void setUp() {
        chessRunner = new ChessRunner();
    }

    @DisplayName("현재 순서가 아닐 때 에러 메시지 출력")
    @Test
    void validateCurrentTeam() {
        assertThatThrownBy(() -> chessRunner.update("a7", "a5")) //블랙 팀 폰 이동
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }

    @DisplayName("같은 위치로 이동할 때 에러 메시지 출력")
    @Test
    void validateSamePosition() {
        assertThatThrownBy(() -> chessRunner.update("a2", "a2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 위치로 이동할 수 없습니다.");
    }

    @DisplayName("선택한 기물에 맞지 않는 목적지를 선택했을 때 에레 메시지 출력")
    @Test
    void validateMovable() {
        assertThatThrownBy(() -> chessRunner.update("a1", "b2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선택한 기물이 이동할 수 없는 곳입니다.");
    }

    @DisplayName("경로 사이에 장애물이 있을 때 에러 메시지 출력")
    @Test
    void validateObstacle() {
        assertThatThrownBy(() -> chessRunner.update("a1", "a3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로 사이에 장애물이 있습니다.");
    }

    @DisplayName("잘못된 목적지를 선택했을 때 에러 메시지 출력")
    @Test
    void validateTarget() {
        assertThatThrownBy(() -> chessRunner.update("a1", "a2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적지가 잘못되었습니다.");
    }

    @DisplayName("점수 계산 테스트")
    @Test
    void calculateScoreTest() {
        assertThat(chessRunner.calculateScore()).isEqualTo(38d);
    }

    @DisplayName("게임이 종료되었는지 검사")
    @Test
    void isEndChessTest() {
        assertThat(chessRunner.isEndChess()).isFalse();
    }


    @Test
    void tileDtosTest() {
        List<TileDto> tileDtos = chessRunner.tileDtos();
    }

    @DisplayName("게임이 종료되지 않았을 때 승자를 출력 시 빈 문자열 출력")
    @Test
    void getWinnerTest() {
        assertThat(chessRunner.getWinner()).isEqualTo(StringUtils.EMPTY);
    }
}