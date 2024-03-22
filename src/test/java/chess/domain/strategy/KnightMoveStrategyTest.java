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

@DisplayName("나이트 움직임 전략")
class KnightMoveStrategyTest {

    private MoveStrategy moveStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        moveStrategy = new KnightMoveStrategy();
        board = new Board();
    }

    @DisplayName("나이트는 올바른 위치가 들어오면 이동 가능을 반환한다.")
    @Test
    void knightCorrectDestination() {
        // given
        Square source = Square.of(File.B, Rank.EIGHT);
        Square destination = Square.of(File.C, Rank.SIX);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("나이트는 잘못된 위치가 들어오면 이동 불가능을 반환한다.")
    @Test
    void knightInCorrectDestination() {
        // given
        Square source = Square.of(File.B, Rank.EIGHT);
        Square destination = Square.of(File.B, Rank.SIX);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isFalse();
    }
}
