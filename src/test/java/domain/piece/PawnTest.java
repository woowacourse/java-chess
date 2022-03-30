package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Player;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PawnTest {

    @ParameterizedTest
    @MethodSource("blackPawnTarget")
    @DisplayName("BlackPawn 은 현재 위치에서 아래로 한 칸 또는 대각선(아래)으로 한칸 이동할 수 있다.")
    void moveBlackPawnMove(Position target) {
        Piece piece = new Pawn(Player.BLACK);
        Position source = Position.of(File.B, Rank.SEVEN);
        piece.generateAvailablePosition(source);

        assertDoesNotThrow(() -> piece.getAvailablePositions(source, target));
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

        piece.generateAvailablePosition(source);
        assertDoesNotThrow(() -> piece.getAvailablePositions(source, target));
    }

    private static Stream<Position> whitePawnTarget() {
        return Stream.of(
            Position.of(File.A, Rank.THREE),
            Position.of(File.B, Rank.THREE),
            Position.of(File.C, Rank.THREE)
        );
    }
}
