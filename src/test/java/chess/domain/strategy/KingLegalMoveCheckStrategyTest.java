package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("킹 움직임 전략")
public class KingLegalMoveCheckStrategyTest {

    private LegalMoveCheckStrategy legalMoveCheckStrategy;
    private Board board;

    @BeforeEach
    void setUp() {
        legalMoveCheckStrategy = new KingLegalMoveCheckStrategy();
        board = new Board();
    }

    @DisplayName("킹은 수평 1칸 이동 위치가 들어오면 이동 가능을 반환한다.")
    @Test
    void horizontalMove() {
        // given
        Square source = Square.of(File.E, Rank.EIGHT);
        Square destination = Square.of(File.F, Rank.EIGHT);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("킹은 수직 1칸 이동 위치가 들어오면 이동 가능을 반환한다.")
    @Test
    void verticalMove() {
        // given
        Square source = Square.of(File.E, Rank.EIGHT);
        Square destination = Square.of(File.E, Rank.SEVEN);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("킹은 대각 1칸 이동 위치가 들어오면 이동 가능을 반환한다.")
    @Test
    void diagonalMove() {
        // given
        Square source = Square.of(File.E, Rank.EIGHT);
        Square destination = Square.of(File.D, Rank.SEVEN);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("킹은 잘못된 위치가 들어오면 이동 불가능을 반환한다.")
    @Test
    void cannotMove() {
        // given
        Square source = Square.of(File.E, Rank.EIGHT);
        Square destination = Square.of(File.C, Rank.SIX);
        Piece destinationPiece = board.findPieceBySquare(destination);

        // when
        boolean actual = legalMoveCheckStrategy.check(source, destination, board);

        // then
        assertThat(actual).isFalse();
    }
}
