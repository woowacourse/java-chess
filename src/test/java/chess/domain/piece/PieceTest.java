package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @ParameterizedTest
    @DisplayName("흑백팀 기물 이름을 올바르게 가져온다.")
    @MethodSource("checkNameParameter")
    void checkName(Piece piece, String name) {
        assertThat(piece.getName()).isEqualTo(name);
    }

    private static Stream<Arguments> checkNameParameter() {
        return Stream.of(
                Arguments.arguments(new King(Team.BLACK), "K"),
                Arguments.arguments(new King(Team.WHITE), "k"),
                Arguments.arguments(new Rook(Team.BLACK), "R"),
                Arguments.arguments(new Rook(Team.WHITE), "r"),
                Arguments.arguments(new Pawn(Team.BLACK), "P"),
                Arguments.arguments(new Pawn(Team.WHITE), "p"),
                Arguments.arguments(new Bishop(Team.BLACK), "B"),
                Arguments.arguments(new Bishop(Team.WHITE), "b"),
                Arguments.arguments(new Knight(Team.BLACK), "N"),
                Arguments.arguments(new Knight(Team.WHITE), "n"),
                Arguments.arguments(new Queen(Team.BLACK), "Q"),
                Arguments.arguments(new Queen(Team.WHITE), "q")
        );
    }

    @ParameterizedTest
    @DisplayName("기물별로 이동할 수 있는 방향인지 확인한다.")
    @MethodSource("movableDirectionParameter")
    void checkMovableDirection(Piece piece, Direction direction) {
        assertThat(piece.isMovableDirection(direction)).isTrue();
    }

    private static Stream<Arguments> movableDirectionParameter() {
        return Stream.of(
                Arguments.arguments(new Rook(Team.WHITE), Direction.U),
                Arguments.arguments(new Knight(Team.WHITE), Direction.UUR),
                Arguments.arguments(new Bishop(Team.WHITE), Direction.UR),
                Arguments.arguments(new Queen(Team.WHITE), Direction.U),
                Arguments.arguments(new Queen(Team.BLACK), Direction.DL),
                Arguments.arguments(new King(Team.WHITE), Direction.UR),
                Arguments.arguments(new King(Team.WHITE), Direction.R)
        );
    }

    @ParameterizedTest
    @DisplayName("기물별로 이동할 수 있는 방향인지 확인한다.")
    @MethodSource("notMovableDirectionParameter")
    void checkNotMovableDirection(Piece piece, Direction direction) {
        assertThat(piece.isMovableDirection(direction)).isFalse();
    }

    private static Stream<Arguments> notMovableDirectionParameter() {
        return Stream.of(
                Arguments.arguments(new Rook(Team.WHITE), Direction.UR),
                Arguments.arguments(new Knight(Team.WHITE), Direction.U),
                Arguments.arguments(new Bishop(Team.WHITE), Direction.D),
                Arguments.arguments(new Queen(Team.WHITE), Direction.UUR),
                Arguments.arguments(new Queen(Team.BLACK), Direction.DLL),
                Arguments.arguments(new King(Team.WHITE), Direction.UUR),
                Arguments.arguments(new King(Team.WHITE), Direction.DDL)
        );
    }

    @DisplayName("기물별로 이동할 수 있는 거리인지 확인한다.")
    @ParameterizedTest
    @MethodSource("checkMovableDistanceParameter")
    void checkMovableDistance(Piece piece, LocationDiff locationDiff) {
        assertThat(piece.isMovableDistance(locationDiff)).isTrue();
    }

    private static Stream<Arguments> checkMovableDistanceParameter() {
        return Stream.of(
                Arguments.arguments(new Rook(Team.WHITE), new LocationDiff(1, 1)),
                Arguments.arguments(new Knight(Team.WHITE), new LocationDiff(2, 1)),
                Arguments.arguments(new Bishop(Team.WHITE), new LocationDiff(7, 7)),
                Arguments.arguments(new Queen(Team.WHITE), new LocationDiff(0, 7)),
                Arguments.arguments(new King(Team.WHITE), new LocationDiff(-1, 1))
        );
    }

    @DisplayName("기물별로 이동할 수 있는 거리인지 확인한다.")
    @ParameterizedTest
    @MethodSource("notMovableDistanceParameter")
    void checkNotMovableDistance(Piece piece, LocationDiff locationDiff) {
        assertThat(piece.isMovableDistance(locationDiff)).isFalse();
    }

    private static Stream<Arguments> notMovableDistanceParameter() {
        return Stream.of(
                Arguments.arguments(new Knight(Team.WHITE), new LocationDiff(4,2)),
                Arguments.arguments(new King(Team.WHITE), new LocationDiff(-2,-2))
        );
    }

}
