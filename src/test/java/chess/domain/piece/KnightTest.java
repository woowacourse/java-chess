package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;

public class KnightTest {

    private ChessBoard board;
    private Piece sourcePiece;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        Position source = Position.of(File.d, Rank.Four);
        sourcePiece = new Knight(Color.Black);

        board.putPiece(source, sourcePiece);
        board.putPiece(Position.of(File.b, Rank.Three), new Knight(Color.White));

        for (Direction direction : Direction.all()) {
            board.putPiece(source.getNext(direction), new Knight(Color.Black));
        }
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("나이트가 이동할 수 없는 곳으로 이동")
    void knightInvalidTest(Position target, String testName) {
        Position source = Position.of(File.d, Rank.Four);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.d, Rank.Five), "나이트는 위로 이동할 수 없다."),
            Arguments.of(Position.of(File.e, Rank.Five), "나이트는 오른쪽위로 이동할 수 없다."),
            Arguments.of(Position.of(File.e, Rank.Four), "나이트는 오른쪽으로 이동할 수 없다."),
            Arguments.of(Position.of(File.e, Rank.Three), "나이트는 오른쪽아래로 이동할 수 없다."),
            Arguments.of(Position.of(File.d, Rank.Three), "나이트는 아래로 이동할 수 없다."),
            Arguments.of(Position.of(File.c, Rank.Three), "나이트는 왼쪽아래로 이동할 수 없다."),
            Arguments.of(Position.of(File.c, Rank.Four), "나이트는 왼쪽으로 이동할 수 없다."),
            Arguments.of(Position.of(File.c, Rank.Five), "나이트는 왼쪽위로 이동할 수 없다.")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("validParameters")
    @DisplayName("나이트가 기물이 있을 경우에도 뛰어넘어 이동할 수 있는 곳으로 이동")
    void knightValidTest(Position target, String testName) {
        Position source = Position.of(File.d, Rank.Four);
        board.move(source, target);

        assertThat(board.getPiece(target)).isSameAs(sourcePiece);
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.b, Rank.Five), "나이트는 위 왼쪽 왼쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.c, Rank.Six), "나이트는 위 위 왼쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.e, Rank.Six), "나이트는 위 위 오른쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.f, Rank.Five), "나이트는 위 오른쪽 오른쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.f, Rank.Three), "나이트는 아래 오른쪽 오른쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.e, Rank.Two), "나이트는 아래 아래 오른쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.c, Rank.Two), "나이트는 아래 아래 왼쪽으로 이동할 수 있다."),
            Arguments.of(Position.of(File.b, Rank.Three), "나이트는 기물이 있을 경우 잡고 이동한다.")
        );
    }
}
