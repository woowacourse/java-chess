package domain.piece.unit;

import domain.piece.property.Team;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @ParameterizedTest
    @MethodSource("availableWhitePositions")
    @DisplayName("WhitePawn 은 앞으로 한 칸, 대각선으로 한 칸(적이있을 때) 이동할 수 있다.")
    void moveWhitePawn(Position target) {
        Piece piece = new Pawn(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availableWhitePositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.THREE),
                Position.of(XPosition.B, YPosition.THREE),
                Position.of(XPosition.C, YPosition.THREE)
        );
    }

    @ParameterizedTest
    @MethodSource("unavailableWhitePositions")
    @DisplayName("WhitePawn 은 앞으로 한 칸, 대각선으로 한 칸(적이있을 때) 이외에 이동할 수 없다.")
    void moveWhitePawnUnablePositions(Position target) {
        Piece piece = new Pawn(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO), target))
                .isEqualTo(false);
    }

    private static Stream<Position> unavailableWhitePositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.FOUR),
                Position.of(XPosition.C, YPosition.FOUR),
                Position.of(XPosition.A, YPosition.TWO),
                Position.of(XPosition.C, YPosition.TWO)
        );
    }

    @ParameterizedTest
    @MethodSource("availableBlackPositions")
    @DisplayName("BlackPawn 은 앞으로 한 칸, 대각선으로 한 칸(적이있을 때) 이동할 수 있다.")
    void moveBlackPawn(Position target) {
        Piece piece = new Pawn(Team.BLACK);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.SEVEN), target))
                .isEqualTo(true);
    }

    private static Stream<Position> availableBlackPositions() {
        return Stream.of(
                Position.of(XPosition.A, YPosition.SIX),
                Position.of(XPosition.B, YPosition.SIX),
                Position.of(XPosition.C, YPosition.SIX)
        );
    }

    @ParameterizedTest
    @MethodSource("unavailableBlackPositions")
    @DisplayName("BlackPawn 은 앞으로 한 칸, 대각선으로 한 칸(적이있을 때) 이외에 이동할 수 없다.")
    void moveBlackPawnUnablePositions(Position target) {
        Piece piece = new Pawn(Team.BLACK);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.D, YPosition.FIVE), target))
                .isEqualTo(false);
    }

    private static Stream<Position> unavailableBlackPositions() {
        return Stream.of(
                Position.of(XPosition.C, YPosition.FIVE),
                Position.of(XPosition.E, YPosition.FIVE),
                Position.of(XPosition.C, YPosition.THREE),
                Position.of(XPosition.E, YPosition.THREE)
        );
    }


    @Test
    @DisplayName("Black Pawn 은 처음 출발할 때 앞으로 두 칸 이동할 수 있다.")
    void moveBlackPawnFirst() {
        Piece piece = new Pawn(Team.BLACK);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.SEVEN),
                        Position.of(XPosition.B, YPosition.FIVE)))
                .isEqualTo(true);
    }


    @Test
    @DisplayName("White Pawn 은 처음 출발할 때 앞으로 두 칸 이동할 수 있다.")
    void moveWhitePawnFirst() {
        Piece piece = new Pawn(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.TWO),
                        Position.of(XPosition.B, YPosition.FOUR)))
                .isEqualTo(true);
    }

    @Test
    @DisplayName("White Pawn 은 처음 출발할 때가 아니라면 앞으로 두 칸 이동할 수 없다.")
    void moveWhitePawnNotFirst() {
        Piece piece = new Pawn(Team.WHITE);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.D, YPosition.THREE),
                        Position.of(XPosition.D, YPosition.FIVE)))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Black Pawn 은 처음 출발할 때가 아니라면 앞으로 두 칸 이동할 수 없다.")
    void moveBlackPawnNotFirst() {
        Piece piece = new Pawn(Team.BLACK);

        Assertions.assertThat(piece.availableMove(Position.of(XPosition.B, YPosition.SIX),
                        Position.of(XPosition.B, YPosition.FOUR)))
                .isEqualTo(false);
    }
}
