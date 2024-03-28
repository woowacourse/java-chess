package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PiecePositionsTest {

    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
     */
    @DisplayName("체스판을 만든다.")
    @Test
    void createChessBoard() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(new Position(File.A, Rank.EIGHTH), Rook.from(Color.BLACK));
        pieces.put(new Position(File.B, Rank.EIGHTH), Knight.from(Color.BLACK));
        pieces.put(new Position(File.C, Rank.EIGHTH), Bishop.from(Color.BLACK));
        pieces.put(new Position(File.D, Rank.EIGHTH), Queen.from(Color.BLACK));
        pieces.put(new Position(File.E, Rank.EIGHTH), King.from(Color.BLACK));
        pieces.put(new Position(File.F, Rank.EIGHTH), Bishop.from(Color.BLACK));
        pieces.put(new Position(File.G, Rank.EIGHTH), Knight.from(Color.BLACK));
        pieces.put(new Position(File.H, Rank.EIGHTH), Rook.from(Color.BLACK));

        for (File file : File.values()) {
            pieces.put(new Position(file, Rank.SEVENTH), Pawn.from(Color.BLACK));
            pieces.put(new Position(file, Rank.SECOND), Pawn.from(Color.WHITE));
        }

        pieces.put(new Position(File.A, Rank.FIRST), Rook.from(Color.WHITE));
        pieces.put(new Position(File.B, Rank.FIRST), Knight.from(Color.WHITE));
        pieces.put(new Position(File.C, Rank.FIRST), Bishop.from(Color.WHITE));
        pieces.put(new Position(File.D, Rank.FIRST), Queen.from(Color.WHITE));
        pieces.put(new Position(File.E, Rank.FIRST), King.from(Color.WHITE));
        pieces.put(new Position(File.F, Rank.FIRST), Bishop.from(Color.WHITE));
        pieces.put(new Position(File.G, Rank.FIRST), Knight.from(Color.WHITE));
        pieces.put(new Position(File.H, Rank.FIRST), Rook.from(Color.WHITE));

        for (File file : File.values()) {
            pieces.put(new Position(file, Rank.THIRD), Empty.getInstance());
            pieces.put(new Position(file, Rank.FOURTH), Empty.getInstance());
            pieces.put(new Position(file, Rank.FIFTH), Empty.getInstance());
            pieces.put(new Position(file, Rank.SIXTH), Empty.getInstance());
        }
        ChessBoard expected = new ChessBoard(pieces);

        // when
        ChessBoard board = PiecePositions.createBoard();

        // then
        assertThat(board)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
