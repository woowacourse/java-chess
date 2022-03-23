package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PawnTest {
    @ParameterizedTest
    @MethodSource("blackPawnTarget")
    @DisplayName("BlackPawn 은 현재 위치에서 아래로 한 칸 또는 대각선(아래)으로 한칸 이동할 수 있다.")
    void moveBlackPawnMove(Position target) {
        Piece piece = new BlackPawn(Player.BLACK);
        Position source = new Position(Row.SEVEN, Column.B);
        List<Position> positions = piece.availableMovePositions(source);

        assertThat(positions.contains(target)).isEqualTo(true);
    }

    private static Stream<Position> blackPawnTarget() {
        return Stream.of(
            new Position(Row.SIX, Column.A),
            new Position(Row.SIX, Column.B),
            new Position(Row.SIX, Column.C)
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnTarget")
    @DisplayName("WhitePawn 은 현재 위치에서 위로 한 칸 또는 대각선(위)으로 한칸 이동할 수 있다.")
    void moveWhitePawnMove(Position target) {
        Piece piece = new WhitePawn(Player.WHITE);
        Position source = new Position(Row.TWO, Column.B);
        List<Position> positions = piece.availableMovePositions(source);

        assertThat(positions.contains(target)).isEqualTo(true);
    }

    private static Stream<Position> whitePawnTarget() {
        return Stream.of(
            new Position(Row.THREE, Column.A),
            new Position(Row.THREE, Column.B),
            new Position(Row.THREE, Column.C)
        );
    }
}
