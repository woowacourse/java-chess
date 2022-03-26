package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        Position source = Position.of(File.a, Rank.Two);
        Position target = Position.of(File.a, Rank.Three);

        assertThatCode(() -> chessGame.move(source, target))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("첫 턴에는 블랙의 말은 움직일 수 없다")
    void turnTest1() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        Position source = Position.of(File.a, Rank.Seven);
        Position target = Position.of(File.a, Rank.Six);

        assertThatThrownBy(() -> chessGame.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫 턴이 지나면 두번째 턴에서는 블랙의 말만 움직일 수 있다.")
    void move1() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Position.of(File.a, Rank.Two), Position.of(File.a, Rank.Three));

        Position source = Position.of(File.a, Rank.Seven);
        Position target = Position.of(File.a, Rank.Six);

        assertThatCode(() -> chessGame.move(source, target))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("첫 턴이 지나면 두번째 턴에서는 화이트의 말은 움직일 수 없다.")
    void move2() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Position.of(File.a, Rank.Two), Position.of(File.a, Rank.Three));

        Position source = Position.of(File.a, Rank.Three);
        Position target = Position.of(File.a, Rank.Four);

        assertThatThrownBy(() -> chessGame.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.a, Rank.Eight), Rook.class, "black Rook"),
            Arguments.of(Position.of(File.b, Rank.Eight), Knight.class, "black Night"),
            Arguments.of(Position.of(File.c, Rank.Eight), Bishop.class, "black Bishop"),
            Arguments.of(Position.of(File.d, Rank.Eight), Queen.class, "black Queen"),
            Arguments.of(Position.of(File.e, Rank.Eight), King.class, "black King"),
            Arguments.of(Position.of(File.f, Rank.Eight), Bishop.class, "black Bishop"),
            Arguments.of(Position.of(File.g, Rank.Eight), Knight.class, "black Night"),
            Arguments.of(Position.of(File.h, Rank.Eight), Rook.class, "black Rook"),

            Arguments.of(Position.of(File.a, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.b, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.c, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.d, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.e, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.f, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.g, Rank.Seven), Pawn.class, "black Pawn"),
            Arguments.of(Position.of(File.h, Rank.Seven), Pawn.class, "black Pawn"),

            Arguments.of(Position.of(File.a, Rank.One), Rook.class, "white Rook"),
            Arguments.of(Position.of(File.b, Rank.One), Knight.class, "white Night"),
            Arguments.of(Position.of(File.c, Rank.One), Bishop.class, "white Bishop"),
            Arguments.of(Position.of(File.d, Rank.One), Queen.class, "white Queen"),
            Arguments.of(Position.of(File.e, Rank.One), King.class, "white King"),
            Arguments.of(Position.of(File.f, Rank.One), Bishop.class, "white Bishop"),
            Arguments.of(Position.of(File.g, Rank.One), Knight.class, "white Night"),
            Arguments.of(Position.of(File.h, Rank.One), Rook.class, "white Rook"),

            Arguments.of(Position.of(File.a, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.b, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.c, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.d, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.e, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.f, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.g, Rank.Two), Pawn.class, "white Pawn"),
            Arguments.of(Position.of(File.h, Rank.Two), Pawn.class, "white Pawn")
        );
    }
}
