package chess.domain.piece.pawn;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.piece.Piece.createBlackPiece;
import static chess.domain.piece.Piece.createWhitePiece;
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
        source = Position.of('b', '7');
        pawn = new Pawn(BLACK);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,8", "b,7", "b,4", "a,8", "a,7"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어나면 예외 발생")
    void isMovableToEmptyPosition(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, createBlackPiece(new Pawn(BLACK))));

        assertThatThrownBy(() -> pawn.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @ParameterizedTest
    @MethodSource("cannotMoveToPiecePosition")
    @DisplayName("기물이 가로막을 경우의 전진 불가능")
    void cannotMoveToPiecePosition(Position target, Piece piece) {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createBlackPiece(new Pawn(BLACK)),
                target, piece));

        assertThatThrownBy(() -> pawn.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    private static Stream<Arguments> cannotMoveToPiecePosition() {
        return Stream.of(
                Arguments.of(Position.of('b', '5'), createWhitePiece(new Pawn(WHITE))),
                Arguments.of(Position.of('b', '6'), createWhitePiece(new Pawn(WHITE))),
                Arguments.of(Position.of('b', '5'), createBlackPiece(new Pawn(BLACK))),
                Arguments.of(Position.of('b', '6'), createBlackPiece(new Pawn(BLACK)))
        );
    }
}
