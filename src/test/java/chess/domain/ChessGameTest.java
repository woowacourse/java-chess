package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Camp;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("기물이 가로막고 있으면 통과 할 수 없다.")
    void moveBlockTest() {
        PiecesPosition piecesPosition = new PiecesPosition();
        ChessGame chessGame = new ChessGame(piecesPosition);

        Position whiteRookPosition = Position.of(File.A, Rank.ONE);
        Position toPosition = Position.of(File.A, Rank.THREE);

        assertThatThrownBy(() -> chessGame.move(whiteRookPosition, toPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("나이트는 기물의 유무와 관계없이 이동할 수 있다.")
    void movePassKnightTest() {
        PiecesPosition piecesPosition = new PiecesPosition();
        ChessGame chessGame = new ChessGame(piecesPosition);

        Position whiteKnightPosition = Position.of(File.B, Rank.ONE);
        Position toPosition = Position.of(File.A, Rank.THREE);

        assertDoesNotThrow(() -> chessGame.move(whiteKnightPosition, toPosition));
    }

    @Test
    @DisplayName("빈칸을 이동 시킬 수 없다.")
    void moveEmptyExceptionTest() {
        PiecesPosition piecesPosition = new PiecesPosition();
        ChessGame chessGame = new ChessGame(piecesPosition);

        Position emptyPosition = Position.of(File.A, Rank.THREE);
        Position toPosition = Position.of(File.A, Rank.FOUR);

        assertThatThrownBy(() -> chessGame.move(emptyPosition, toPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일한 위치로 이동 시킬 수 없다.")
    void moveEqualPositionTest() {
        PiecesPosition piecesPosition = new PiecesPosition();
        ChessGame chessGame = new ChessGame(piecesPosition);

        Position toPosition = Position.of(File.A, Rank.FOUR);

        assertThatThrownBy(() -> chessGame.move(toPosition, toPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임의 현재 턴과 다른 진영의 기물을 선택할 수 없다.")
    void moveDifferentCampPieceAndTurnTest() {
        PiecesPosition piecesPosition = new PiecesPosition();
        ChessGame chessGame = new ChessGame(piecesPosition);
        assertThat(chessGame).extracting("turnCamp")
                .isEqualTo(Camp.WHITE);

        Position blackPawnPosition = Position.of(File.A, Rank.SEVEN);

        assertThatThrownBy(() -> chessGame.move(blackPawnPosition, Position.of(File.A, Rank.SIX)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
