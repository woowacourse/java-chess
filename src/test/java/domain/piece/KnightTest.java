package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.position.Rank;
import domain.position.File;
import domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightTest {

    @ParameterizedTest
    @MethodSource("targetPosition")
    @DisplayName("Knight 은 현재 위치에서 모든 방향으로 한 칸 이동할 수 있다.")
    void moveKingDiagonally(Position target) {
        Piece piece = new Knight(Player.WHITE);
        Position source = Position.of(File.C, Rank.FOUR);

        assertThat(piece.isAvailableMove(source, target)).isEqualTo(true);
    }

    private static Stream<Position> targetPosition() {
        return Stream.of(
            Position.of(File.B, Rank.SIX),
            Position.of(File.D, Rank.SIX),
            Position.of(File.A, Rank.FIVE),
            Position.of(File.A, Rank.THREE),
            Position.of(File.E, Rank.FIVE),
            Position.of(File.E, Rank.THREE),
            Position.of(File.B, Rank.TWO),
            Position.of(File.D, Rank.TWO)
        );
    }
}
