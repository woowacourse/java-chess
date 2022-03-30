package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.strategy.PawnMoveStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,TWO,A,THREE,TOP",
            "A,SEVEN,A,SIX,DOWN",
            "A,TWO,B,TWO,RIGHT",
            "B,TWO,A,TWO,LEFT",
            "A,TWO,B,THREE,TOP_RIGHT",
            "B,TWO,A,THREE,TOP_LEFT",
            "A,SEVEN,B,SIX,DOWN_RIGHT",
            "B,SEVEN,A,SIX,DOWN_LEFT",
            "B,ONE,C,THREE,TOP_TOP_RIGHT",
            "B,ONE,D,TWO,RIGHT_RIGHT_TOP",
            "B,TWO,D,ONE,RIGHT_RIGHT_DOWN",
            "B,THREE,C,ONE,DOWN_DOWN_RIGHT",
            "B,THREE,A,ONE,DOWN_DOWN_LEFT",
            "C,TWO,A,ONE,LEFT_LEFT_DOWN",
            "C,TWO,A,THREE,LEFT_LEFT_TOP",
            "B,ONE,A,THREE,TOP_TOP_LEFT"
    })
    @DisplayName("체스 말이 거리가 같은지 확인할 수 있다.")
    void isSameDistance(Column columnA, Rank rankA, Column columnB, Rank rankB, Direction direction) {

        assertThat(direction.isSameDistance(
                new Position(columnA, rankA),
                new Position(columnB, rankB)))
                .isTrue();
    }

    @Test
    @DisplayName("위치 두개로 방향 값을 가져올 수 있다.")
    void getDirection() {
        assertThat(Direction.of(new Position(Column.A, Rank.ONE),
                new Position(Column.C, Rank.THREE)))
                .isEqualTo(Direction.TOP_RIGHT);

    }

    @Test
    @DisplayName("진영에 따라서 말의 이동방향이 뒤집힌다.")
    void pieceDirectionReversed() {
        List<Direction> directions = PawnMoveStrategy.pawnDirection(Team.BLACK);

        assertThat(directions).containsAll(
                List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,TWO,A,THREE,TOP",
            "A,SEVEN,A,SIX,DOWN",
            "A,TWO,B,TWO,RIGHT",
            "B,TWO,A,TWO,LEFT"
    })
    @DisplayName("체스 말이 해당 방향으로 가는지 확인할 수 있다.")
    void isSameDirection(Column columnA, Rank rankA, Column columnB, Rank rankB, Direction direction) {
        assertThat(direction.isSameDirection(
                new Position(columnA, rankA),
                new Position(columnB, rankB)))
                .isTrue();
    }
}
