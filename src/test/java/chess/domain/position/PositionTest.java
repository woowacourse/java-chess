package chess.domain.position;

import chess.domain.Team;
import chess.domain.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionTest {
    @Test
    @DisplayName("Poistion을 생성하면, Position 인스턴스가 생성된다.")
    void create() {
        int row = 1;
        int col = 2;
        Position position = new Position(row, col);
        assertThat(position).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("같은 Col인지 확인")
    void sameCol() {
        Position position = new Position(1, 3);
        assertTrue(position.isSameCol(3));
    }

    @Test
    @DisplayName("팀에따른 pawn의 초기위치 확인")
    void isInitPawnPositionByTeam() {
        Pawn whitePawn = Pawn.white(0);
        assertTrue(whitePawn.position().isSameInitPawnPositionByTeam(Team.WHITE));

        Pawn blackPawn = Pawn.black(0);
        assertTrue(blackPawn.position().isSameInitPawnPositionByTeam(Team.BLACK));
    }

    @Test
    @DisplayName("주어진 col, row 에 따라 다음 위치를 구한다.")
    void next() {
        Position position = new Position(1, 2);
        Position nextPosition = position.next(0, 1);
        assertThat(nextPosition).isEqualTo(new Position(1, 3));
    }

    @Test
    @DisplayName("현재 위치가 범위를 벗어나는지 확인")
    void outOfRange() {
        Position position = new Position(-1, 3);
        assertTrue(position.isOutOfRange());
    }
}
