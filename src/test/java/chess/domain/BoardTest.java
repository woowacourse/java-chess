package chess.domain;

import chess.domain.piece.Team;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.WHITE_SOURCE;
import static chess.domain.PositionFixture.WHITE_TARGET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @DisplayName("기물이 source에서 target으로 이동하는 기능 테스트")
    @Test
    void movePiece1() {
        Board board = BoardFixture.setup();

        Board newBoard = board.movePiece(WHITE_SOURCE, WHITE_TARGET, Team.WHITE);
        var cells = newBoard.cells();

        assertThat(cells.containsKey(WHITE_TARGET)).isTrue();
    }

    @DisplayName("기물이 source에서 target으로 이동하는 기능 테스트")
    @Test
    void movePiece2() {
        Board board = BoardFixture.setup();

        Board newBoard = board.movePiece(WHITE_SOURCE, WHITE_TARGET, Team.WHITE);
        var cells = newBoard.cells();

        assertThat(cells.containsKey(WHITE_SOURCE)).isFalse();
    }

    @DisplayName("기물이 source에서 target으로 이동하는 경로에 기물이 있을 경우 에러 테스트")
    @Test
    void invalidPath1() {
        Board board = BoardFixture.setup();
        assertThatThrownBy(() -> board.movePiece(new Position(File.A, Rank.ONE), new Position(File.A, Rank.SIX), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source 위치에 기물이 존재하지 않는 경우 에러 테스트")
    @Test
    void notExistInSource() {
        Board board = BoardFixture.setup();
        assertThatThrownBy(() -> board.movePiece(new Position(File.A, Rank.ONE), new Position(File.A, Rank.SIX), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("target 위치에 같은 팀 기물이 있는 경우 에러 테스트")
    @Test
    void sameTeamInTarget() {
        Board board = BoardFixture.setup();
        assertThatThrownBy(() -> board.movePiece(new Position(File.A, Rank.ONE), new Position(File.A, Rank.TWO), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
