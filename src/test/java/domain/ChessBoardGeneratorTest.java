package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.unit.Piece;
import domain.piece.property.Team;
import domain.piece.unit.Bishop;
import domain.piece.unit.King;
import domain.piece.unit.Knight;
import domain.piece.unit.Pawn;
import domain.piece.unit.Queen;
import domain.piece.unit.Rook;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardGeneratorTest {

    @Test
    @DisplayName("체스보드의 사이즈는 64이다.")
    void checkChessBoardSize() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        assertThat(chessBoardGenerator.generate().size()).isEqualTo(64);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("검은색 플레이어의 초기 Pawn의 row 위치는 7이다")
    void checkPositionBlackPawn(XPosition x) {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(Position.of(x, YPosition.SEVEN));

        assertThat(piece.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("흰색 플레이어의 초기 Pawn의 row 위치는 2이다")
    void checkPositionWhitePawn(XPosition x) {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(Position.of(x, YPosition.TWO));

        assertThat(piece.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    private static Stream<Arguments> columns() {
        return Stream.of(
                Arguments.of(XPosition.A),
                Arguments.of(XPosition.B),
                Arguments.of(XPosition.C),
                Arguments.of(XPosition.D),
                Arguments.of(XPosition.E),
                Arguments.of(XPosition.F),
                Arguments.of(XPosition.G),
                Arguments.of(XPosition.H)
        );
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Rook 위치는 Row 8, Column a, h이다.")
    void checkPositionBlackRook() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftRook = chessBoardGenerator.generate().get(Position.of(XPosition.A, YPosition.EIGHT));
        Piece rightRook = chessBoardGenerator.generate().get(Position.of(XPosition.H, YPosition.EIGHT));

        assertThat(leftRook.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(rightRook.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Rook 위치는 Row 1, Column a, h이다.")
    void checkPositionWhiteRook() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftRook = chessBoardGenerator.generate().get(Position.of(XPosition.A, YPosition.ONE));
        Piece rightRook = chessBoardGenerator.generate().get(Position.of(XPosition.H, YPosition.ONE));

        assertThat(leftRook.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(rightRook.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Knight 위치는 Row 8, Column b, g이다.")
    void checkPositionBlackKnight() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(Position.of(XPosition.B, YPosition.EIGHT));
        Piece rightKnight = chessBoardGenerator.generate().get(Position.of(XPosition.G, YPosition.EIGHT));

        assertThat(leftKnight.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(rightKnight.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Knight 위치는 Row 1, Column b, g이다.")
    void checkPositionWhiteKnight() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(Position.of(XPosition.B, YPosition.ONE));
        Piece rightKnight = chessBoardGenerator.generate().get(Position.of(XPosition.G, YPosition.ONE));

        assertThat(leftKnight.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(rightKnight.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Bishop 위치는 Row 8, Column c, f이다.")
    void checkPositionBlackBishop() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftBishop = chessBoardGenerator.generate().get(Position.of(XPosition.C, YPosition.EIGHT));
        Piece rightBishop = chessBoardGenerator.generate().get(Position.of(XPosition.F, YPosition.EIGHT));

        assertThat(leftBishop.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(rightBishop.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Bishop 위치는 Row 1, Column c, f이다.")
    void checkPositionWhiteBishop() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftBishop = chessBoardGenerator.generate().get(Position.of(XPosition.C, YPosition.ONE));
        Piece rightBishop = chessBoardGenerator.generate().get(Position.of(XPosition.F, YPosition.ONE));

        assertThat(leftBishop.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(rightBishop.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Queen 위치는 Row 8, Column d이다.")
    void checkPositionBlackQueen() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(Position.of(XPosition.D, YPosition.EIGHT));

        assertThat(piece.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Queen 위치는 Row 1, Column d이다.")
    void checkPositionWhiteQueen() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(Position.of(XPosition.D, YPosition.ONE));

        assertThat(piece.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 King 위치는 Row 8, Column e이다.")
    void checkPositionBlackKing() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(Position.of(XPosition.E, YPosition.EIGHT));

        assertThat(piece.checkSameTeam(Team.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 King 위치는 Row 1, Column e이다.")
    void checkPositionWhiteKing() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(Position.of(XPosition.E, YPosition.ONE));

        assertThat(piece.checkSameTeam(Team.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(King.class);
    }
}
