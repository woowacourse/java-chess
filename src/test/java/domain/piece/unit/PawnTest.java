package domain.piece.unit;

import static domain.PositionFixtures.*;
import static domain.piece.property.Team.*;

import domain.position.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @ParameterizedTest
    @MethodSource("availableWhitePositions")
    @DisplayName("WhitePawn 은 앞으로 한 칸, 앞으로 두 칸(시작시), 대각선으로 한 칸(적이있을 때) 이동할 수 있다.")
    void moveWhitePawn(Position target) {
        Piece piece = new Pawn(WHITE);

        Assertions.assertThat(piece.availableMove(B2, target)).isEqualTo(true);
    }

    private static Stream<Position> availableWhitePositions() {
        return Stream.of(A3, B3, B4, C3);
    }

    @ParameterizedTest
    @MethodSource("unavailableWhitePositions")
    @DisplayName("WhitePawn 은 앞으로 한 칸, 앞으로 두 칸(시작시), 대각선으로 한 칸(적이있을 때) 이외에 이동할 수 없다.")
    void moveWhitePawnUnablePositions(Position target) {
        Piece piece = new Pawn(WHITE);

        Assertions.assertThat(piece.availableMove(B2, target)).isEqualTo(false);
    }

    private static Stream<Position> unavailableWhitePositions() {
        return Stream.of(A2, A4, C2, C4);
    }

    @ParameterizedTest
    @MethodSource("availableBlackPositions")
    @DisplayName("BlackPawn 은 앞으로 한 칸, 대각선으로 한 칸(적이있을 때) 이동할 수 있다.")
    void moveBlackPawn(Position target) {
        Piece piece = new Pawn(BLACK);

        Assertions.assertThat(piece.availableMove(B7, target)).isEqualTo(true);
    }

    private static Stream<Position> availableBlackPositions() {
        return Stream.of(A6, B6, B5, C6);
    }

    @ParameterizedTest
    @MethodSource("unavailableBlackPositions")
    @DisplayName("BlackPawn 은 앞으로 한 칸, 대각선으로 한 칸(적이있을 때) 이외에 이동할 수 없다.")
    void moveBlackPawnUnablePositions(Position target) {
        Piece piece = new Pawn(BLACK);

        Assertions.assertThat(piece.availableMove(B7, target)).isEqualTo(false);
    }

    private static Stream<Position> unavailableBlackPositions() {
        return Stream.of(A5, A7, C5, C7);
    }

    @Test
    @DisplayName("White Pawn 은 처음 출발할 때 앞으로 두 칸 이동할 수 있다.")
    void moveWhitePawnFirst() {
        Piece piece = new Pawn(WHITE);

        Assertions.assertThat(piece.availableMove(B2, B4)).isEqualTo(true);
    }

    @Test
    @DisplayName("Black Pawn 은 처음 출발할 때 앞으로 두 칸 이동할 수 있다.")
    void moveBlackPawnFirst() {
        Piece piece = new Pawn(BLACK);

        Assertions.assertThat(piece.availableMove(B7, B5)).isEqualTo(true);
    }


    @Test
    @DisplayName("White Pawn 은 처음 출발할 때가 아니라면 앞으로 두 칸 이동할 수 없다.")
    void moveWhitePawnNotFirst() {
        Piece piece = new Pawn(WHITE);

        Assertions.assertThat(piece.availableMove(B3, B5)).isEqualTo(false);
    }

    @Test
    @DisplayName("Black Pawn 은 처음 출발할 때가 아니라면 앞으로 두 칸 이동할 수 없다.")
    void moveBlackPawnNotFirst() {
        Piece piece = new Pawn(BLACK);

        Assertions.assertThat(piece.availableMove(B6, B4)).isEqualTo(false);
    }
}
