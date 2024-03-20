package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Bishop;
import chess.domain.Color;
import chess.domain.King;
import chess.domain.Knight;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Queen;
import chess.domain.Rook;
import chess.domain.TestBoardFactory;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GeneralMoveStrategyTest {

    @ParameterizedTest(name = "[{index}] 퀸이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"4,8", "8,4", "4,1", "1,4", "1,1", "8,8", "1,7", "7,1"})
    @DisplayName("퀸은 여덟 방향으로 모두 이동할 수 있다.")
    void queenMove(int x, int y) {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Queen(new Position(4, 4), Color.WHITE)
        ));
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);
        generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(x, y));

        assertThat(generalMoveStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_QUEEN);
    }

    @ParameterizedTest(name = "[{index}] 비숍이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"1,1", "8,8", "1,7", "7,1"})
    @DisplayName("비숍은 대각선 네방향으로 모두 이동할 수 있다.")
    void bishopMove(int x, int y) {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Bishop(new Position(4, 4), Color.WHITE)
        ));
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);
        generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(x, y));

        assertThat(generalMoveStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_BISHOP);
    }

    @ParameterizedTest(name = "[{index}] 룩이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"4,8", "8,4", "4,1", "1,4"})
    @DisplayName("룩은 네방향으로 모두 이동할 수 있다.")
    void rookMove(int x, int y) {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Rook(new Position(4, 4), Color.WHITE)
        ));
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);
        generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(x, y));

        assertThat(generalMoveStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_ROOK);
    }

    @ParameterizedTest(name = "[{index}] 나이트가 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"5,6", "6,5", "3,6", "6,3", "2,3", "3,2", "2,5", "5,2"})
    @DisplayName("나이트 이동")
    void knightMove(int x, int y) {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Knight(new Position(4, 4), Color.WHITE)
        ));
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);
        generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(x, y));

        assertThat(generalMoveStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_KNIGHT);
    }

    @ParameterizedTest(name = "[{index}] 킹이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"3,3", "3,4", "3,5", "4,5", "5,5", "5,4", "5,3", "4,3"})
    @DisplayName("킹은 여덟 방향으로 한 칸씩만 이동할 수 있다.")
    void kingMove(int x, int y) {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new King(new Position(4, 4), Color.WHITE)
        ));
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);
        generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(x, y));

        assertThat(generalMoveStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_KING);
    }

    @Test
    @DisplayName("이동 경로에 다른 말이 있으면 이동할 수 없다.")
    void movePieceWhenHasOtherPiece() {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Queen(new Position(4, 4), Color.WHITE),
                new Position(4, 3), new Queen(new Position(4, 3), Color.BLACK))
        );
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(4, 1)))
                .withMessage("이동할 수 없는 경로 입니다.");
    }

    @Test
    @DisplayName("도착지에 같은색 말이 있으면 이동할 수 없다.")
    void movePieceWhenHasSameColorPieceInDestination() {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Queen(new Position(4, 4), Color.WHITE),
                new Position(4, 1), new King(new Position(4, 1), Color.WHITE))
        );
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(4, 1)))
                .withMessage("이동할 수 없는 경로 입니다.");
    }

    @Test
    @DisplayName("도착지에 다른색 말이 있으면 상대 말을 잡고 해당 위치로 이동할 수 있다.")
    void movePieceWhenHasOtherColorPieceInDestination() {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new Queen(new Position(4, 4), Color.WHITE),
                new Position(4, 1), new King(new Position(4, 1), Color.BLACK))
        );
        GeneralMoveStrategy generalMoveStrategy = new GeneralMoveStrategy(board);
        generalMoveStrategy.move(Color.WHITE, new Position(4, 4), new Position(4, 1));

        Assertions.assertAll(
                () -> assertThat(generalMoveStrategy.collectBoard().get(new Position(4, 1))).isEqualTo(
                        PieceType.WHITE_QUEEN),
                () -> assertThat(generalMoveStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(PieceType.NONE),
                () -> assertThat(generalMoveStrategy.collectBoard()).doesNotContainValue(PieceType.BLACK_KING)
        );
    }
}