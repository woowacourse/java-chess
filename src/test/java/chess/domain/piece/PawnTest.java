package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import chess.exception.ImpossibleMoveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("흰색 폰의 초기 이동 조건 테스트")
    void testAddMovable_white() {
        //given
        Pawn pawn = new Pawn(TeamColor.WHITE, Position.of(3, 1));
        List<Position> existPiecePositions = Collections.singletonList(
                Position.of(4, 2)
        );
        List<Position> enemiesPositions = Collections.singletonList(
                Position.of(4, 2)
        );
        List<Position> expectedPosition = new ArrayList<>();
        expectedPosition.add(Position.of(3, 3));
        expectedPosition.add(Position.of(3, 2));
        expectedPosition.add(Position.of(4, 2));
        expectedPosition.addAll(enemiesPositions);

        //when
        pawn.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = pawn.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }

    @Test
    @DisplayName("검은색 폰의 초기 이동 조건 테스트")
    void testAddMovable_black() {
        //given
        Pawn pawn = new Pawn(TeamColor.BLACK, Position.of(3, 6));
        List<Position> existPiecePositions = Collections.singletonList(
                Position.of(4, 5)
        );
        List<Position> enemiesPositions = Collections.singletonList(
                Position.of(4, 5)
        );
        List<Position> expectedPosition = new ArrayList<>();
        expectedPosition.add(Position.of(3, 5));
        expectedPosition.add(Position.of(3, 4));
        expectedPosition.add(Position.of(4, 5));
        expectedPosition.addAll(enemiesPositions);

        //when
        pawn.addMovablePositions(existPiecePositions, enemiesPositions);
        List<Position> movablePosition = pawn.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }

    @Test
    @DisplayName("흰색 폰이 한번 움직인 뒤 조건 테스트")
    void testAddMovable_moved() throws ImpossibleMoveException {
        //given
        Pawn pawn = new Pawn(TeamColor.WHITE, Position.of(3, 2));
        ArrayList<Position> positions = new ArrayList<>();
        pawn.addMovablePositions(positions, positions);
        pawn.changePosition(Position.of(3, 3));

        List<Position> expectedPosition = new ArrayList<>();
        expectedPosition.add(Position.of(3, 4));

        //when
        pawn.addMovablePositions(positions, positions);
        List<Position> movablePosition = pawn.movablePositions();

        //then
        assertThat(movablePosition).hasSameElementsAs(expectedPosition);
    }
}