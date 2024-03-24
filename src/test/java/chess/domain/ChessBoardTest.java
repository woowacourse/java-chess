package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    final ChessBoard chessBoard = ChessBoardMaker.init();

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._룩의 경우")
    @Test
    void existInWayRook() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.A, Rank.FOUR);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._나이트의 경우")
    @Test
    void existInWayNight() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.ONE);
        final Position targetPosition = new Position(File.B, Rank.TWO);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._비숍의 경우")
    @Test
    void existInWayBishop() {
        // given
        final Position sourcePosition = new Position(File.C, Rank.ONE);
        final Position targetPosition = new Position(File.C, Rank.TWO);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다.킹의 경우")
    @Test
    void existInWayKing() {
        // given
        final Position sourcePosition = new Position(File.E, Rank.ONE);
        final Position targetPosition = new Position(File.E, Rank.TWO);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다.퀸의 경우")
    @Test
    void existInWayQueen() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.ONE);
        final Position targetPosition = new Position(File.D, Rank.THREE);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("전략상 이동할 수 없는 위치이다.")
    @Test
    void canNotMoveTo() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.TWO); // 폰
        final Position targetPosition = new Position(File.A, Rank.FIVE);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈칸이면 움직인다.")
    @Test
    void moveWhenEmpty() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.TWO); // 폰
        final Position targetPosition = new Position(File.A, Rank.FOUR);
        final Piece sourcePiece = chessBoard.findPieceBy(sourcePosition);

        // when
        chessBoard.move(sourcePosition, targetPosition);

        // then
        assertThat(chessBoard.getPieces().get(targetPosition)).isEqualTo(sourcePiece);
    }

    @DisplayName("빈칸인데 경로상에 기물이 존재하면 움직일 수 없다.")
    @Test
    void canNotMoveByExistingPiece() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE); // 룩
        final Position targetPosition = new Position(File.A, Rank.FIVE);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상대 기물을 잡으러 움직인다.")
    @Test
    void moveToCatch() {
        // given
        Position sourcePosition = new Position(File.B, Rank.ONE); // 나이트
        final Piece originPiece = chessBoard.findPieceBy(sourcePosition);
        Position targetPosition = new Position(File.C, Rank.THREE);
        chessBoard.move(sourcePosition, targetPosition);

        sourcePosition = targetPosition;
        targetPosition = new Position(File.D, Rank.FIVE);
        chessBoard.move(sourcePosition, targetPosition);

        sourcePosition = targetPosition;
        targetPosition = new Position(File.E, Rank.SEVEN);
        chessBoard.move(sourcePosition, targetPosition);

        // when && then
        final Piece sourcePiece = chessBoard.findPieceBy(targetPosition);
        assertThat(sourcePiece).isEqualTo(originPiece);
    }

    @DisplayName("상대 기물을 잡으러 움직이는 도중에 기물이 존재하면 움직일 수 없다.")
    @Test
    void canNotMoveToCatchByExistingPiece() {
        // given
        final Position sourcePosition = new Position(File.A, Rank.ONE); // 룩
        final Position targetPosition = new Position(File.A, Rank.EIGHT);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
