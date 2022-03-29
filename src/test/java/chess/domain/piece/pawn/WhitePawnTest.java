package chess.domain.piece.pawn;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
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

class WhitePawnTest {

    private PieceRule pawn;
    private Position source;

    @BeforeEach
    void setUp() {
        source = Position.of('b', '2');

        ChessBoard chessBoard = new ChessBoard(
                Map.of(Position.of('b', '1'), new Piece(WHITE, new WhitePawn())));
        pawn = new WhitePawn()
                .move(Position.of('b', '1'), source, chessBoard);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,4", "b,2", "a,2", "a,3"})
    @DisplayName("폰의 빈곳 전진 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Piece(WHITE, new WhitePawn())));

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
                Arguments.of(Position.of('b', '3'), new Piece(WHITE, new WhitePawn())),
                Arguments.of(Position.of('b', '3'), new Piece(BLACK, new BlackPawn()))
        );
    }

    @ParameterizedTest
    @MethodSource("canMoveToEnemyPiecePosition")
    @DisplayName("대각선 방향에 적이 있으면 전진 가능")
    void canMoveToEnemyPiecePosition(Position target, Piece piece) {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Piece(WHITE, new WhitePawn()),
                target, piece));

        assertThat(pawn.move(source, target, chessBoard)).isInstanceOf(WhitePawn.class);
    }

    private static Stream<Arguments> canMoveToEnemyPiecePosition() {
        return Stream.of(
                Arguments.of(Position.of('a', '3'), new Piece(BLACK, new BlackPawn()), true),
                Arguments.of(Position.of('c', '3'), new Piece(BLACK, new BlackPawn()), true)
        );
    }

}
