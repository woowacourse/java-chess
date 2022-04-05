package domain;

import static chess.domain.piece.property.Team.*;
import static domain.PositionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.ChessBoardGenerator;
import chess.domain.piece.property.Team;
import chess.domain.piece.unit.Piece;
import chess.domain.piece.unit.Bishop;
import chess.domain.piece.unit.King;
import chess.domain.piece.unit.Knight;
import chess.domain.piece.unit.Pawn;
import chess.domain.piece.unit.Queen;
import chess.domain.piece.unit.Rook;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardGeneratorTest {

    @Test
    @DisplayName("체스보드의 사이즈는 64이다.")
    void checkChessBoardSize() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        assertThat(chessBoardGenerator.generate().size()).isEqualTo(64);
    }

    @ParameterizedTest
    @MethodSource("whitePawns")
    @DisplayName("흰색 플레이어의 초기 Pawn의 row 위치는 2이다")
    void checkPositionWhitePawn(Position position) {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(position);

        assertAll(
                () -> assertEquals(piece.checkSameTeam(WHITE), true),
                () -> assertThat(piece).isInstanceOf(Pawn.class)
        );
    }

    private static Stream<Position> whitePawns() {
        return Stream.of(A2, B2, C2, D2, E2, F2, G2, H2);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("검은색 플레이어의 초기 Pawn의 row 위치는 7이다")
    void checkPositionBlackPawn(Position position) {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(position);

        assertAll(
                () -> assertEquals(piece.checkSameTeam(BLACK), true),
                () -> assertThat(piece).isInstanceOf(Pawn.class)
        );
    }

    private static Stream<Position> columns() {
        return Stream.of(A7, B7, C7, D7, E7, F7, G7, H7);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Rook 위치는 A1, H1이다.")
    void checkPositionWhiteRook() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftRook = chessBoardGenerator.generate().get(A1);
        Piece rightRook = chessBoardGenerator.generate().get(H1);

        assertAll(
                () -> assertEquals(leftRook.checkSameTeam(WHITE), true),
                () -> assertEquals(rightRook.checkSameTeam(WHITE), true),
                () -> assertThat(leftRook).isInstanceOf(Rook.class),
                () -> assertThat(rightRook).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Rook 위치는 A8, H8이다.")
    void checkPositionBlackRook() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftRook = chessBoardGenerator.generate().get(A8);
        Piece rightRook = chessBoardGenerator.generate().get(H8);

        assertAll(
                () -> assertEquals(leftRook.checkSameTeam(BLACK), true),
                () -> assertEquals(rightRook.checkSameTeam(BLACK), true),
                () -> assertThat(leftRook).isInstanceOf(Rook.class),
                () -> assertThat(rightRook).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Knight 위치는 B1, G1이다.")
    void checkPositionWhiteKnight() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(B1);
        Piece rightKnight = chessBoardGenerator.generate().get(G1);

        assertAll(
                () -> assertEquals(leftKnight.checkSameTeam(WHITE), true),
                () -> assertEquals(rightKnight.checkSameTeam(WHITE), true),
                () -> assertThat(leftKnight).isInstanceOf(Knight.class),
                () -> assertThat(rightKnight).isInstanceOf(Knight.class)
        );
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Knight 위치는 B8, G8이다.")
    void checkPositionBlackKnight() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftKnight = chessBoardGenerator.generate().get(B8);
        Piece rightKnight = chessBoardGenerator.generate().get(G8);

        assertAll(
                () -> assertEquals(leftKnight.checkSameTeam(BLACK), true),
                () -> assertEquals(rightKnight.checkSameTeam(BLACK), true),
                () -> assertThat(leftKnight).isInstanceOf(Knight.class),
                () -> assertThat(rightKnight).isInstanceOf(Knight.class)
        );
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Bishop 위치는 C1, F1이다.")
    void checkPositionWhiteBishop() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftBishop = chessBoardGenerator.generate().get(C1);
        Piece rightBishop = chessBoardGenerator.generate().get(F1);

        assertAll(
                () -> assertEquals(leftBishop.checkSameTeam(WHITE), true),
                () -> assertEquals(rightBishop.checkSameTeam(WHITE), true),
                () -> assertThat(leftBishop).isInstanceOf(Bishop.class),
                () -> assertThat(rightBishop).isInstanceOf(Bishop.class)
        );
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Bishop 위치는 C8, F8이다.")
    void checkPositionBlackBishop() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece leftBishop = chessBoardGenerator.generate().get(C8);
        Piece rightBishop = chessBoardGenerator.generate().get(F8);

        assertAll(
                () -> assertEquals(leftBishop.checkSameTeam(BLACK), true),
                () -> assertEquals(rightBishop.checkSameTeam(BLACK), true),
                () -> assertThat(leftBishop).isInstanceOf(Bishop.class),
                () -> assertThat(rightBishop).isInstanceOf(Bishop.class)
        );
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Queen 위치는 D1이다.")
    void checkPositionWhiteQueen() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(D1);

        assertAll(
                () -> assertEquals(piece.checkSameTeam(WHITE), true),
                () -> assertThat(piece).isInstanceOf(Queen.class)
        );
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Queen 위치는 D8이다.")
    void checkPositionBlackQueen() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(D8);

        assertAll(
                () -> assertEquals(piece.checkSameTeam(BLACK), true),
                () -> assertThat(piece).isInstanceOf(Queen.class)
        );
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 King 위치는 E1이다.")
    void checkPositionWhiteKing() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(E1);

        assertAll(
                () -> assertEquals(piece.checkSameTeam(WHITE), true),
                () -> assertThat(piece).isInstanceOf(King.class)
        );
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 King 위치는 E8이다.")
    void checkPositionBlackKing() {
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        Piece piece = chessBoardGenerator.generate().get(E8);

        assertAll(
                () -> assertEquals(piece.checkSameTeam(BLACK), true),
                () -> assertThat(piece).isInstanceOf(King.class)
        );
    }
}
