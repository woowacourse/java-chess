package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardCreatorTest {

    private ChessBoard chessBoard;

    @BeforeEach
    public void setUp() {
        ChessBoardCreator creator = new ChessBoardCreator();
        chessBoard = creator.create();
    }

    @Test
    void testPawnsPlacement() {
        for (File file : File.values()) {
            assertThat(chessBoard.findPieceByPosition(new Position(file, Rank.TWO))).isInstanceOf(Pawn.class);
            assertThat(chessBoard.findPieceByPosition(new Position(file, Rank.SEVEN))).isInstanceOf(Pawn.class);
        }
    }

    @Test
    void testRooksPlacement() {
        assertThat(chessBoard.findPieceByPosition(new Position(File.A, Rank.ONE))).isInstanceOf(Rook.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.H, Rank.ONE))).isInstanceOf(Rook.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.A, Rank.EIGHT))).isInstanceOf(Rook.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.H, Rank.EIGHT))).isInstanceOf(Rook.class);
    }

    @Test
    void testKnightsPlacement() {
        assertThat(chessBoard.findPieceByPosition(new Position(File.B, Rank.ONE))).isInstanceOf(Knight.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.G, Rank.ONE))).isInstanceOf(Knight.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.B, Rank.EIGHT))).isInstanceOf(Knight.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.G, Rank.EIGHT))).isInstanceOf(Knight.class);
    }

    @Test
    void testBishopsPlacement() {
        assertThat(chessBoard.findPieceByPosition(new Position(File.C, Rank.ONE))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.F, Rank.ONE))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.C, Rank.EIGHT))).isInstanceOf(Bishop.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.F, Rank.EIGHT))).isInstanceOf(Bishop.class);
    }

    @Test
    void testQueensPlacement() {
        assertThat(chessBoard.findPieceByPosition(new Position(File.D, Rank.ONE))).isInstanceOf(Queen.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
    }

    @Test
    void testKingsPlacement() {
        assertThat(chessBoard.findPieceByPosition(new Position(File.E, Rank.ONE))).isInstanceOf(King.class);
        assertThat(chessBoard.findPieceByPosition(new Position(File.E, Rank.EIGHT))).isInstanceOf(King.class);
    }
}
