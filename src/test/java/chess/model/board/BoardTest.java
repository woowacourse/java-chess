package chess.model.board;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.A6;
import static chess.model.board.PositionFixture.A7;
import static chess.model.board.PositionFixture.F1;
import static chess.model.board.PositionFixture.F5;
import static chess.model.board.PositionFixture.H3;
import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.BISHOP;
import static chess.model.piece.PieceType.KING;
import static chess.model.piece.PieceType.KNIGHT;
import static chess.model.piece.PieceType.PAWN;
import static chess.model.piece.PieceType.QUEEN;
import static chess.model.piece.PieceType.ROOK;
import static chess.model.position.Rank.EIGHTH;
import static chess.model.position.Rank.FIRST;
import static chess.model.position.Rank.SECOND;
import static chess.model.position.Rank.SEVENTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.position.Positions;
import chess.model.position.Rank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Nested
    @DisplayName("create() 테스트")
    class CreateTest {

        @Test
        @DisplayName("64개의 칸을 가지는 체스판을 생성한다.")
        void constructor_whenCall_thenSuccess() {
            assertAll(
                    () -> assertThat(board).isExactlyInstanceOf(Board.class),
                    () -> assertThat(board)
                            .extracting("squares", InstanceOfAssertFactories.map(Position.class, Piece.class))
                            .hasSize(64)
            );
        }

        /**
         * rank = 8 RNBQKBNR
         * rank = 7 PPPPPPPP
         * rank = 2 pppppppp
         * rank = 1 rnbqkbnr
         */
        @Test
        @DisplayName("체스판에 정상적으로 기물이 생성 되었는지 확인한다.")
        void constructor_whenCall_thenReturnPieces() {
            //given
            final Map<Position, Piece> squares = board.getSquares();

            //when
            final List<Type> rank1 = getRank(squares, FIRST);
            final List<Type> rank2 = getRank(squares, SECOND);
            final List<Type> rank7 = getRank(squares, SEVENTH);
            final List<Type> rank8 = getRank(squares, EIGHTH);

            //then
            assertAll(
                    () -> assertThat(rank1).containsExactly(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP,
                            KNIGHT, ROOK),
                    () -> assertThat(rank2).containsExactly(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN),
                    () -> assertThat(rank7).containsExactly(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN),
                    () -> assertThat(rank8).containsExactly(ROOK, KNIGHT, BISHOP, QUEEN, KING,
                            BISHOP, KNIGHT, ROOK)
            );
        }

        private List<Type> getRank(final Map<Position, Piece> squares, final Rank rank) {
            return Positions.getPositionsBy(rank).stream()
                    .map(squares::get)
                    .map(Piece::getType)
                    .collect(Collectors.toList());
        }
    }

    @Nested
    @DisplayName("move() 테스트")
    class MoveTest {

        @Test
        @DisplayName("유효한 체스 판의 칸을 입력하면 정상적으로 기물을 이동시킨다")
        void move_givenValidSourceAndTarget_thenSuccess() {
            // when, then
            assertThatCode(() -> board.move(A2, A3, WHITE))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("source로 빈 칸을 선택하면 예외가 발생한다.")
        void move_givenEmptySource_thenFail() {
            // when, then
            assertThatThrownBy(() -> board.move(F5, H3, WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치에 기물이 없습니다.");
        }

        @Test
        @DisplayName("source로 상대방의 기물이 있는 칸을 선택하면 예외가 발생한다.")
        void move_givenEnemySource_thenFail() {
            // when, then
            assertThatThrownBy(() -> board.move(A7, A6, WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("자신의 기물이 아닙니다.");
        }

        @Test
        @DisplayName("source와 target 사이의 칸 중 기물이 존재하는 칸을 만나면 예외가 발생한다.")
        void move_givenInvalidSourceAndTarget_thenFail() {
            // when, then
            assertThatThrownBy(() -> board.move(F1, H3, WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 경로로 이동할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("findKing()을 테스트한다.")
    class findKingTest {
        @Test
        @DisplayName("킹이 있으면 true가 나온다.")
        void findKing_whenHasKing_thenReturnBoolean() {
            // when, then
            assertThat(board.findKing(WHITE)).isTrue();
        }

        /**
         * RNBQKB.R
         * PPPPPPPP
         * ........
         * ........
         * ........
         * ........
         * pppppp.p
         * rnbqNbnr
         */
        @Test
        @DisplayName("킹이 없으면 false가 나온다.")
        void findKing_whenNoKing_thenReturnBoolean() {
            // when
            killWhiteKing();

            // then
            assertThat(board.findKing(WHITE)).isFalse();
        }

        private void killWhiteKing() {
            board.move(PositionFixture.G8, PositionFixture.F6, BLACK);
            board.move(PositionFixture.F6, PositionFixture.D5, BLACK);
            board.move(PositionFixture.D5, PositionFixture.E3, BLACK);
            board.move(PositionFixture.E3, PositionFixture.G2, BLACK);
            board.move(PositionFixture.G2, PositionFixture.E1, BLACK);
        }
    }
}
