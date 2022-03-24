package domain;

import static org.assertj.core.api.Assertions.*;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.CommonMovablePiece;
import domain.piece.Player;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.YPosition;
import domain.position.Position;
import domain.position.XPosition;
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
    void checkPositionBlackPawn(XPosition x){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece piece = chessBoardGenerator.generate().get(new Position(x, YPosition.SEVEN));

        assertThat(piece.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("흰색 플레이어의 초기 Pawn의 row 위치는 2이다")
    void checkPositionWhitePawn(XPosition x){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece piece = chessBoardGenerator.generate().get(new Position(x, YPosition.TWO));

        assertThat(piece.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    private static Stream<Arguments> columns(){
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
    void checkPositionBlackRook(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece leftRook = chessBoardGenerator.generate().get(new Position(XPosition.A, YPosition.EIGHT));
        CommonMovablePiece rightRook = chessBoardGenerator.generate().get(new Position(XPosition.H, YPosition.EIGHT));

        assertThat(leftRook.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(rightRook.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Rook 위치는 Row 1, Column a, h이다.")
    void checkPositionWhiteRook(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece leftRook = chessBoardGenerator.generate().get(new Position(XPosition.A, YPosition.ONE));
        CommonMovablePiece rightRook = chessBoardGenerator.generate().get(new Position(XPosition.H, YPosition.ONE));

        assertThat(leftRook.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(rightRook.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Knight 위치는 Row 8, Column b, g이다.")
    void checkPositionBlackKnight(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece leftKnight = chessBoardGenerator.generate().get(new Position(XPosition.B, YPosition.EIGHT));
        CommonMovablePiece rightKnight = chessBoardGenerator.generate().get(new Position(XPosition.G, YPosition.EIGHT));

        assertThat(leftKnight.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(rightKnight.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Knight 위치는 Row 1, Column b, g이다.")
    void checkPositionWhiteKnight(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece leftKnight = chessBoardGenerator.generate().get(new Position(XPosition.B, YPosition.ONE));
        CommonMovablePiece rightKnight = chessBoardGenerator.generate().get(new Position(XPosition.G, YPosition.ONE));

        assertThat(leftKnight.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(rightKnight.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Bishop 위치는 Row 8, Column c, f이다.")
    void checkPositionBlackBishop(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece leftBishop = chessBoardGenerator.generate().get(new Position(XPosition.C, YPosition.EIGHT));
        CommonMovablePiece rightBishop = chessBoardGenerator.generate().get(new Position(XPosition.F, YPosition.EIGHT));

        assertThat(leftBishop.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(rightBishop.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Bishop 위치는 Row 1, Column c, f이다.")
    void checkPositionWhiteBishop(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece leftBishop = chessBoardGenerator.generate().get(new Position(XPosition.C, YPosition.ONE));
        CommonMovablePiece rightBishop = chessBoardGenerator.generate().get(new Position(XPosition.F, YPosition.ONE));

        assertThat(leftBishop.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(rightBishop.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Queen 위치는 Row 8, Column d이다.")
    void checkPositionBlackQueen(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece piece = chessBoardGenerator.generate().get(new Position(XPosition.D, YPosition.EIGHT));

        assertThat(piece.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Queen 위치는 Row 1, Column d이다.")
    void checkPositionWhiteQueen(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece piece = chessBoardGenerator.generate().get(new Position(XPosition.D, YPosition.ONE));

        assertThat(piece.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 King 위치는 Row 8, Column e이다.")
    void checkPositionBlackKing(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece piece = chessBoardGenerator.generate().get(new Position(XPosition.E, YPosition.EIGHT));

        assertThat(piece.checkSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 King 위치는 Row 1, Column e이다.")
    void checkPositionWhiteKing(){
        ChessBoardGenerator chessBoardGenerator = new ChessBoardGenerator();
        CommonMovablePiece piece = chessBoardGenerator.generate().get(new Position(XPosition.E, YPosition.ONE));

        assertThat(piece.checkSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(King.class);
    }
}
