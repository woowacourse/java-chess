package chess.domain;

import chess.domain.piece.Team;
import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.postion.File.A;
import static chess.domain.postion.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @DisplayName("기물이 source에서 target으로 이동하는 기능 테스트")
    @Test
    void movePiece1() {
        Board board = BoardFixture.setup();

        Board newBoard = board.movePiece(new Position(A, TWO), new Position(A, THREE), Team.WHITE);
        var cells = newBoard.cells();

        assertThat(cells.containsKey(new Position(A, THREE))).isTrue();
    }

    @DisplayName("기물이 source에서 target으로 이동하는 기능 테스트")
    @Test
    void movePiece2() {
        Board board = BoardFixture.setup();

        Board newBoard = board.movePiece(new Position(A, TWO), new Position(A, THREE), Team.WHITE);
        var cells = newBoard.cells();

        assertThat(cells.containsKey(new Position(A, TWO))).isFalse();
    }

    @DisplayName("기물이 source에서 target으로 이동하는 경로에 기물이 있을 경우 에러 테스트")
    @Test
    void invalidPath1() {
        Board board = BoardFixture.setup();
        assertThatThrownBy(() -> board.movePiece(new Position(A, ONE), new Position(A, SIX), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source 위치에 기물이 존재하지 않는 경우 에러 테스트")
    @Test
    void notExistInSource() {
        Board board = BoardFixture.setup();
        assertThatThrownBy(() -> board.movePiece(new Position(A, ONE), new Position(A, SIX), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치에 같은 팀 기물이 있는 경우 에러 테스트")
    @Test
    void sameTeamInTarget() {
        Board board = BoardFixture.setup();
        assertThatThrownBy(() -> board.movePiece(new Position(A, ONE), new Position(A, TWO), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
