package chess.service;

import static chess.service.ChessBoardFixture.createServiceOfEmpty;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dto.CommandRequest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {

    private ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        chessGameService = new ChessGameService(createServiceOfEmpty());
    }

    @DisplayName("이미 실행중인 게임을 시작 시 예외를 발생시킨다.")
    @Test
    void 게임_시작_실행중_예외() {
        chessGameService.start(1);

        assertThatThrownBy(() -> chessGameService.start(1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 게임이 실행중입니다.");
    }

    @DisplayName("대기중인 게임에 이동 요청 시 예외를 발생시킨다.")
    @Test
    void 게임_이동_대기중_예외() {
        assertThatThrownBy(
                () -> chessGameService.move(CommandRequest.fromMoveCommand("move", List.of(1, 2), List.of(1, 3))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임 대기 상태이거나, 오버된 게임입니다.");
    }

    @DisplayName("오버 상태인 게임에 이동 요청 시 예외를 발생시킨다.")
    @Test
    void 게임_이동_오버_예외() {
        chessGameService = new ChessGameService(ChessBoardFixture.createServiceOfOverBoard());

        assertThatThrownBy(
                () -> chessGameService.move(CommandRequest.fromMoveCommand("move", List.of(1, 2), List.of(1, 3))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임 대기 상태이거나, 오버된 게임입니다.");
    }

    @DisplayName("대기중인 게임에 종료 요청 시 예외를 발생시킨다.")
    @Test
    void 게임_종료_대기중_예외() {
        assertThatThrownBy(
                () -> chessGameService.end(CommandRequest.fromControlCommand("end")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 게임 대기 상태입니다.");
    }

    @DisplayName("오버 상태가 아닌 게임에 결과 요청 시 예외를 발생시킨다.")
    @Test
    void 게임_결과_오버아님_예외() {
        assertThatThrownBy(
                () -> chessGameService.computeResult(CommandRequest.fromControlCommand("status")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임 오버 시에만 결과를 확인할 수 있습니다.");
    }

}
