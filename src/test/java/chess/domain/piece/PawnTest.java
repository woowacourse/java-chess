package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;

public class PawnTest {

    private ChessBoard board;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        Position white = Position.of(File.D, Rank.TWO);
        Position black = Position.of(File.D, Rank.SEVEN);

        board.putPiece(white, new StartedPawn(Color.WHITE));
        board.putPiece(black, new StartedPawn(Color.BLACK));

        board.putPiece(Position.of(File.C, Rank.THREE), new StartedPawn(Color.BLACK));
        board.putPiece(Position.of(File.E, Rank.THREE), new StartedPawn(Color.BLACK));
        board.putPiece(Position.of(File.C, Rank.SIX), new StartedPawn(Color.WHITE));
        board.putPiece(Position.of(File.E, Rank.SIX), new StartedPawn(Color.WHITE));

        board.putPiece(Position.of(File.A, Rank.TWO), new StartedPawn(Color.WHITE));
        board.putPiece(Position.of(File.A, Rank.THREE), new StartedPawn(Color.BLACK));

        board.putPiece(Position.of(File.H, Rank.FOUR), new StartedPawn(Color.BLACK));
        board.putPiece(Position.of(File.H, Rank.TWO), new StartedPawn(Color.WHITE));
    }

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("invalidParameters")
    @DisplayName("폰이 이동할 수 없는 곳으로 이동")
    void pawnInvalidTest(Position source, Position target, String testName) {
        board.move(Position.of(File.D, Rank.TWO), Position.of(File.D, Rank.THREE));
        board.move(Position.of(File.D, Rank.SEVEN), Position.of(File.D, Rank.SIX));

        board.putPiece(Position.of(File.D, Rank.TWO), new StartedPawn(Color.WHITE));
        board.putPiece(Position.of(File.D, Rank.SEVEN), new StartedPawn(Color.BLACK));
        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.D, Rank.THREE), Position.of(File.D, Rank.FIVE), "한번 움직인 화이트 폰은 두칸 이동할 수 없다."),
            Arguments.of(Position.of(File.D, Rank.SIX), Position.of(File.D, Rank.FOUR), "한번 움직인 블랙 폰은 두칸 이동할 수 없다."),

            Arguments.of(Position.of(File.D, Rank.THREE), Position.of(File.D, Rank.TWO), "화이트 폰은 뒤로 이동할 수 없다."),
            Arguments.of(Position.of(File.D, Rank.SIX), Position.of(File.D, Rank.SEVEN), "블랙 폰은 뒤로 이동할 수 없다."),

            Arguments.of(Position.of(File.D, Rank.THREE), Position.of(File.C, Rank.FOUR), "화이트 폰은 잡을 기물이 없으면 왼쪽위로 이동할 수 없다."),
            Arguments.of(Position.of(File.D, Rank.THREE), Position.of(File.E, Rank.FOUR), "화이트 폰은 잡을 기물이 없으면 오른쪽위로 이동할 수 없다."),

            Arguments.of(Position.of(File.D, Rank.SIX), Position.of(File.C, Rank.FIVE), "블랙 폰은 잡을 기물이 없으면 왼쪽아래로 이동할 수 없다."),
            Arguments.of(Position.of(File.D, Rank.SIX), Position.of(File.E, Rank.FIVE), "블랙 폰은 잡을 기물이 없으면 오른쪽아래로 이동할 수 없다."),

            Arguments.of(Position.of(File.D, Rank.TWO), Position.of(File.D, Rank.FOUR), "화이트 폰은 기물을 뛰어넘어 이동할 수 없다."),
            Arguments.of(Position.of(File.D, Rank.SEVEN), Position.of(File.D, Rank.FIVE), "블랙 폰은 기물을 뛰어넘어 이동할 수 없다."),

            Arguments.of(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE), "폰은 앞에 있는 기물을 잡을 수 없다."),
            Arguments.of(Position.of(File.H, Rank.FOUR), Position.of(File.H, Rank.TWO), "폰은 두칸 앞에 있는 기물을 잡을 수 없다.")
        );
    }

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("validParameters")
    @DisplayName("폰이 이동할 수 있는 곳으로 이동")
    void pawnValidTest(Position source, Position target, String testName) {
        Piece piece = board.getPiece(source);
        board.move(source, target);

        assertThat(board.getPiece(target).getClass()).isSameAs(MovedPawn.class);
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.D, Rank.TWO), Position.of(File.D, Rank.THREE), "화이트는 위로 한칸 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.TWO), Position.of(File.D, Rank.FOUR), "화이트는 위로 두칸 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.TWO), Position.of(File.C, Rank.THREE), "화이트는 왼쪽 위의 기물을 잡고 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.TWO), Position.of(File.E, Rank.THREE), "화이트는 오른쪽 위의 기물을 잡고 이동할 수 있다."),

            Arguments.of(Position.of(File.D, Rank.SEVEN), Position.of(File.D, Rank.SIX), "블랙은 아래로 한칸 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.SEVEN), Position.of(File.D, Rank.FIVE), "블랙은 아래로 두칸 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.SEVEN), Position.of(File.C, Rank.SIX), "블랙 왼쪽 아래의 기물을 잡고 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.SEVEN), Position.of(File.E, Rank.SIX), "블랙 오른쪽 아래의 기물을 잡고 이동할 수 있다.")
        );
    }
}
