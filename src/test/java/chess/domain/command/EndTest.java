package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.GameResultBySide;
import chess.domain.board.ResultCalculator;
import chess.domain.board.ScoreBySide;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("게임 종료 상태에서 시작 시 예외를 던진다.")
    void start() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));

        // when, then
        assertThatThrownBy(() -> end.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 이동 시 예외를 던진다.")
    void move() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> end.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 종료 시 예외를 던진다.")
    void end() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));

        // when, then
        assertThatThrownBy(() -> end.end())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 종료할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 게임 결과 출력 시 예외를 던진다.")
    void printGameResult() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));
        // when, then
        assertThatThrownBy(() -> end.printGameResult())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 게임 결과 상태로 전이될 수 없습니다.");
    }


    @Test
    @DisplayName("게임 종료 상태에서 기물들을 가져올 시 예외를 던진다.")
    void getPieces() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));

        // when, then
        assertThatThrownBy(() -> end.getPieces())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임 종료 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 턴 이름을 가져올 시 예외를 던진다.")
    void getTurnDisplayName() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));

        // when, then
        assertThatThrownBy(() -> end.getTurnDisplayName())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 종료 상태에서는 턴 이름을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료 상태에서 진영별 점수를 가져올 수 있다.")
    void getScoreBySide() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));

        // when, then
        Assertions.assertDoesNotThrow(() -> end.getScoreBySide());
    }

    @Test
    @DisplayName("게임 종료 상태에서 진영별 결과를 가져올 수 있다.")
    void getGameResultBySide() {
        // given
        End end = new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));

        // when, then
        Assertions.assertDoesNotThrow(() -> end.getGameResultBySide());
    }
}
