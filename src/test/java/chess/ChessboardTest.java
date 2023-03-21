package chess;

import chess.domain.BoardInitializer;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ChessboardTest {
    @DisplayName("체스판은 64개의 Square로 이루어진다.")
    @Test
    void createChessboardSuccessTest() {
        Assertions.assertThat(new Chessboard())
                .extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Square.class, Piece.class))
                .hasSize(64);
    }

    @DisplayName("경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.A, Rank.EIGHT)))
                .isFalse();
    }

    @DisplayName("경로 사이가 비어있는 경우, false를 반환한다.")
    @Test
    void isEmptyInRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.A, Rank.SEVEN)))
                .isTrue();
    }

    @DisplayName("대각선 경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInDiagonalRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.G, Rank.EIGHT)))
                .isFalse();
    }

    @DisplayName("대각선 경로 사이가 비어있는 경우, true를 반환한다.")
    @Test
    void isEmptyInDiagonalRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.F, Rank.SEVEN)))
                .isTrue();
    }
}
