package chessgame;

import static chessgame.point.PointFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Board;
import chessgame.domain.Team;
import chessgame.factory.ChessBoardFactory;

class BoardTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBord() {
        assertDoesNotThrow(() -> new Board(ChessBoardFactory.create()));
    }

    @Test
    @DisplayName("소스에 기물이 있는지 확인하다.")
    void Should_ThrowException_When_SourcePointHasPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(()->board.move(A3,A4,Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 좌표에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("해당 팀에 해당하는 기물만 움직이는지 확인한다.")
    void Should_ThrowException_When_WhiteCanMove() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(()->board.move(A7,A5,Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("흰팀 기물만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("해당 팀에 해당하는 기물은 잡을 수 없습니다.")
    void Should_ThrowException_When_SameTarget() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(()->board.move(A1,A2,Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자기팀 기물을 잡을 수 없습니다.");
    }

    @Test
    @DisplayName("기물의 이동 경로가 다른 기물에 의해 막혀있는지 확인합니다.")
    void Should_False_When_RouteBlockedByPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(()->board.move(A1,A2,Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("폰이 공격 대상이 없는 좌표로 대각선 이동 시 실패 테스트")
    void Should_ThrowException_When_ImpossibleMove() {
        Board board = new Board(ChessBoardFactory.create());

        assertThatThrownBy(() -> board.move(B2, C3,Team.WHITE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("불가능한 움직임 입니다.");
    }


    @Test
    @DisplayName("폰이 전진 시 상대 기물이 있을 경우 실패 테스트")
    void Should_ThrowException_When_PawnAttackStraight() {
        Board board = new Board(ChessBoardFactory.create());

        board.move(A2, A4,Team.WHITE);
        board.move(A7, A5,Team.BLACK);

        assertThatThrownBy(() -> board.move(A4, A5,Team.WHITE))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("폰은 직진으로 적을 잡을수 없습니다.");
    }
    
}
