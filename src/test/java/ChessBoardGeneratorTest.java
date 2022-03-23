import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ChessBoardGeneratorTest {

    @Test
    @DisplayName("체스보드의 사이즈는 64이다.")
    void checkChessBoardSize() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();

        assertThat(chessBoardGenerator.generate().size()).isEqualTo(64);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("검은색 플레이어의 초기 Pawn의 행은 7행이다")
    void checkPositionBlackPawn(Column column) {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.SEVEN, column));

        assertThat(piece.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("흰색 플레이어의 초기 Pawn의 행은 2행이다")
    void checkPositionWhitePawn(Column column) {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.TWO, column));

        assertThat(piece.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Rook 위치는 Row 8, Column a, h이다.")
    void checkPositionBlackRook() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftRook = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.A));
        Piece rightRook = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.H));

        assertThat(leftRook.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(rightRook.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Rook 위치는 Row 1, Column a, h이다.")
    void checkPositionWhiteRook() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftRook = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.A));
        Piece rightRook = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.H));

        assertThat(leftRook.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(rightRook.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    private static Stream<Column> columns() {
        return Stream.of(
            Column.A,
            Column.B,
            Column.C,
            Column.D,
            Column.E,
            Column.F,
            Column.G,
            Column.H
        );
    }
}
