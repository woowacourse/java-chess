package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardFactoryTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        Map<Square, Piece> board = BoardFactory.make();
        assertThat(board.get(Square.of(File.A, Rank.ONE))).isInstanceOf(Rook.class);
        assertThat(board.get(Square.of(File.B, Rank.ONE))).isInstanceOf(Knight.class);
        assertThat(board.get(Square.of(File.C, Rank.ONE))).isInstanceOf(Bishop.class);
        assertThat(board.get(Square.of(File.D, Rank.ONE))).isInstanceOf(Queen.class);
        assertThat(board.get(Square.of(File.E, Rank.ONE))).isInstanceOf(King.class);
        assertThat(board.get(Square.of(File.F, Rank.ONE))).isInstanceOf(Bishop.class);
        assertThat(board.get(Square.of(File.G, Rank.ONE))).isInstanceOf(Knight.class);
        assertThat(board.get(Square.of(File.H, Rank.ONE))).isInstanceOf(Rook.class);
        assertThat(board.get(Square.of(File.A, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.B, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.C, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.D, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.E, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.F, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.G, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.H, Rank.TWO))).isInstanceOf(WhitePawn.class);
        assertThat(board.get(Square.of(File.A, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.B, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.C, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.D, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.E, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.F, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.G, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.H, Rank.SEVEN))).isInstanceOf(BlackPawn.class);
        assertThat(board.get(Square.of(File.A, Rank.EIGHT))).isInstanceOf(Rook.class);
        assertThat(board.get(Square.of(File.B, Rank.EIGHT))).isInstanceOf(Knight.class);
        assertThat(board.get(Square.of(File.C, Rank.EIGHT))).isInstanceOf(Bishop.class);
        assertThat(board.get(Square.of(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
        assertThat(board.get(Square.of(File.E, Rank.EIGHT))).isInstanceOf(King.class);
        assertThat(board.get(Square.of(File.F, Rank.EIGHT))).isInstanceOf(Bishop.class);
        assertThat(board.get(Square.of(File.G, Rank.EIGHT))).isInstanceOf(Knight.class);
        assertThat(board.get(Square.of(File.H, Rank.EIGHT))).isInstanceOf(Rook.class);
    }

}