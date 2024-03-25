package domain.game;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static domain.Fixture.Positions.*;
import static domain.Fixture.Pieces.*;
import static domain.game.TeamColor.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {
    @DisplayName("기물의 시작 위치를 배치한 Board 인스턴스를 생성한다.")
    @Test
    void createBoard() {
        // When
        Board board = BoardInitializer.init();

        // Then
        assertThat(board).isNotNull();
    }

    @DisplayName("각 기물의 전략에 따라 기물을 이동한다.")
    @Nested
    class MovePieceTest {
        @DisplayName("기물의 이동 목적지가 비어있으면 이동시킬 수 있다.")
        @Test
        void movePieceToEmptySpaceTest() {
            // Given
            Piece piece = PieceFactory.create(PieceType.WHITE_BISHOP);
            Position source = B2;
            Position destination = Position.of(File.D, Rank.FOUR);
            Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, piece));
            Board board = new Board(piecePositions);

            // When
            board.movePiece(WHITE, source, destination);

            // Then
            assertThat(piecePositions).doesNotContainKey(source).containsKey(destination);
        }

        @DisplayName("기물의 이동 목적지에 다른 색의 기물이 있으면 이동시킬 수 있다.")
        @Test
        void movePieceToEnemySpaceTest() {
            // Given
            Piece piece = PieceFactory.create(PieceType.WHITE_KNIGHT);
            Piece enemy = PieceFactory.create(PieceType.BLACK_KING);
            Position source = B2;
            Position destination = C4;
            Map<Position, Piece> piecePositions = new HashMap<>(Map.of(
                    source, piece,
                    destination, enemy
            ));
            Board board = new Board(piecePositions);

            // When
            board.movePiece(WHITE, source, destination);

            // Then
            assertThat(piecePositions).doesNotContainKey(source).containsKey(destination);
        }

        @DisplayName("기물의 이동 목적지에 같은 색의 기물이 있으면 이동시킬 수 없다.")
        @Test
        void notMovePieceTest() {
            // Given
            Piece piece = PieceFactory.create(PieceType.WHITE_KNIGHT);
            Piece other = PieceFactory.create(PieceType.WHITE_ROOK);
            Position source = B2;
            Position destination = C4;
            Map<Position, Piece> piecePositions = new HashMap<>(Map.of(
                    source, piece,
                    destination, other
            ));
            Board board = new Board(piecePositions);

            // When & Then
            assertThatThrownBy(() -> board.movePiece(WHITE, source, destination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동 위치에 아군 기물이 존재합니다.");
        }
    }

    @DisplayName("점수 계산 테스트")
    @Nested
    class CalculateScoreTest {
        @Test
        @DisplayName("초기 상태의 점수는 흑/백 모두 38점이다.")
        void initialScoreTest() {
            // Given
            Board board = BoardInitializer.init();

            // When
            double whiteScore = board.calculateScoreOf(WHITE);
            double blackScore = board.calculateScoreOf(BLACK);

            // Then
            double expected = 9 + 2 * (5 + 3 + 2.5) + (1 * 8);
            assertThat(whiteScore).isEqualTo(blackScore).isEqualTo(expected);
        }

        @Test
        @DisplayName("각 폰이 모두 각자의 세로줄에 존재하는 경우, 모든 기물들은 각자의 평가치에 맞게 평가된다.")
        void calculateScoreTest() {
            // Given
            Map<Position, Piece> piecePositions = Map.ofEntries(
                    Map.entry(A7, BLACK_PAWN_PIECE),
                    Map.entry(B6, BLACK_PAWN_PIECE),
                    Map.entry(B8, BLACK_KING_PIECE),
                    Map.entry(C7, BLACK_PAWN_PIECE),
                    Map.entry(C8, BLACK_ROOK_PIECE),
                    Map.entry(D7, BLACK_BISHOP_PIECE),
                    Map.entry(E6, BLACK_QUEEN_PIECE),

                    Map.entry(E1, WHITE_ROOK_PIECE),
                    Map.entry(E3, WHITE_PAWN_PIECE),
                    Map.entry(F1, WHITE_KING_PIECE),
                    Map.entry(F2, WHITE_PAWN_PIECE),
                    Map.entry(F4, WHITE_KNIGHT_PIECE),
                    Map.entry(G2, WHITE_PAWN_PIECE),
                    Map.entry(G4, WHITE_QUEEN_PIECE),
                    Map.entry(H3, WHITE_PAWN_PIECE)
            );
            Board board = new Board(piecePositions);

            // When
            double whiteScore = board.calculateScoreOf(WHITE);
            double blackScore = board.calculateScoreOf(BLACK);

            // Then
            double expectedWhiteScore = 5 + 2.5 + 9 + (1 * 4);  // 20.5
            double expectedBlackScore = 5 + 3 + 9 + (1 * 3) + 0;  // 20
            assertAll(
                    () -> assertThat(whiteScore).isEqualTo(expectedWhiteScore),
                    () -> assertThat(blackScore).isEqualTo(expectedBlackScore)
            );
        }

        @ParameterizedTest
        @MethodSource("pawnOnSameFileCase")
        @DisplayName("같은 세로줄에 같은 색의 폰이 존재하는 경우, 해당 폰은 원래 평가치의 절반으로 평가된다.")
        void pawnOnSameFileTest(Map<Position, Piece> piecePositions, double expectedWhiteScore, double expectedBlackScore) {
            // Given
            Board board = new Board(piecePositions);

            // When
            double whiteScore = board.calculateScoreOf(WHITE);
            double blackScore = board.calculateScoreOf(BLACK);

            // Then
            assertAll(
                    () -> assertThat(whiteScore).isEqualTo(expectedWhiteScore),
                    () -> assertThat(blackScore).isEqualTo(expectedBlackScore)
            );
        }

        static Stream<Arguments> pawnOnSameFileCase() {
            return Stream.of(
                    Arguments.of(Map.ofEntries(
                            Map.entry(E1, WHITE_ROOK_PIECE),
                            Map.entry(F1, WHITE_KING_PIECE),
                            Map.entry(F2, WHITE_PAWN_PIECE),  // 0.5
                            Map.entry(F3, WHITE_PAWN_PIECE),  // 0.5
                            Map.entry(F4, WHITE_KNIGHT_PIECE),
                            Map.entry(G2, WHITE_PAWN_PIECE),
                            Map.entry(G4, WHITE_QUEEN_PIECE),
                            Map.entry(H3, WHITE_PAWN_PIECE),
                            Map.entry(A7, BLACK_PAWN_PIECE),
                            Map.entry(B6, BLACK_PAWN_PIECE),
                            Map.entry(B8, BLACK_KING_PIECE),
                            Map.entry(C7, BLACK_PAWN_PIECE),
                            Map.entry(C8, BLACK_ROOK_PIECE),
                            Map.entry(D7, BLACK_BISHOP_PIECE),
                            Map.entry(E6, BLACK_QUEEN_PIECE)
                    ), 19.5, 20),
                    Arguments.of(Map.ofEntries(
                            Map.entry(A2, WHITE_PAWN_PIECE),  // 0.5
                            Map.entry(A3, WHITE_PAWN_PIECE),  // 0.5
                            Map.entry(C2, WHITE_PAWN_PIECE),
                            Map.entry(D7, BLACK_PAWN_PIECE),  // 0.5
                            Map.entry(D6, BLACK_PAWN_PIECE),  // 0.5
                            Map.entry(D5, BLACK_PAWN_PIECE),  // 0.5
                            Map.entry(C7, BLACK_PAWN_PIECE)
                    ), 2, 2.5),
                    Arguments.of(Map.ofEntries(
                            Map.entry(A2, BLACK_PAWN_PIECE),
                            Map.entry(A3, BLACK_PAWN_PIECE),
                            Map.entry(A4, BLACK_PAWN_PIECE),
                            Map.entry(A5, BLACK_PAWN_PIECE),
                            Map.entry(A6, BLACK_PAWN_PIECE),
                            Map.entry(A7, BLACK_PAWN_PIECE),
                            Map.entry(A8, BLACK_PAWN_PIECE)
                    ), 0, 3.5)
            );
        }
    }
}
