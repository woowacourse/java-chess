package chess.domain.piece;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @ParameterizedTest
    @DisplayName("체스말 이름을 올바르게 가져온다.")
    @MethodSource("checkNameParameter")
    void checkName(Piece piece, String name) {
        assertThat(piece.getName()).isEqualTo(name);
    }

    private static Stream<Arguments> checkNameParameter() {
        return Stream.of(
                Arguments.arguments(new King(BLACK), "K"),
                Arguments.arguments(new King(WHITE), "k"),
                Arguments.arguments(new Rook(BLACK), "R"),
                Arguments.arguments(new Rook(WHITE), "r"),
                Arguments.arguments(new Pawn(BLACK), "P"),
                Arguments.arguments(new Pawn(WHITE), "p"),
                Arguments.arguments(new Bishop(BLACK), "B"),
                Arguments.arguments(new Bishop(WHITE), "b"),
                Arguments.arguments(new Knight(BLACK), "N"),
                Arguments.arguments(new Knight(WHITE), "n"),
                Arguments.arguments(new Queen(BLACK), "Q"),
                Arguments.arguments(new Queen(WHITE), "q")
        );
    }

    @ParameterizedTest
    @DisplayName("체스말이 검정팀인지 확인한다.")
    @MethodSource("blackPieceTeamParameter")
    void isBlack(Piece piece, boolean result) {
        Assertions.assertThat(piece.isBlack()).isEqualTo(result);
    }

    public static Stream<Arguments> blackPieceTeamParameter() {
        return Stream.of(
                Arguments.arguments(new Pawn(BLACK), true),
                Arguments.arguments(new King(WHITE), false)
        );
    }

    @ParameterizedTest
    @DisplayName("체스말이 흰팀인지 확인한다.")
    @MethodSource("whitePieceTeamParameter")
    void isWhite(Piece piece, boolean result) {
        Assertions.assertThat(piece.isWhite()).isEqualTo(result);
    }

    public static Stream<Arguments> whitePieceTeamParameter() {
        return Stream.of(
                Arguments.arguments(new Pawn(WHITE), true),
                Arguments.arguments(new King(BLACK), false)
        );
    }

    @Test
    @DisplayName("체스말이 처음 움직이는지 확인한다.")
    void isFirst() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isFirst()).isTrue();
    }

    @Test
    @DisplayName("체스말이 처음 움직였을 때 상태를 변경할 수 있는지 확인한다.")
    void changeNotFirst() {
        Pawn pawn = new Pawn(WHITE);
        pawn.changeNotFirst();
        assertThat(pawn.isFirst()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("체스말이 같은 팀인지 확인한다.")
    @MethodSource
    void isSameColor(Piece piece, Team team, boolean result) {
        Assertions.assertThat(piece.isSameTeam(team)).isEqualTo(result);
    }

    public static Stream<Arguments> isSameColor() {
        return Stream.of(
                Arguments.arguments(new Pawn(BLACK), BLACK, true),
                Arguments.arguments(new Pawn(WHITE), WHITE, true),
                Arguments.arguments(new Pawn(BLACK), WHITE, false),
                Arguments.arguments(new Pawn(WHITE), BLACK, false)
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
                Arguments.arguments(new Rook(WHITE), Direction.U),
                Arguments.arguments(new Knight(WHITE), Direction.UUR),
                Arguments.arguments(new Bishop(WHITE), Direction.UR),
                Arguments.arguments(new Queen(WHITE), Direction.U),
                Arguments.arguments(new Queen(BLACK), Direction.DL),
                Arguments.arguments(new King(WHITE), Direction.UR),
                Arguments.arguments(new King(WHITE), Direction.R)
        );
    }

    @ParameterizedTest
    @DisplayName("기물별로 이동할 수 없는 방향인지 확인한다.")
    @MethodSource("notMovableDirectionParameter")
    void checkNotMovableDirection(Piece piece, Direction direction) {
        assertThat(piece.isMovableDirection(direction)).isFalse();
    }

    private static Stream<Arguments> notMovableDirectionParameter() {
        return Stream.of(
                Arguments.arguments(new Rook(WHITE), Direction.UR),
                Arguments.arguments(new Knight(WHITE), Direction.U),
                Arguments.arguments(new Bishop(WHITE), Direction.D),
                Arguments.arguments(new Queen(WHITE), Direction.UUR),
                Arguments.arguments(new Queen(BLACK), Direction.DLL),
                Arguments.arguments(new King(WHITE), Direction.UUR),
                Arguments.arguments(new King(WHITE), Direction.DDL)
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
                Arguments.arguments(new Rook(WHITE), new LocationDiff(1, 1)),
                Arguments.arguments(new Knight(WHITE), new LocationDiff(2, 1)),
                Arguments.arguments(new Bishop(WHITE), new LocationDiff(7, 7)),
                Arguments.arguments(new Queen(WHITE), new LocationDiff(0, 7)),
                Arguments.arguments(new King(WHITE), new LocationDiff(-1, 1))
        );
    }

    @DisplayName("기물별로 이동할 수 없는 거리인지 확인한다.")
    @ParameterizedTest
    @MethodSource("notMovableDistanceParameter")
    void checkNotMovableDistance(Piece piece, LocationDiff locationDiff) {
        assertThat(piece.isMovableDistance(locationDiff)).isFalse();
    }

    private static Stream<Arguments> notMovableDistanceParameter() {
        return Stream.of(
                Arguments.arguments(new Knight(WHITE), new LocationDiff(4, 2)),
                Arguments.arguments(new King(WHITE), new LocationDiff(-2, -2))
        );
    }

}
