package chess.domain.piece.pawn;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceRule;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackFirstMovePawnTest {

    private PieceRule pawn;
    private Position source;

    @BeforeEach
    void setUp() {
        pawn = new BlackPawn();
        source = Position.of('b', '8');
    }

    @ParameterizedTest
    @CsvSource(value = {"b,8", "b,4", "a,8", "a,7"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어나면 예외 발생")
    void isMovableToEmptyPosition(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Piece(BLACK, new BlackPawn())));

        assertThatThrownBy(() -> pawn.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @ParameterizedTest
    @MethodSource("cannotMoveToPiecePosition")
    @DisplayName("기물이 가로막을 경우의 전진 불가능")
    void cannotMoveToPiecePosition(Position target, Piece piece) {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Piece(WHITE, new WhitePawn()),
                target, piece));

        assertThatThrownBy(() -> pawn.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    private static Stream<Arguments> cannotMoveToPiecePosition() {
        return Stream.of(
                Arguments.of(Position.of('b', '7'), new Piece(WHITE, new WhitePawn())),
                Arguments.of(Position.of('b', '6'), new Piece(WHITE, new WhitePawn())),
                Arguments.of(Position.of('b', '7'), new Piece(BLACK, new BlackPawn())),
                Arguments.of(Position.of('b', '6'), new Piece(BLACK, new BlackPawn()))
        );
    }
}
