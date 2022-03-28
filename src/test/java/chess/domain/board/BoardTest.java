package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.game.ChessGame;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("체스판 중 Pawn을 제외한 기물을 생성할 수 있다.")
    void createBoardOfExcludePawn() {
        Board board = new ChessGame().getBoard();

        assertAll(
                () -> assertThat(board.findByPiece(new Position(File.A, Rank.ONE))).isInstanceOf(Rook.class),
                () -> assertThat(board.findByPiece(new Position(File.B, Rank.ONE))).isInstanceOf(Knight.class),
                () -> assertThat(board.findByPiece(new Position(File.C, Rank.ONE))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findByPiece(new Position(File.D, Rank.ONE))).isInstanceOf(Queen.class),
                () -> assertThat(board.findByPiece(new Position(File.E, Rank.ONE))).isInstanceOf(King.class),
                () -> assertThat(board.findByPiece(new Position(File.F, Rank.ONE))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findByPiece(new Position(File.G, Rank.ONE))).isInstanceOf(Knight.class),
                () -> assertThat(board.findByPiece(new Position(File.H, Rank.ONE))).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("체스판 중 Pawn 기물을 생성할 수 있다.")
    void createBoardOfPawn() {
        Board board = new ChessGame().getBoard();

        assertThat(board.findByPiece(new Position(File.A, Rank.TWO))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("체스판의 말을 이동할 수 있다.")
    void movePiece() {
        Board board = new ChessGame().getBoard();

        board.move(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));

        assertAll(
                () -> assertThat(board.findByPiece(new Position(File.A, Rank.TWO)))
                        .isInstanceOf(EmptyPiece.class),
                () -> assertThat(board.findByPiece(new Position(File.A, Rank.THREE)))
                        .isInstanceOf(Pawn.class)
        );
    }

    @Test
    @DisplayName("체스판의 말이 이동할 수 없는 경우 예외가 발생한다.")
    void canNotMovePiece() {
        Board board = new ChessGame().getBoard();

        assertThatThrownBy(() -> board.move(new Position(File.A, Rank.ONE), new Position(File.A, Rank.THREE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 진영의 점수를 계산할 수 있다.")
    void calculateScoreOfTeam() {
        Board board = new ChessGame().getBoard();

        Map<Color, Double> colorsTotalScore = board.getColorsTotalScore();

        assertAll(
                () -> assertThat(colorsTotalScore.get(Color.BLACK)).isEqualTo(38),
                () -> assertThat(colorsTotalScore.get(Color.WHITE)).isEqualTo(38)
        );
    }
}
