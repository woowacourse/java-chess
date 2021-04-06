package chess.domain.piece;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.PieceDirection;
import chess.domain.Position;
import chess.domain.PositionInformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("기물들이 있을 때 하얀 폰의 움직임 테스트 (처음 턴)")
    void movablePositions_white() {
        List<Position> expectedMovablePositions = Arrays.asList(
            Position.of(3, 2),
            Position.of(3, 3)
        );

        Pawn pawn = new Pawn(WHITE, Position.of(3, 1));

        pawn.updateMovablePositions(new ArrayList<>());

        Assertions.assertThat(pawn.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("기물들이 있을 때 하얀 폰의 움직임 테스트 (두번 째 턴)")
    void movablePositions_white_secondTurn() {
        List<Position> expectedMovablePositions =
            Collections.singletonList(Position.of(3, 3));

        Pawn pawn = new Pawn(WHITE, Position.of(3, 1));

        pawn.updateMovablePositions(new ArrayList<>());
        Position targetPosition = pawn.currentPosition()
            .go(PieceDirection.forwardDirection(WHITE).get(0));
        pawn.move(targetPosition);

        pawn.updateMovablePositions(new ArrayList<>());

        Assertions.assertThat(pawn.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("기물들이 있을 때 하얀 폰이 죽일 수 있는 범위 테스트)")
    void killablePositions_white() {
        List<Position> expectedMovablePositions =
            Collections.singletonList(Position.of(4, 2));

        Pawn pawn = new Pawn(WHITE, Position.of(3, 1));

        List<PositionInformation> positionInformation = Arrays.asList(
            new PositionInformation(Position.of(3, 2), WHITE),
            new PositionInformation(Position.of(4, 2), BLACK)
        );

        pawn.updateMovablePositions(positionInformation);

        Assertions.assertThat(pawn.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("기물들이 있을 때 검은 폰의 움직임 테스트 (처음 턴)")
    void movablePositions_black() {
        List<Position> expectedMovablePositions = Arrays.asList(
            Position.of(3, 5),
            Position.of(3, 4)
        );

        Pawn pawn = new Pawn(BLACK, Position.of(3, 6));

        pawn.updateMovablePositions(new ArrayList<>());

        Assertions.assertThat(pawn.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("기물들이 있을 때 검은 폰의 움직임 테스트 (두번 째 턴)")
    void movablePositions_black_secondTurn() {
        List<Position> expectedMovablePositions =
            Collections.singletonList(Position.of(3, 4));

        Pawn pawn = new Pawn(BLACK, Position.of(3, 6));

        pawn.updateMovablePositions(new ArrayList<>());

        Position targetPosition = pawn.currentPosition()
            .go(PieceDirection.forwardDirection(BLACK).get(0));
        pawn.move(targetPosition);
        pawn.updateMovablePositions(new ArrayList<>());

        Assertions.assertThat(pawn.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }

    @Test
    @DisplayName("기물들이 있을 때 검은 폰이 죽일 수 있는 범위 테스트)")
    void killablePositions_black() {
        List<Position> expectedMovablePositions =
            Collections.singletonList(Position.of(4, 5));

        Pawn pawn = new Pawn(BLACK, Position.of(3, 6));

        List<PositionInformation> positionInformation = Arrays.asList(
            new PositionInformation(Position.of(3, 5), WHITE),
            new PositionInformation(Position.of(4, 5), WHITE)
        );

        pawn.updateMovablePositions(positionInformation);

        Assertions.assertThat(pawn.movablePositions()).hasSameElementsAs(expectedMovablePositions);
    }
}