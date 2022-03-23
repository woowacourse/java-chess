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

    @Test
    @DisplayName("검은색 플레이어의 초기 Knight 위치는 Row 8, Column b, g이다.")
    void checkPositionBlackKnight() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.B));
        Piece rightKnight = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.G));

        assertThat(leftKnight.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(rightKnight.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Knight 위치는 Row 1, Column b, g이다.")
    void checkPositionWhiteKnight() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.B));
        Piece rightKnight = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.G));

        assertThat(leftKnight.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(rightKnight.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Bishop 위치는 Row 8, Column c, f이다.")
    void checkPositionBlackBishop() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftBishop = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.C));
        Piece rightBishop = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.F));

        assertThat(leftBishop.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(rightBishop.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Bishop 위치는 Row 1, Column c, f이다.")
    void checkPositionWhiteBishop() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftBishop = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.C));
        Piece rightBishop = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.F));

        assertThat(leftBishop.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(rightBishop.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Queen 위치는 Row 8, Column d이다.")
    void checkPositionBlackQueen() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.D));

        assertThat(piece.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Queen 위치는 Row 1, Column d이다.")
    void checkPositionWhiteQueen() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.D));

        assertThat(piece.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 King 위치는 Row 8, Column e이다.")
    void checkPositionBlackKing() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.EIGHT, Column.E));

        assertThat(piece.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(piece).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 King 위치는 Row 1, Column e이다.")
    void checkPositionWhiteKing() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.E));

        assertThat(piece.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(piece).isInstanceOf(King.class);
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
