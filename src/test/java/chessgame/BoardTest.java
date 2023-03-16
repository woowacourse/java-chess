package chessgame;

import static chessgame.PointFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.domain.Board;
import chessgame.util.ChessBoardFactory;

class BoardTest {
    @Test
    @DisplayName("모든 좌표값을 가진 보드를 생성한다")
    void Should_NotThrowException_When_ConstructBord() {
        assertDoesNotThrow(() -> new Board(ChessBoardFactory.create()));
    }

    @Test
    @DisplayName("소스에 기물이 있는지 확인하다.")
    void Should_True_When_SourcePointHasPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThat(board.checkSource(A1)).isTrue();
    }

    @Test
    @DisplayName("타겟에 기물이 있는지 확인하다.")
    void Should_True_When_TargetPointHasPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThat(board.checkTarget(F7)).isTrue();
    }

    @Test
    @DisplayName("기물의 이동 경로가 다른 기물에 의해 막혀있는지 확인")
    void Should_False_When_RouteBlockedByPiece() {
        Board board = new Board(ChessBoardFactory.create());

        assertThat(board.checkRoute(A1, A8)).isFalse();
    }

}
