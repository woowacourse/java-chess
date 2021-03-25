package chess.domain.piece.direction;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceDirectionsTest {

    @Test
    @DisplayName("Piece가 없는 Position으로 이동 가능한지 테스트")
    void testAddPositionsNotExistPiece() {
        //given
        Position currentPosition = Position.of(0, 0);
        RookDirections rookDirections = new RookDirections();
        List<Position> existPiecePositions = Arrays.asList(
                Position.of(0, 3),
                Position.of(3, 0)
        );
        List<Position> movablePositions = new ArrayList<>();
        List<Position> actualMovablePositions = Arrays.asList(
                Position.of(0, 1),
                Position.of(0, 2),
                Position.of(1, 0),
                Position.of(2, 0)
        );

        //when
        rookDirections.addPositionsNotExistPiece(currentPosition, existPiecePositions, movablePositions);

        //than
        assertThat(movablePositions).isEqualTo(actualMovablePositions);
    }

    @Test
    @DisplayName("상대방 Piece를 kill가능할 때 테스트")
    void testAddKillPositions() {
        //given
        Position currentPosition = Position.of(0, 0);
        RookDirections rookDirections = new RookDirections();
        List<Position> enemyPositions = Arrays.asList(
                Position.of(0, 3),
                Position.of(3, 0)
        );
        List<Position> expectedKillPositions = new ArrayList<>();
        List<Position> actualKillPositions = Arrays.asList(
                Position.of(0, 3),
                Position.of(3, 0)
        );

        //when
        rookDirections.addKillPositions(currentPosition, enemyPositions, expectedKillPositions);

        //than
        assertThat(expectedKillPositions).isEqualTo(actualKillPositions);
    }
}