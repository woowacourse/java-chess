package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.strategy.CreateBoard;
import chess.domain.board.strategy.CreateMockBoardStrategy;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

@DisplayName("Board 테스트")
class BoardTest {

    @DisplayName("알맞은 개수의 말이 생성된다.")
    @ParameterizedTest(name = "{index} {displayName} piece={0} count={1}")
    @MethodSource("providePieceAndExpectedCount")
    void valid_Count(final Piece piece, final int expected) {
        Board board = new Board(new CreateBoard());
        Map<Position, Piece> pieces = board.getPieces();
        final int actual = (int)pieces.values()
            .stream()
            .filter(piece::equals)
            .count();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePieceAndExpectedCount() {
        return Stream.of(
            Arguments.of(new Pawn(Color.BLACK), 8),
            Arguments.of(new Rook(Color.BLACK), 2),
            Arguments.of(new Bishop(Color.BLACK), 2),
            Arguments.of(new Knight(Color.BLACK), 2),
            Arguments.of(new King(Color.BLACK), 1),
            Arguments.of(new Queen(Color.BLACK), 1)
        );
    }

    @DisplayName("나이트를 이동시킬 때")
    @Nested
    class KnightMovingTest {
        @DisplayName("도착 지점에 같은 팀의 말이 존재할 경우 예외를 반환한다.")
        @Test
        void same_Color_Piece_In_Target_Point() {
            Piece startPiece = new Knight(Color.BLACK);
            Piece existPiece = new Knight(Color.BLACK);
            Position start = new Position(Row.FIRST, Column.a);
            Position target = new Position(Row.THIRD, Column.b);

            Board board = new Board(new CreateMockBoardStrategy(Map.of(start, startPiece, target, existPiece)));

            assertThatThrownBy(() -> board.move(start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은 팀의 다른 말이 존재해 이동할 수 없습니다.");
        }

        @DisplayName("경로에 말 존재 엽주와 관련 없이 말이 이동한다.")
        @Test
        void success_Move() {
            Piece startPiece = new Knight(Color.BLACK);
            Piece existPiece = new Knight(Color.BLACK);
            Position start = new Position(Row.FIRST, Column.a);
            Position midPoint = new Position(Row.SECOND, Column.a);
            Position target = new Position(Row.THIRD, Column.b);

            Board board = new Board(new CreateMockBoardStrategy(Map.of(start, startPiece, midPoint, existPiece)));

            board.move(start, target);
            Map<Position, Piece> pieces = board.getPieces();

            Assertions.assertAll(
                () -> assertThat(pieces.get(target)).isEqualTo(startPiece),
                () -> assertThat(pieces.get(start)).isNull());
        }
    }

    @DisplayName("말을 이동시킬 때")
    @Nested
    class CommonMovingTest {

        @DisplayName("빈 칸을 이동시킬 말로 지정할 경우 예외를 반환한다.")
        @Test
        void designate_Empty_Space() {
            Board board = new Board(new CreateMockBoardStrategy(new HashMap<>()));
            Position start = new Position(Row.FIRST, Column.a);
            Position target = new Position(Row.SECOND, Column.b);

            assertThatThrownBy(() -> board.move(start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치에 말이 존재하지 않습니다.");
        }

        @DisplayName("중간 경로에 다른 말이 존재할 경우 예외를 반환한다.")
        @Test
        void other_Piece_In_Path() {
            Piece startPiece = new Rook(Color.BLACK);
            Piece existPiece = new Rook(Color.BLACK);
            Position start = new Position(Row.FIRST, Column.a);
            Position midpoint = new Position(Row.SECOND, Column.a);
            Position target = new Position(Row.THIRD, Column.a);

            Board board = new Board(new CreateMockBoardStrategy(Map.of(start, startPiece, midpoint, existPiece)));

            assertThatThrownBy(() -> board.move(start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 말이 경로에 존재해 이동할 수 없습니다.");
        }

        @DisplayName("도착 지점에 같은 팀의 말이 존재할 경우 예외를 반환한다.")
        @Test
        void same_Color_Piece_In_Target_Point() {
            Piece startPiece = new Rook(Color.BLACK);
            Piece existPiece = new Rook(Color.BLACK);
            Position start = new Position(Row.FIRST, Column.a);
            Position target = new Position(Row.THIRD, Column.a);

            Board board = new Board(new CreateMockBoardStrategy(Map.of(start, startPiece, target, existPiece)));

            assertThatThrownBy(() -> board.move(start, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은 팀의 다른 말이 존재해 이동할 수 없습니다.");
        }

        @DisplayName("경로에 아무런 말이 없을 경우 말이 이동한다.")
        @Test
        void success_Move() {
            Piece startPiece = new King(Color.BLACK);
            Position start = new Position(Row.FIRST, Column.a);
            Position target = new Position(Row.SECOND, Column.a);

            Board board = new Board(new CreateMockBoardStrategy(Map.of(start, startPiece)));

            board.move(start, target);
            Map<Position, Piece> pieces = board.getPieces();

            Assertions.assertAll(
                () -> assertThat(pieces.get(target)).isEqualTo(startPiece),
                () -> assertThat(pieces.get(start)).isNull());
        }
    }

}