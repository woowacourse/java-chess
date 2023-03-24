package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.pieces.component.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Test
    @DisplayName("킹의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatThrownBy(() -> new King(team));
    }

    @Test
    @DisplayName("canMoveStep()은 현재 위치와 목표 위치를 받아 step이 1이면 true를 반환한다.")
    void canMoveStep_test_true() {
        Position position = new Position(Rank.FIVE, File.F);
        Direction direction = Direction.DOWN;
        List<Piece> pieces = List.of(new King(Team.WHITE));
        King king = new King(Team.BLACK);

        king.checkStep(position, direction, pieces);
    }

    @Test
    @DisplayName("canMoveStep()은 현재 위치와 목표 위치를 받아 step이 1이 아니면 false를 반환한다.")
    void canMoveStep_test_false() {
        Position position = new Position(Rank.FIVE, File.F);
        Direction direction = Direction.DOWN;
        List<Piece> pieces = List.of(new King(Team.WHITE),new King(Team.WHITE));
        King king = new King(Team.BLACK);

        assertThatThrownBy(() -> king.checkStep(position, direction, pieces));
    }
}
