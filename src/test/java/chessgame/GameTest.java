package chessgame;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.controller.Command;
import chessgame.domain.Game;

class GameTest {
    Game game;

    @BeforeEach
    void before() {
        game = new Game();
        game.setFrom(Command.of("start"));
    }

    @Test
    @DisplayName("검은팀 턴에 흰팀 기물을 이동하려고 할 때 실패 테스트")
    void Should_ThrowException_When_BlackPieceTurn() {
        game.setFrom(Command.of("move a2 a4"));

        assertThatThrownBy(() -> game.setFrom(Command.of("move b2 b3")))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("검은팀 기물만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("흰팀 턴에 검은팀 기물을 이동하려고 할 때 실패 테스트")
    void Should_ThrowException_When_WhitePieceTurn() {
        assertThatThrownBy(() -> game.setFrom(Command.of("move h8 h7")))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("흰팀 기물만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("이동 좌표에 자기팀 기물이 있을 경우 실패 테스트")
    void Should_ThrowException_When_CatchMyTeamPiece() {
        assertThatThrownBy(() -> game.setFrom(Command.of("move h1 g1")))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("자기팀 기물을 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("기물이 없는 좌표에서 이동하려고 할 때 실패 테스트")
    void Should_ThrowException_When_NotExistPiece() {
        assertThatThrownBy(() -> game.setFrom(Command.of("move h4 h6")))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 좌표에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("폰이 공격 대상이 없는 좌표로 대각선 이동 시 실패 테스트")
    void Should_ThrowException_When_ImpossibleMove() {
        assertThatThrownBy(() -> game.setFrom(Command.of("move b2 c3")))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("폰은 이동시 앞으로만 한칸 이동 가능하나, 처음은 두칸도 가능합니다. 공격시엔 앞방향 대각선으로 한칸 이동가능 합니다.");
    }

    @Test
    @DisplayName("폰이 전진 시 상대 기물이 있을 경우 실패 테스트")
    void Should_ThrowException_When_PawnAttackStraight() {
        game.setFrom(Command.of("move a2 a4"));
        game.setFrom(Command.of("move a7 a5"));

        assertThatThrownBy(() -> game.setFrom(Command.of("move a4 a5")))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("폰은 이동시 앞으로만 한칸 이동 가능하나, 처음은 두칸도 가능합니다. 공격시엔 앞방향 대각선으로 한칸 이동가능 합니다.");
    }
}
