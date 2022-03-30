package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.CachedPosition;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Board 는")
class BoardTest {

    @DisplayName("알맞은 개수의 말이 생성된다.")
    @ParameterizedTest(name = "{index} {displayName} piece={0} count={1}")
    @MethodSource("providePieceAndExpectedCount")
    void valid_Count(final Piece piece, final int expected) {
        final Map<Position, Piece> initialPieces = (new CreateCompleteBoardStrategy()).createPieces();
        Board board = new Board(initialPieces);
        Map<Position, Piece> pieces = board.getPieces();
        final int actual = (int) pieces.values()
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
            Position start = CachedPosition.a1;
            Position target = CachedPosition.b3;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, target, existPiece));
            Board board = new Board(initialPieces);

            assertThatThrownBy(() -> board.move(start, target, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("같은 팀의 다른 말이 존재해 이동할 수 없습니다.");
        }

        @DisplayName("경로에 말 존재 여부와 관련 없이 말이 이동한다.")
        @Test
        void success_Move() {
            Piece startPiece = new Knight(Color.BLACK);
            Piece existPiece = new Knight(Color.BLACK);
            Position start = CachedPosition.a1;
            Position midPoint = CachedPosition.a2;
            Position target = CachedPosition.b3;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, midPoint, existPiece));
            Board board = new Board(initialPieces);

            board.move(start, target, Color.BLACK);
            Map<Position, Piece> pieces = board.getPieces();

            assertAll(
                    () -> assertThat(pieces.get(target)).isEqualTo(startPiece),
                    () -> assertThat(pieces.get(start)).isNull());
        }
    }

    @DisplayName("폰을 이동시킬 때")
    @Nested
    class PawnTest {
        @DisplayName("대각선 한 칸 앞에 상대 말이 있으면 이동시킬 수 있다")
        @Test
        void move_Diagonal_To_Catch_Enemy() {
            Piece startPiece = new Pawn(Color.WHITE);
            Piece existPiece = new Knight(Color.BLACK);
            Position start = CachedPosition.a1;
            Position target = CachedPosition.b2;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, target, existPiece));
            Board board = new Board(initialPieces);

            board.move(start, target, Color.WHITE);
            Map<Position, Piece> pieces = board.getPieces();

            assertAll(
                    () -> assertThat(pieces.get(target)).isEqualTo(startPiece),
                    () -> assertThat(pieces.get(start)).isNull());
        }

        @DisplayName("대각선 한 칸 앞에 상대 말이 없으면 예외가 발생한다.")
        @Test
        void same_Color_Piece_In_Diagonal() {
            Piece startPiece = new Pawn(Color.WHITE);
            Piece existPiece = new Knight(Color.WHITE);
            Position start = CachedPosition.a1;
            Position target = CachedPosition.b2;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, target, existPiece));
            Board board = new Board(initialPieces);

            assertThatThrownBy(() -> board.move(start, target, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("폰은 상대 말을 공격할 때만 대각선으로 이동할 수 있습니다.");
        }

        @DisplayName("처음 두 칸 이동할 떄 경로에 말이 존재하는 경우 예외를 반환한다")
        @Test
        void other_Piece_In_Path() {
            Piece startPiece = new Pawn(Color.WHITE);
            Piece existPiece = new Knight(Color.WHITE);
            Position start = CachedPosition.a2;
            Position midPoint = CachedPosition.a3;
            Position target = CachedPosition.a4;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, midPoint, existPiece));
            Board board = new Board(initialPieces);

            assertThatThrownBy(() -> board.move(start, target, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("다른 말이 경로에 존재해 이동할 수 없습니다.");
        }

        @DisplayName("한 칸 이동할 떄 말이 존재하는 경우 예외를 반환한다")
        @Test
        void other_Piece_In_Destination() {
            Piece startPiece = new Pawn(Color.WHITE);
            Piece existPiece = new Knight(Color.WHITE);
            Position start = CachedPosition.a3;
            Position target = CachedPosition.a4;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, target, existPiece));
            Board board = new Board(initialPieces);

            assertThatThrownBy(() -> board.move(start, target, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("다른 말이 존재해 이동할 수 없습니다.");
        }

        @DisplayName("직진 성공")
        @Test
        void move_Success() {
            Piece startPiece = new Pawn(Color.WHITE);
            Position start = CachedPosition.a3;
            Position target = CachedPosition.a4;

            final Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece));
            Board board = new Board(initialPieces);
            board.move(start, target, Color.WHITE);
            Map<Position, Piece> pieces = board.getPieces();
            assertAll(
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
            Board board = new Board(new HashMap<>());
            Position start = CachedPosition.a1;
            Position target = CachedPosition.b2;

            assertThatThrownBy(() -> board.move(start, target, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("해당 위치에 말이 존재하지 않습니다.");
        }

        @DisplayName("중간 경로에 다른 말이 존재할 경우 예외를 반환한다.")
        @Test
        void other_Piece_In_Path() {
            Piece startPiece = new Rook(Color.BLACK);
            Piece existPiece = new Rook(Color.BLACK);
            Position start = CachedPosition.a1;
            Position midpoint = CachedPosition.a2;
            Position target = CachedPosition.a3;

            Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, midpoint, existPiece));
            Board board = new Board(initialPieces);

            assertThatThrownBy(() -> board.move(start, target, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("다른 말이 경로에 존재해 이동할 수 없습니다.");
        }

        @DisplayName("도착 지점에 같은 팀의 말이 존재할 경우 예외를 반환한다.")
        @Test
        void same_Color_Piece_In_Target_Point() {
            Piece startPiece = new Rook(Color.BLACK);
            Piece existPiece = new Rook(Color.BLACK);
            Position start = CachedPosition.a1;
            Position target = CachedPosition.a3;

            Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, target, existPiece));
            Board board = new Board(initialPieces);

            assertThatThrownBy(() -> board.move(start, target, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("같은 팀의 다른 말이 존재해 이동할 수 없습니다.");
        }

        @DisplayName("경로에 아무런 말이 없을 경우 말이 이동한다.")
        @Test
        void success_Move() {
            Piece startPiece = new King(Color.BLACK);
            Position start = CachedPosition.a1;
            Position target = CachedPosition.a2;

            Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece));
            Board board = new Board(initialPieces);

            board.move(start, target, Color.BLACK);
            Map<Position, Piece> pieces = board.getPieces();

            assertAll(
                    () -> assertThat(pieces.get(target)).isEqualTo(startPiece),
                    () -> assertThat(pieces.get(start)).isNull());
        }
    }

    @DisplayName("상대방 말을 이동시키려 할 경우 예외를 반환한다.")
    @Test
    void same_Color_Piece_In_Target_Point() {
        Piece startPiece = new Rook(Color.BLACK);
        Piece existPiece = new Rook(Color.BLACK);
        Position start = CachedPosition.a1;
        Position target = CachedPosition.a3;

        Map<Position, Piece> initialPieces = new HashMap<>(Map.of(start, startPiece, target, existPiece));
        Board board = new Board(initialPieces);

        assertThatThrownBy(() -> board.move(start, target, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편 말은 욺직일 수 없습니다.");
    }

    @DisplayName("특정 말의 갯수게 제대로 카운트 되는지 확인")
    @Test
    void count_Specific_Piece() {
        final Map<Position, Piece> initialPieces = (new CreateCompleteBoardStrategy()).createPieces();
        Board board = new Board(initialPieces);
        final int actualCount = board.countPiece(PieceType.ROOK, Color.BLACK);

        assertThat(actualCount).isEqualTo(2);
    }

    @DisplayName("현재 보드에 있는 말들의 올바른 총 점수를 계산해야 한다")
    @Test
    void calculate_Score() {
        final Map<Position, Piece> initialPieces = (new CreateCompleteBoardStrategy()).createPieces();
        Board board = new Board(initialPieces);
        final double actualBlackScore = board.calculateScore(Color.BLACK);
        final double actualWhiteScore = board.calculateScore(Color.WHITE);

        assertAll(
                () -> assertThat(actualBlackScore).isEqualTo(38.0),
                () -> assertThat(actualWhiteScore).isEqualTo(38.0)
        );
    }
}

