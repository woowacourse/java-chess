package chess.board.piece;

import chess.board.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("비숍 움직일 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"3,-3,true", "3,-2,false"})
    void canMove(int fileVariation, int rankVariation, boolean expect) {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.canMove(new Vector(fileVariation, rankVariation))).isEqualTo(expect);
    }

    @DisplayName("대각선 방향에 따라 방향객체 리스트 생성")
    @ParameterizedTest
    @CsvSource(value = {"1,1,RIGHT_UP", "1,-1,RIGHT_DOWN", "-1,1,LEFT_UP", "-1,-1,LEFT_DOWN"})
    void findPath(int fileUnit, int rankUnit, Direction expect) {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.findPath(new Vector(fileUnit, rankUnit)))
                .containsExactly(expect);
    }
}