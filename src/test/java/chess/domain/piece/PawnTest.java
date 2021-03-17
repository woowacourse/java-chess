package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {


    @ParameterizedTest
    @CsvSource(value = {"1:1:false", "2:2:false", "2:1:false", "1:2:true", "1:3:true"}, delimiter = ':')
    @DisplayName("흰색 진영 폰의 이동 조건 테스트")
    void testMovable_white(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(1, 1);
        Position targetPosition = Position.of(x, y);
        Pawn pawn = new Pawn(TeamColor.WHITE);

        //when
        boolean movable = pawn.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }

    @ParameterizedTest
    @CsvSource(value = {"6:6:false", "5:5:false", "5:6:false", "6:5:true", "6:4:true"}, delimiter = ':')
    @DisplayName("검은색 진영 폰의 이동 조건 테스트")
    void testMovable_black(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(6, 6);
        Position targetPosition = Position.of(x, y);
        Pawn pawn = new Pawn(TeamColor.BLACK);

        //when
        boolean movable = pawn.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2:true", "1:3:false"}, delimiter = ':')
    @DisplayName("moved에 따른 폰의 이동 조건 테스트")
    void testMovable_moved(int x, int y, boolean expectedMovable) {
        //given
        Position currentPosition = Position.of(1, 1);
        Position targetPosition = Position.of(x, y);
        Pawn pawn = new Pawn(TeamColor.WHITE);
        pawn.changeMoveState();

        //when
        boolean movable = pawn.movable(currentPosition, targetPosition);

        //then
        assertThat(movable).isEqualTo(expectedMovable);
    }
}