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

@DisplayName("퀸 움직임 전략")
class QueenMoveStrategyTest {

    private MoveStrategy moveStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        moveStrategy = new QueenMoveStrategy();
        board = new Board();
    }

    @DisplayName("퀸은 수평 이동이 들어오면 이동 가능을 반환한다.")
    @Test
    void horizontalMove() {
        // given
        Square source = Square.of(File.d, Rank.SIX);
        Square destination = Square.of(File.h, Rank.SIX);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("퀸은 수직 이동이 들어오면 이동 가능을 반환한다.")
    @Test
    void verticalMove() {
        // given
        Square source = Square.of(File.d, Rank.SIX);
        Square destination = Square.of(File.d, Rank.THREE);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("퀸은 대각 이동이 들어오면 이동 가능을 반환한다.")
    @Test
    void diagonalMove() {
        // given
        Square source = Square.of(File.d, Rank.SIX);
        Square destination = Square.of(File.g, Rank.THREE);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("퀸은 잘못된 이동이 들어오면 이동 불가능을 반환한다.")
    @Test
    void cannotMove() {
        // given
        Square source = Square.of(File.d, Rank.EIGHT);
        Square destination = Square.of(File.c, Rank.SIX);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = moveStrategy.check(source, destination, board);

        // then
        assertThat(actual).isFalse();
    }
}
