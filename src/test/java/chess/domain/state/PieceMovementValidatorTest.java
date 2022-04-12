package chess.domain.state;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceMovementValidatorTest {

    @Test
    @DisplayName("다른 팀일 경우 예외 처리")
    void checkSourceColor_diffColor() {
        Assertions.assertThatThrownBy(() -> PieceMovementValidator.checkSourceColor(new Pawn(Team.BLACK), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 색 기물을 잡으려고 할 경우 예외 처리")
    void checkTarget() {
        Assertions.assertThatThrownBy(() -> PieceMovementValidator.checkTarget(new Pawn(Team.WHITE), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("기물이 움직일 수 없는 방향을 명령하면 예외 처리")
    @MethodSource
    void checkDirection(Piece piece, Direction direction) {
        Assertions.assertThatThrownBy(() -> PieceMovementValidator.checkDirection(piece, direction))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> checkDirection() {
        return Stream.of(
                Arguments.arguments(new Pawn(Team.WHITE), Direction.D),
                Arguments.arguments(new Pawn(Team.BLACK), Direction.U),
                Arguments.arguments(new Rook(Team.WHITE), Direction.UR),
                Arguments.arguments(new Knight(Team.WHITE), Direction.D),
                Arguments.arguments(new Bishop(Team.WHITE), Direction.U),
                Arguments.arguments(new Queen(Team.WHITE), Direction.UUR),
                Arguments.arguments(new King(Team.WHITE), Direction.UUR)
        );
    }

    @ParameterizedTest
    @DisplayName("기물이 움직일 수 없는 거리를 명령하면 예외처리")
    @MethodSource
    void checkDistance(Piece piece, LocationDiff locationDiff) {
        Assertions.assertThatThrownBy(() -> PieceMovementValidator.checkDistance(piece, locationDiff))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> checkDistance() {
        return Stream.of(
                Arguments.arguments(new Pawn(Team.WHITE), new LocationDiff(2, 2)),
                Arguments.arguments(new Pawn(Team.BLACK), new LocationDiff(2, -2)),
                Arguments.arguments(new Knight(Team.WHITE), new LocationDiff(2,4)),
                Arguments.arguments(new King(Team.WHITE), new LocationDiff(2,2))
        );
    }

    @Test
    @DisplayName("기물이 이동하는 경로에 다른 기물이 있으면 예외처리")
    void checkRoute() {
        Assertions.assertThatThrownBy(() -> PieceMovementValidator.checkRoute(List.of(new Pawn(Team.BLACK))))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
