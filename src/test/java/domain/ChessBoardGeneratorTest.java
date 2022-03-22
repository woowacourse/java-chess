package domain;

import static org.assertj.core.api.Assertions.*;

import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Player;
import domain.piece.Rook;
import domain.position.Row;
import domain.position.Position;
import domain.position.Column;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardGeneratorTest {

    @Test
    @DisplayName("체스보드의 사이즈는 64이다.")
    void checkChessBoardSize(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        assertThat(chessBoardGenerator.generate().size()).isEqualTo(64);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("검은색 플레이어의 초기 Pawn의 row 위치는 7이다")
    void checkPositionBlackPawn(Column column){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.SEVEN, column));

        assertThat(piece.getPlayer()).isEqualTo(Player.BLACK);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("흰색 플레이어의 초기 Pawn의 row 위치는 2이다")
    void checkPositionWhitePawn(Column column){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(new Position(Row.TWO, column));

        assertThat(piece.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    private static Stream<Arguments> columns(){
        return Stream.of(
                Arguments.of(Column.A),
                Arguments.of(Column.B),
                Arguments.of(Column.C),
                Arguments.of(Column.D),
                Arguments.of(Column.E),
                Arguments.of(Column.F),
                Arguments.of(Column.G),
                Arguments.of(Column.H)
        );
    }

/*
rook
queen
king
bishop
knight
// pawn
 */

    @Test
    @DisplayName("검은색 플레이어의 초기 Rook 위치는 Row 8, Column a, h이다.")
    void checkPositionBlackRook(){
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
    void checkPositionWhiteRook(){
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
    void checkPositionBlackKnight(){
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
    void checkPositionWhiteKnight(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.B));
        Piece rightKnight = chessBoardGenerator.generate().get(new Position(Row.ONE, Column.G));

        assertThat(leftKnight.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(rightKnight.getPlayer()).isEqualTo(Player.WHITE);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }
}
