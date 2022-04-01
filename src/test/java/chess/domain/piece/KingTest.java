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

public class KingTest {

    private ChessBoard board;
    private Piece sourcePiece;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        sourcePiece = new King(Color.BLACK);

        board.putPiece(Position.of(File.C, Rank.THREE), sourcePiece);

        board.putPiece(Position.of(File.C, Rank.FOUR), new King(Color.WHITE));
        board.putPiece(Position.of(File.F, Rank.SIX), new King(Color.BLACK));
        board.putPiece(Position.of(File.G, Rank.SEVEN), new King(Color.BLACK));
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("킹이 이동할 수 없는 곳으로 이동")
    void kingInvalidTest(Position target, String testName) {
        Position source = Position.of(File.F, Rank.SIX);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.F, Rank.EIGHT), "킹은 2칸 이상 움직일 수 없다."),
            Arguments.of(Position.of(File.G, Rank.SEVEN), "킹은 이동하려는 칸에 같은 색의 기물이 있을경우 이동할 수 없다.")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("validParameters")
    @DisplayName("킹이 이동할 수 있는 곳으로 이동")
    void kingValidTest(Position target, String testName) {
        Position source = Position.of(File.C, Rank.THREE);
        board.move(source, target);

        assertThat(board.getPiece(target)).isSameAs(sourcePiece);
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.D, Rank.FOUR), "킹은 오른쪽위로 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.THREE), "킹은 오른쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.D, Rank.TWO), "킹은 오른쪽아래로 이동할 수 있다."),
            Arguments.of(Position.of(File.C, Rank.TWO), "킹은 아래로 이동할 수 있다."),
            Arguments.of(Position.of(File.B, Rank.TWO), "킹은 왼쪽아래로 이동할 수 있다."),
            Arguments.of(Position.of(File.B, Rank.THREE), "킹은 왼쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.B, Rank.FOUR), "킹은 왼쪽위로 이동할 수 있다."),
            Arguments.of(Position.of(File.C, Rank.FOUR), "킹은 이동하려는 칸에 다른 색의 기물이 있을경우 기물을 잡고 그 위치로 이동한다.")
        );
    }
}
