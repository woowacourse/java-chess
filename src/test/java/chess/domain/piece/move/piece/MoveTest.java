package chess.domain.piece.move.piece;

import chess.domain.piece.Position;
import chess.domain.piece.move.Direction;
import chess.domain.piece.move.Location;
import chess.domain.piece.move.LocationHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.piece.move.Direction.DOWN;
import static chess.domain.piece.move.Direction.DOWN_LEFT;
import static chess.domain.piece.move.Direction.DOWN_RIGHT;
import static chess.domain.piece.move.Direction.LEFT;
import static chess.domain.piece.move.Direction.RIGHT;
import static chess.domain.piece.move.Direction.UP;
import static chess.domain.piece.move.Direction.UP_LEFT;
import static chess.domain.piece.move.Direction.UP_RIGHT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoveTest {

    @Test
    @DisplayName("비숍이 이동할 수 있는 모든 위치를 반환한다")
    void bishop_getAllPositions() {
        // given
        final Position source = new Position(3, 3);
        final int moveCount = 8;
        final List<Direction> directions = List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
        final Location expected = LocationHelper.getBishopLocation();

        // when
        final Location actual = Move.getLocation(source, directions, moveCount);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("룩이 이동할 수 있는 모든 위치를 반환한다")
    void rook_getAllPositions() {
        // given
        final Position source = new Position(3, 3);
        final int moveCount = 8;
        final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT);
        final Location expected = LocationHelper.getRookLocation();

        // when
        final Location actual = Move.getLocation(source, directions, moveCount);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("퀸이 이동할 수 있는 모든 위치를 반환한다")
    void queen_getAllPositions() {
        // given
        final Position source = new Position(3, 3);
        final int moveCount = 8;
        final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
        final Location expected = LocationHelper.getQueenLocation();

        // when
        final Location actual = Move.getLocation(source, directions, moveCount);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("퀸이 이동할 수 있는 모든 위치를 반환한다")
    void king_getAllPositions() {
        // given
        final Position source = new Position(3, 3);
        final int moveCount = 1;
        final List<Direction> directions = List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
        final Location expected = LocationHelper.getKingLocation();

        // when
        final Location actual = Move.getLocation(source, directions, moveCount);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("폰이 이동할 수 있는 모든 위치를 반환한다")
    void pawn_getAllPositions() {
        // given
        final Position source = new Position(3, 3);
        final int moveCount = 2;
        final List<Direction> whiteDirections = List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT);
        final Location whiteExpected = LocationHelper.getWhitePawnLocation();
        final List<Direction> blackDirections = List.of(DOWN, DOWN_LEFT, DOWN_RIGHT);
        final Location blackExpected = LocationHelper.getBlackPawnLocation();

        // when
        final Location whiteActual = Move.getLocation(source, whiteDirections, moveCount);
        final Location blackActual = Move.getLocation(source, blackDirections, moveCount);

        // then
        assertThat(whiteExpected)
                .isEqualTo(whiteActual);

        assertThat(blackExpected)
                .isEqualTo(blackActual);
    }
}
