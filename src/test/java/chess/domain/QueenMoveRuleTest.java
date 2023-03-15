package chess.domain;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.QueenMoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenMoveRuleTest {

    @Test
    void 퀸_움직임_실패() {
        MoveRule moveRule = new QueenMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_D, Rank.RANK2);

        assertThatThrownBy(() -> moveRule.possibleRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 대각선 또는 직선 상으로만 움직일 수 있습니다.");
    }

    @Test
    void 퀸의_중간경로를_반환한다() {
        MoveRule moveRule = new QueenMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_A, Rank.RANK4);

        List<Position> routePositions = moveRule.possibleRoute(currentPosition, nextPosition);
        List<Position> expected = List.of(Position.of(File.FILE_A, Rank.RANK2), Position.of(File.FILE_A, Rank.RANK3));
        assertThat(routePositions.containsAll(expected)).isTrue();
    }

}
