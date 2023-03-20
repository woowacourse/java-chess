package chessgame;

import static chessgame.PointFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Board;
import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.util.ChessBoardFactory;

class GameTest {

    Game game;

    @BeforeEach
    void before() {
        game = new Game();
        game.setButton(Command.of("start"));
    }

    @Test
    @DisplayName("폰이 공격 대상이 없는 좌표로 대각선 이동 시 실패 테스트")
    void Should_ThrowException_When_ImpossibleMove() {
        assertThatThrownBy(() -> game.movePiece(List.of(B2, C3)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("불가능한 움직임 입니다.");
    }

    @Test
    @DisplayName("검은팀 턴에 흰팀 기물을 이동하려고 할 때 실패 테스트")
    void Should_ThrowException_When_BlackPieceTurn() {
        game.movePiece(List.of(A2, A4));
        game.movePiece(List.of(B7, B5));
        game.movePiece(List.of(A4, B5));

        assertThatThrownBy(() -> game.movePiece(List.of(B5, C6)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("검은팀 기물만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("폰이 전진 시 상대 기물이 있을 경우 실패 테스트")
    void Should_ThrowException_When_PawnAttackStraight() {
        game.movePiece(List.of(A2, A4));
        game.movePiece(List.of(A7, A5));

        assertThatThrownBy(() -> game.movePiece(List.of(A4, A5)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("폰은 직진으로 적을 잡을수 없습니다.");
    }

    @Test
    @DisplayName("흰팀 턴에 검은팀 기물을 이동하려고 할 때 실패 테스트")
    void Should_ThrowException_When_WhitePieceTurn() {
        assertThatThrownBy(() -> game.movePiece(List.of(H8, H7)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("흰팀 기물만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("기물이 없는 좌표에서 이동하려고 할 때 실패 테스트")
    void Should_ThrowException_When_NotExistPiece() {
        assertThatThrownBy(() -> game.movePiece(List.of(H4, H6)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 좌표에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("이동 좌표에 자기팀 기물이 있을 경우 실패 테스트")
    void Should_ThrowException_When_CatchMyTeamPiece() {
        assertThatThrownBy(() -> game.movePiece(List.of(H1, G1)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("자기팀 기물을 잡을 수 없습니다.");
    }
}
