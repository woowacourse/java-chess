package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.property.Color;

public class PawnTest {

    private ChessBoard board;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        Position white = Position.of(File.d, Rank.Two);
        Position black = Position.of(File.d, Rank.Seven);

        board.putPiece(white, new Pawn(Color.White));
        board.putPiece(black, new Pawn(Color.Black));

        board.putPiece(Position.of(File.c, Rank.Three), new Pawn(Color.Black));
        board.putPiece(Position.of(File.e, Rank.Three), new Pawn(Color.Black));
        board.putPiece(Position.of(File.c, Rank.Six), new Pawn(Color.White));
        board.putPiece(Position.of(File.e, Rank.Six), new Pawn(Color.White));

        board.putPiece(Position.of(File.a, Rank.Three), new Pawn(Color.Black));
        board.putPiece(Position.of(File.a, Rank.Two), new Pawn(Color.White));

        board.putPiece(Position.of(File.h, Rank.Four), new Pawn(Color.Black));
        board.putPiece(Position.of(File.h, Rank.Two), new Pawn(Color.White));
    }

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("invalidParameters")
    @DisplayName("폰이 이동할 수 없는 곳으로 이동")
    void pawnInvalidTest(Position source, Position target, String testName) {
        board.putPiece(Position.of(File.d, Rank.Three), new Pawn(Color.White));
        board.putPiece(Position.of(File.d, Rank.Six), new Pawn(Color.Black));

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.d, Rank.Three), Position.of(File.d, Rank.Two), "화이트 폰은 뒤로 이동할 수 없다."),
            Arguments.of(Position.of(File.d, Rank.Six), Position.of(File.d, Rank.Seven), "블랙 폰은 뒤로 이동할 수 없다."),

            Arguments.of(Position.of(File.d, Rank.Three), Position.of(File.c, Rank.Four), "화이트 폰은 잡을 기물이 없으면 왼쪽위로 이동할 수 없다."),
            Arguments.of(Position.of(File.d, Rank.Three), Position.of(File.e, Rank.Four), "화이트 폰은 잡을 기물이 없으면 오른쪽위로 이동할 수 없다."),

            Arguments.of(Position.of(File.d, Rank.Six), Position.of(File.c, Rank.Five), "블랙 폰은 잡을 기물이 없으면 왼쪽아래로 이동할 수 없다."),
            Arguments.of(Position.of(File.d, Rank.Six), Position.of(File.e, Rank.Five), "블랙 폰은 잡을 기물이 없으면 오른쪽아래로 이동할 수 없다."),

            Arguments.of(Position.of(File.d, Rank.Two), Position.of(File.d, Rank.Four), "화이트 폰은 기물을 뛰어넘어 이동할 수 없다."),
            Arguments.of(Position.of(File.d, Rank.Seven), Position.of(File.d, Rank.Five), "블랙 폰은 기물을 뛰어넘어 이동할 수 없다."),

            Arguments.of(Position.of(File.a, Rank.Two), Position.of(File.a, Rank.Three), "폰은 앞에 있는 기물을 잡을 수 없다."),
            Arguments.of(Position.of(File.h, Rank.Four), Position.of(File.h, Rank.Two), "폰은 두칸 앞에 있는 기물을 잡을 수 없다.")
        );
    }

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("validParameters")
    @DisplayName("폰이 이동할 수 있는 곳으로 이동")
    void pawnValidTest(Position source, Position target, String testName) {
        Piece piece = board.getPiece(source);
        board.move(source, target);

        assertThat(board.getPiece(target)).isSameAs(piece);
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.d, Rank.Two), Position.of(File.d, Rank.Three), "화이트는 위로 한칸 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Two), Position.of(File.d, Rank.Four), "화이트는 위로 두칸 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Two), Position.of(File.c, Rank.Three), "화이트는 왼쪽 위의 기물을 잡고 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Two), Position.of(File.e, Rank.Three), "화이트는 오른쪽 위의 기물을 잡고 이동할 수 있다."),

            Arguments.of(Position.of(File.d, Rank.Seven), Position.of(File.d, Rank.Six), "블랙은 아래로 한칸 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Seven), Position.of(File.d, Rank.Five), "블랙은 아래로 두칸 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Seven), Position.of(File.c, Rank.Six), "블랙 왼쪽 아래의 기물을 잡고 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Seven), Position.of(File.e, Rank.Six), "블랙 오른쪽 아래의 기물을 잡고 이동할 수 있다.")
        );
    }

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("movedPawnInvalidParameters")
    @DisplayName("한번 움직인 폰이 이동할 수 없는 곳으로 이동")
    void movedPawnTest(Position source, Position target, String testName) {
        board.move(Position.of(File.d, Rank.Two), Position.of(File.d, Rank.Three));
        board.move(Position.of(File.d, Rank.Seven), Position.of(File.d, Rank.Six));

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> movedPawnInvalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.d, Rank.Three), Position.of(File.d, Rank.Five), "한번 움직인 화이트 폰은 두칸 이동할 수 없다."),
            Arguments.of(Position.of(File.d, Rank.Six), Position.of(File.d, Rank.Four), "한번 움직인 블랙 폰은 두칸 이동할 수 없다.")
        );
    }
}
