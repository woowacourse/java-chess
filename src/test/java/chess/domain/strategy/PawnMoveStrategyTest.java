package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰 움직임 전략")
public class PawnMoveStrategyTest {

    private MoveStrategy moveStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        moveStrategy = new PawnMoveStrategy();
        board = new Board();
    }

    @DisplayName("폰은 올바른 위치가 입력되면 이동 가능을 반환한다.")
    @Test
    void pawnCorrectDestination() {
        // given
        Square source = Square.of(File.a, Rank.THREE);
        Square destination = Square.of(File.a, Rank.FOUR);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 잘못된 위치가 입력되면 이동 불가능을 반환한다.")
    @Test
    void pawnInCorrectDestination() {
        // given
        Square source = Square.of(File.a, Rank.THREE);
        Square destination = Square.of(File.c, Rank.FOUR);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 상대 말이 있을 경우 대각선 1칸이 입력되면 이동 가능을 반환한다.")
    @Test
    void moveOneDiagonal() {
        // given
        Square source = Square.of(File.a, Rank.THREE);
        Square destination = Square.of(File.b, Rank.FOUR);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }
}
