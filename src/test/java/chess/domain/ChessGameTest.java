package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.game.ChessGame;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;

public class ChessGameTest {
    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("invalidParameters")
    @DisplayName("체스판 초기화시 각 체스말들이 생성된다.")
    void boardTest(Position position, Class<Piece> piece, String testName) {
        ChessGame chessGame = new ChessGame();
        Map<Position, Piece> start = chessGame.start();
        assertThat(start.get(position)).isInstanceOf(piece);
    }

    @Test
    @DisplayName("첫 턴에는 화이트의 말만 움직일 수 있다")
    void turnTest() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        Position source = Position.of(File.A, Rank.TWO);
        Position target = Position.of(File.A, Rank.THREE);

        assertThatCode(() -> chessGame.move(source, target))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("첫 턴에는 블랙의 말은 움직일 수 없다")
    void turnTest1() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        Position source = Position.of(File.A, Rank.SEVEN);
        Position target = Position.of(File.A, Rank.SIX);

        assertThatThrownBy(() -> chessGame.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫 턴이 지나면 두번째 턴에서는 블랙의 말만 움직일 수 있다.")
    void move1() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE));

        Position source = Position.of(File.A, Rank.SEVEN);
        Position target = Position.of(File.A, Rank.SIX);

        assertThatCode(() -> chessGame.move(source, target))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("첫 턴이 지나면 두번째 턴에서는 화이트의 말은 움직일 수 없다.")
    void move2() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE));

        Position source = Position.of(File.A, Rank.THREE);
        Position target = Position.of(File.A, Rank.FOUR);

        assertThatThrownBy(() -> chessGame.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.A, Rank.EIGHT), Rook.class, "black Rook"),
            Arguments.of(Position.of(File.B, Rank.EIGHT), Knight.class, "black Night"),
            Arguments.of(Position.of(File.C, Rank.EIGHT), Bishop.class, "black Bishop"),
            Arguments.of(Position.of(File.D, Rank.EIGHT), Queen.class, "black Queen"),
            Arguments.of(Position.of(File.E, Rank.EIGHT), King.class, "black King"),
            Arguments.of(Position.of(File.F, Rank.EIGHT), Bishop.class, "black Bishop"),
            Arguments.of(Position.of(File.G, Rank.EIGHT), Knight.class, "black Night"),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Rook.class, "black Rook"),

            Arguments.of(Position.of(File.A, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.B, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.C, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.D, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.E, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.F, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.G, Rank.SEVEN), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.H, Rank.SEVEN), Pawn.class, "black Pawn"),

            Arguments.of(Position.of(File.A, Rank.ONE), Rook.class, "white Rook"),
            Arguments.of(Position.of(File.B, Rank.ONE), Knight.class, "white Night"),
            Arguments.of(Position.of(File.C, Rank.ONE), Bishop.class, "white Bishop"),
            Arguments.of(Position.of(File.D, Rank.ONE), Queen.class, "white Queen"),
            Arguments.of(Position.of(File.E, Rank.ONE), King.class, "white King"),
            Arguments.of(Position.of(File.F, Rank.ONE), Bishop.class, "white Bishop"),
            Arguments.of(Position.of(File.G, Rank.ONE), Knight.class, "white Night"),
            Arguments.of(Position.of(File.H, Rank.ONE), Rook.class, "white Rook"),

            Arguments.of(Position.of(File.A, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.B, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.C, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.D, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.E, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.F, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.G, Rank.TWO), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.H, Rank.TWO), Pawn.class, "white Pawn")
        );
    }
}
