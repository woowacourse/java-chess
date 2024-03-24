package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.Empty;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardMakerTest {

    @DisplayName("체스판을 초기화한다.")
    @Test
    void init() {
        // given
        final ChessBoard chessBoard = ChessBoardMaker.init();

        // when
        Map<Position, Piece> chessBoardDetail = chessBoard.getPieces();

        // then
        assertThat(chessBoardDetail).containsExactlyEntriesOf(createExpectedPieces());
    }

    private Map<Position, Piece> createExpectedPieces() {
        Map<Position, Piece> expectedPieces = new LinkedHashMap<>();

        // Black pieces
        expectedPieces.put(new Position(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        expectedPieces.put(new Position(File.B, Rank.EIGHT), new Night(Color.BLACK));
        expectedPieces.put(new Position(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        expectedPieces.put(new Position(File.D, Rank.EIGHT), new Queen(Color.BLACK));
        expectedPieces.put(new Position(File.E, Rank.EIGHT), new King(Color.BLACK));
        expectedPieces.put(new Position(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
        expectedPieces.put(new Position(File.G, Rank.EIGHT), new Night(Color.BLACK));
        expectedPieces.put(new Position(File.H, Rank.EIGHT), new Rook(Color.BLACK));

        expectedPieces.put(new Position(File.A, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.B, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.C, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.D, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.E, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.F, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.G, Rank.SEVEN), new Pawn(Color.BLACK));
        expectedPieces.put(new Position(File.H, Rank.SEVEN), new Pawn(Color.BLACK));

        // Empty spaces
        expectedPieces.put(new Position(File.A, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.B, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.C, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.D, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.E, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.F, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.G, Rank.SIX), new Empty());
        expectedPieces.put(new Position(File.H, Rank.SIX), new Empty());

        // Empty spaces
        expectedPieces.put(new Position(File.A, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.B, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.C, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.D, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.E, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.F, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.G, Rank.FIVE), new Empty());
        expectedPieces.put(new Position(File.H, Rank.FIVE), new Empty());

        // Empty spaces
        expectedPieces.put(new Position(File.A, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.B, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.C, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.D, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.E, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.F, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.G, Rank.FOUR), new Empty());
        expectedPieces.put(new Position(File.H, Rank.FOUR), new Empty());

        // Empty spaces
        expectedPieces.put(new Position(File.A, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.B, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.C, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.D, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.E, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.F, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.G, Rank.THREE), new Empty());
        expectedPieces.put(new Position(File.H, Rank.THREE), new Empty());

        // White pieces
        expectedPieces.put(new Position(File.A, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.B, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.C, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.D, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.E, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.F, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.G, Rank.TWO), new Pawn(Color.WHITE));
        expectedPieces.put(new Position(File.H, Rank.TWO), new Pawn(Color.WHITE));

        expectedPieces.put(new Position(File.A, Rank.ONE), new Rook(Color.WHITE));
        expectedPieces.put(new Position(File.B, Rank.ONE), new Night(Color.WHITE));
        expectedPieces.put(new Position(File.C, Rank.ONE), new Bishop(Color.WHITE));
        expectedPieces.put(new Position(File.D, Rank.ONE), new Queen(Color.WHITE));
        expectedPieces.put(new Position(File.E, Rank.ONE), new King(Color.WHITE));
        expectedPieces.put(new Position(File.F, Rank.ONE), new Bishop(Color.WHITE));
        expectedPieces.put(new Position(File.G, Rank.ONE), new Night(Color.WHITE));
        expectedPieces.put(new Position(File.H, Rank.ONE), new Rook(Color.WHITE));

        return expectedPieces;
    }
}
