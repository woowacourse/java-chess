package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.ChessBoard;
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
        sourcePiece = new King(Color.Black);

        board.putPiece(Position.of(File.c, Rank.Three), sourcePiece);

        board.putPiece(Position.of(File.c, Rank.Four), new King(Color.White));
        board.putPiece(Position.of(File.f, Rank.Six), new King(Color.Black));
        board.putPiece(Position.of(File.g, Rank.Seven), new King(Color.Black));
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("킹이 이동할 수 없는 곳으로 이동")
    void kingInvalidTest(Position target, String testName) {
        Position source = Position.of(File.f, Rank.Six);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.f, Rank.Eight), "킹은 2칸 이상 움직일 수 없다."),
            Arguments.of(Position.of(File.g, Rank.Seven), "킹은 이동하려는 칸에 같은 색의 기물이 있을경우 이동할 수 없다.")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("validParameters")
    @DisplayName("킹이 이동할 수 있는 곳으로 이동")
    void kingValidTest(Position target, String testName) {
        Position source = Position.of(File.c, Rank.Three);
        board.move(source, target);

        assertThat(board.getPiece(target)).isSameAs(sourcePiece);
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.d, Rank.Four), "킹은 오른쪽위로 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Three), "킹은 오른쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.d, Rank.Two), "킹은 오른쪽아래로 이동할 수 있다."),
            Arguments.of(Position.of(File.c, Rank.Two), "킹은 아래로 이동할 수 있다."),
            Arguments.of(Position.of(File.b, Rank.Two), "킹은 왼쪽아래로 이동할 수 있다."),
            Arguments.of(Position.of(File.b, Rank.Three), "킹은 왼쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.b, Rank.Four), "킹은 왼쪽위로 이동할 수 있다."),
            Arguments.of(Position.of(File.c, Rank.Four), "킹은 이동하려는 칸에 다른 색의 기물이 있을경우 기물을 잡고 그 위치로 이동한다.")
        );
    }
}
