package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.Player;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PawnTest {

    @ParameterizedTest
    @MethodSource("blackPawnTarget")
    @DisplayName("BlackPawn 은 현재 위치에서 아래로 한 칸 또는 대각선(아래)으로 한칸 이동할 수 있다.")
    void moveBlackPawnMove(Position target) {
        Piece piece = new Pawn(Player.BLACK);
        Position source = Position.of(File.B, Rank.SEVEN);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> blackPawnTarget() {
        return Stream.of(
            Position.of(File.A, Rank.SIX),
            Position.of(File.B, Rank.SIX),
            Position.of(File.C, Rank.SIX)
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnTarget")
    @DisplayName("WhitePawn 은 현재 위치에서 위로 한 칸 또는 대각선(위)으로 한칸 이동할 수 있다.")
    void moveWhitePawnMove(Position target) {
        Piece piece = new Pawn(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    private static Stream<Position> whitePawnTarget() {
        return Stream.of(
            Position.of(File.A, Rank.THREE),
            Position.of(File.B, Rank.THREE),
            Position.of(File.C, Rank.THREE)
        );
    }

    @Test
    @DisplayName("White Pawn 은 처음 움직이는 상황(Rank 2)이라면 두 칸 이동할 수 있다.")
    void moveTwoSpaceMoveWhitePawn_success() {
        Piece piece = new Pawn(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.B, Rank.FOUR);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    @Test
    @DisplayName("Black Pawn 은 처음 움직이는 상황(Rank 7)이라면 두 칸 이동할 수 있다.")
    void moveTwoSpaceMoveBlackPawn_success() {
        Piece piece = new Pawn(Player.BLACK);
        Position source = Position.of(File.E, Rank.SEVEN);
        Position target = Position.of(File.E, Rank.FIVE);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    @Test
    @DisplayName("White Pawn 은 처음 움직이는 상황(Rank 2)이 아니라면 두 칸 이동할 수 없다.")
    void moveTwoSpaceMoveWhitePawn_fail() {
        Piece piece = new Pawn(Player.WHITE);
        Position source = Position.of(File.B, Rank.THREE);
        Position target = Position.of(File.B, Rank.FIVE);

        assertThrows(IllegalArgumentException.class,
            () -> piece.move(source, target));
    }

    @Test
    @DisplayName("Black Pawn 은 처음 움직이는 상황(Rank 7)이 아니라면 두 칸 이동할 수 없다.")
    void moveTwoSpaceMoveBlackPawn_fail() {
        Piece piece = new Pawn(Player.BLACK);
        Position source = Position.of(File.E, Rank.SIX);
        Position target = Position.of(File.E, Rank.FOUR);

        assertThrows(IllegalArgumentException.class,
            () -> piece.move(source, target));
    }
}
