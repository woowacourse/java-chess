package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("체스판 중 Pawn을 제외한 기물을 생성할 수 있다.")
    void createBoardOfExcludePawn() {
        Board board = new Board();

        assertAll(
                () -> assertThat(board.findByPosition(new Position(File.A, Rank.ONE))).isInstanceOf(Rook.class),
                () -> assertThat(board.findByPosition(new Position(File.B, Rank.ONE))).isInstanceOf(Knight.class),
                () -> assertThat(board.findByPosition(new Position(File.C, Rank.ONE))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findByPosition(new Position(File.D, Rank.ONE))).isInstanceOf(Queen.class),
                () -> assertThat(board.findByPosition(new Position(File.E, Rank.ONE))).isInstanceOf(King.class),
                () -> assertThat(board.findByPosition(new Position(File.F, Rank.ONE))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findByPosition(new Position(File.G, Rank.ONE))).isInstanceOf(Knight.class),
                () -> assertThat(board.findByPosition(new Position(File.H, Rank.ONE))).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("체스판 중 Pawn 기물을 생성할 수 있다.")
    void createBoardOfPawn() {
        Board board = new Board();

        assertThat(board.findByPosition(new Position(File.A, Rank.TWO))).isInstanceOf(Pawn.class);
    }
}
