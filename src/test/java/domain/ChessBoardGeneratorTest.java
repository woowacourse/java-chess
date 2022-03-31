package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.chessgame.ChessBoard;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ChessBoardGenerator;

public class ChessBoardGeneratorTest {

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("검은색 플레이어의 초기 Pawn의 행은 7행이다")
    void checkPositionBlackPawn(File file) {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece piece = chessBoard.findPiece(Position.of(file, Rank.SEVEN));

        assertThat(piece.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @MethodSource("columns")
    @DisplayName("흰색 플레이어의 초기 Pawn의 행은 2행이다")
    void checkPositionWhitePawn(File file) {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece piece = chessBoard.findPiece(Position.of(file, Rank.TWO));

        assertThat(piece.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Rook 위치는 Row 8, Column a, h이다.")
    void checkPositionBlackRook() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece leftRook = chessBoard.findPiece(Position.of(File.A, Rank.EIGHT));
        Piece rightRook = chessBoard.findPiece(Position.of(File.H, Rank.EIGHT));

        assertThat(leftRook.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(rightRook.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Rook 위치는 Row 1, Column a, h이다.")
    void checkPositionWhiteRook() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece leftRook = chessBoard.findPiece(Position.of(File.A, Rank.ONE));
        Piece rightRook = chessBoard.findPiece(Position.of(File.H, Rank.ONE));

        assertThat(leftRook.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(rightRook.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(leftRook).isInstanceOf(Rook.class);
        assertThat(rightRook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Knight 위치는 Row 8, Column b, g이다.")
    void checkPositionBlackKnight() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece leftKnight = chessBoard.findPiece(Position.of(File.B, Rank.EIGHT));
        Piece rightKnight = chessBoard.findPiece(Position.of(File.G, Rank.EIGHT));

        assertThat(leftKnight.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(rightKnight.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Knight 위치는 Row 1, Column b, g이다.")
    void checkPositionWhiteKnight() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece leftKnight = chessBoard.findPiece(Position.of(File.B, Rank.ONE));
        Piece rightKnight = chessBoard.findPiece(Position.of(File.G, Rank.ONE));

        assertThat(leftKnight.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(rightKnight.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(leftKnight).isInstanceOf(Knight.class);
        assertThat(rightKnight).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Bishop 위치는 Row 8, Column c, f이다.")
    void checkPositionBlackBishop() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece leftBishop = chessBoard.findPiece(Position.of(File.C, Rank.EIGHT));
        Piece rightBishop = chessBoard.findPiece(Position.of(File.F, Rank.EIGHT));

        assertThat(leftBishop.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(rightBishop.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Bishop 위치는 Row 1, Column c, f이다.")
    void checkPositionWhiteBishop() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece leftBishop = chessBoard.findPiece(Position.of(File.C, Rank.ONE));
        Piece rightBishop = chessBoard.findPiece(Position.of(File.F, Rank.ONE));

        assertThat(leftBishop.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(rightBishop.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(leftBishop).isInstanceOf(Bishop.class);
        assertThat(rightBishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 Queen 위치는 Row 8, Column d이다.")
    void checkPositionBlackQueen() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece piece = chessBoard.findPiece(Position.of(File.D, Rank.EIGHT));

        assertThat(piece.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 Queen 위치는 Row 1, Column d이다.")
    void checkPositionWhiteQueen() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece piece = chessBoard.findPiece(Position.of(File.D, Rank.ONE));

        assertThat(piece.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("검은색 플레이어의 초기 King 위치는 Row 8, Column e이다.")
    void checkPositionBlackKing() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece piece = chessBoard.findPiece(Position.of(File.E, Rank.EIGHT));

        assertThat(piece.isSamePlayer(Player.BLACK)).isEqualTo(true);
        assertThat(piece).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("흰색 플레이어의 초기 King 위치는 Row 1, Column e이다.")
    void checkPositionWhiteKing() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        Piece piece = chessBoard.findPiece(Position.of(File.E, Rank.ONE));

        assertThat(piece.isSamePlayer(Player.WHITE)).isEqualTo(true);
        assertThat(piece).isInstanceOf(King.class);
    }

    private static Stream<File> columns() {
        return Stream.of(
            File.A,
            File.B,
            File.C,
            File.D,
            File.E,
            File.F,
            File.G,
            File.H
        );
    }
}
