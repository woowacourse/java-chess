package chess.domain;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.RookMoveRule;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookMoveRuleTest {

    @Test
    void 룩_무브_실패() {
        MoveRule moveRule = new RookMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_B, Rank.RANK2);

        assertThatThrownBy(() -> moveRule.possibleRoute(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩은 직선상으로만 움직일 수 있습니다.");
    }

    @Test
    void 룩의_중간경로를_반환한다() {
        MoveRule moveRule = new RookMoveRule();
        Position currentPosition = Position.of(File.FILE_A, Rank.RANK1);
        Position nextPosition = Position.of(File.FILE_D, Rank.RANK1);

        List<Position> routePositions = moveRule.possibleRoute(currentPosition, nextPosition);
        List<Position> expected = List.of(Position.of(File.FILE_B, Rank.RANK1), Position.of(File.FILE_C, Rank.RANK1));
        assertThat(routePositions.containsAll(expected)).isTrue();
    }
}