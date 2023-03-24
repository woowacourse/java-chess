package chess;

import chess.domain.BoardInitializer;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ChessboardTest {
    private Chessboard chessboard;

    @BeforeEach
    void setup() {
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
    }

    @DisplayName("체스판은 64개의 Square로 이루어진다.")
    @Test
    void createChessboardSuccessTest() {
        assertThat(new Chessboard())
                .extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Square.class, Piece.class))
                .hasSize(64);
    }

    @DisplayName("경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.A, Rank.EIGHT);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isFalse();
    }

    @DisplayName("경로 사이가 비어있는 경우, false를 반환한다.")
    @Test
    void isEmptyInRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.A, Rank.SEVEN);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isTrue();
    }

    @DisplayName("대각선 경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInDiagonalRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.G, Rank.EIGHT);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isFalse();
    }

    @DisplayName("대각선 경로 사이가 비어있는 경우, true를 반환한다.")
    @Test
    void isEmptyInDiagonalRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.F, Rank.SEVEN);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isTrue();
    }
}
