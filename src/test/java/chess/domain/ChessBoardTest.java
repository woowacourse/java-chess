package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.util.ChessBoardInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    private final ChessBoard chessBoard = ChessBoardInitializer.init();
    private final Turn whiteTurn = new Turn(Color.WHITE);
    private final Turn blackTurn = new Turn(Color.BLACK);

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._룩의 경우")
    @Test
    void existInWayRook() {
        // given
        final Position currentPosition = new Position(File.A, Rank.ONE);
        final Position nextPosition = new Position(File.A, Rank.FOUR);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._나이트의 경우")
    @Test
    void existInWayNight() {
        // given
        final Position currentPosition = new Position(File.B, Rank.ONE);
        final Position nextPosition = new Position(File.B, Rank.TWO);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._비숍의 경우")
    @Test
    void existInWayBishop() {
        // given
        final Position currentPosition = new Position(File.C, Rank.ONE);
        final Position nextPosition = new Position(File.C, Rank.TWO);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다.킹의 경우")
    @Test
    void existInWayKing() {
        // given
        final Position currentPosition = new Position(File.E, Rank.ONE);
        final Position nextPosition = new Position(File.E, Rank.TWO);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다.퀸의 경우")
    @Test
    void existInWayQueen() {
        // given
        final Position currentPosition = new Position(File.D, Rank.ONE);
        final Position nextPosition = new Position(File.D, Rank.THREE);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("전략상 이동할 수 없는 위치이다.")
    @Test
    void canNotMoveTo() {
        // given
        final Position currentPosition = new Position(File.A, Rank.TWO); // 폰
        final Position nextPosition = new Position(File.A, Rank.FIVE);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈칸이면 움직인다.")
    @Test
    void moveWhenEmpty() {
        // given
        final Position currentPosition = new Position(File.A, Rank.TWO); // 폰
        final Position nextPosition = new Position(File.A, Rank.FOUR);
        final Piece currentPiece = chessBoard.findPieceBy(currentPosition);

        // when
        chessBoard.move(whiteTurn, currentPosition, nextPosition);

        // then
        assertThat(chessBoard.findPieceBy(nextPosition)).isEqualTo(currentPiece);
    }

    @DisplayName("빈칸인데 경로상에 기물이 존재하면 움직일 수 없다.")
    @Test
    void canNotMoveByExistingPiece() {
        // given
        final Position currentPosition = new Position(File.A, Rank.ONE);
        final Position nextPosition = new Position(File.A, Rank.FIVE);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상대 기물을 잡으러 움직인다.")
    @Test
    void moveToCatch() {
        // given
        Position currentPosition = new Position(File.B, Rank.ONE); // 나이트
        final Piece originPiece = chessBoard.findPieceBy(currentPosition);
        Position nextPosition = new Position(File.C, Rank.THREE);
        chessBoard.move(whiteTurn, currentPosition, nextPosition);

        currentPosition = nextPosition;
        nextPosition = new Position(File.D, Rank.FIVE);
        chessBoard.move(whiteTurn, currentPosition, nextPosition);

        currentPosition = nextPosition;
        nextPosition = new Position(File.E, Rank.SEVEN);
        chessBoard.move(whiteTurn, currentPosition, nextPosition);

        // when && then
        final Piece currentPiece = chessBoard.findPieceBy(nextPosition);
        assertThat(currentPiece).isEqualTo(originPiece);
    }

    @DisplayName("상대 기물을 잡으러 움직이는 도중에 기물이 존재하면 움직일 수 없다.")
    @Test
    void canNotMoveToCatchByExistingPiece() {
        // given
        final Position currentPosition = new Position(File.A, Rank.ONE); // 룩
        final Position nextPosition = new Position(File.A, Rank.EIGHT);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(whiteTurn, currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
