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

public class ChessBoardMakerTest {

    @DisplayName("체스판을 만든다.")
    @Test
    void createChessBoard() {
        // given
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();

        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(new Position(Rank.EIGHTH, File.A), Rook.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.B), Knight.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.C), Bishop.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.D), Queen.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.E), King.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.F), Bishop.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.G), Knight.from(Color.BLACK));
        pieces.put(new Position(Rank.EIGHTH, File.H), Rook.from(Color.BLACK));

        for (File file : File.values()) {
            pieces.put(new Position(Rank.SEVENTH, file), Pawn.createOnStart(Color.BLACK));
            pieces.put(new Position(Rank.SECOND, file), Pawn.createOnStart(Color.WHITE));
        }

        pieces.put(new Position(Rank.FIRST, File.A), Rook.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.B), Knight.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.C), Bishop.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.D), Queen.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.E), King.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.F), Bishop.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.G), Knight.from(Color.WHITE));
        pieces.put(new Position(Rank.FIRST, File.H), Rook.from(Color.WHITE));

        for (File file : File.values()) {
            pieces.put(new Position(Rank.THIRD, file), Empty.getInstance());
            pieces.put(new Position(Rank.FOURTH, file), Empty.getInstance());
            pieces.put(new Position(Rank.FIFTH, file), Empty.getInstance());
            pieces.put(new Position(Rank.SIXTH, file), Empty.getInstance());
        }
        ChessBoard expected = new ChessBoard(pieces);

        // when
        ChessBoard board = chessBoardMaker.make();

        // then
        assertThat(board)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
