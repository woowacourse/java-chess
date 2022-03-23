package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void construct() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(new Position("a1"), new Pawn(Color.WHITE)),
                Map.entry(new Position("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(pieces);
        assertThat(chessBoard.getPieces()).containsAllEntriesOf(pieces);
    }

    @Test
    @DisplayName("체스판을 생성할 때 빈 칸은 EmptyPiece를 삽입한다.")
    void constructEmptyPieces() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(new Position("a1"), new Pawn(Color.WHITE)),
                Map.entry(new Position("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(pieces);
        assertThat(chessBoard.getPieces()).contains(Map.entry(new Position("a3"), EmptyPiece.getInstance()));
    }
}
