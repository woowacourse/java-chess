package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopMoveStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,B,TWO",
            "B,ONE,A,TWO",
            "B,TWO,A,ONE",
            "A,TWO,B,ONE",
            "A,ONE,C,THREE",
            "C,ONE,A,THREE",
            "C,THREE,A,ONE",
            "A,THREE,C,ONE"
    })
    @DisplayName("비숍이 갈 수 있는 위치 중 하나여야 한다.")
    void canValidMove(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertDoesNotThrow(() -> new BishopMoveStrategy()
                .isValidateCanMove(Team.WHITE, new Bishop(Team.WHITE),
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                )
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,B,THREE"
    })
    @DisplayName("비숍이 갈 수 있는 위치가 아니면 에러가 발생해야 한다.")
    void canInvalidMove(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertThatThrownBy(() -> new BishopMoveStrategy()
                .isValidateCanMove(Team.WHITE,new Bishop(Team.WHITE),
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("비숍이 이동하는 경로를 반환할 수 있다.")
    void getRoute() {
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();

        List<Position> route = bishopMoveStrategy.getRoute(
                new Position(Column.C, Rank.ONE), new Position(Column.G, Rank.FIVE));

        assertThat(route).containsAll(
                List.of(new Position(Column.D, Rank.TWO),
                        new Position(Column.E, Rank.THREE),
                        new Position(Column.F, Rank.FOUR)
                ));
    }
}
