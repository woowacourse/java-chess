package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Piece;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardMakerTest {

    @DisplayName("체스판을 만든다.")
    @Test
    void createChessBoard() {
        // given
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();

        Map<Position, Square> squares = new LinkedHashMap<>();
        squares.put(new Position(Rank.FIRST, File.A), Piece.ROOK);
        squares.put(new Position(Rank.FIRST, File.B), Piece.KNIGHT);
        squares.put(new Position(Rank.FIRST, File.C), Piece.BISHOP);
        squares.put(new Position(Rank.FIRST, File.D), Piece.QUEEN);
        squares.put(new Position(Rank.FIRST, File.E), Piece.KING);
        squares.put(new Position(Rank.FIRST, File.F), Piece.BISHOP);
        squares.put(new Position(Rank.FIRST, File.G), Piece.KNIGHT);
        squares.put(new Position(Rank.FIRST, File.H), Piece.ROOK);

        for (File file : File.values()) {
            squares.put(new Position(Rank.SECOND, file), Piece.PAWN);
            squares.put(new Position(Rank.SEVENTH, file), Piece.PAWN);
        }

        squares.put(new Position(Rank.EIGHTH, File.A), Piece.ROOK);
        squares.put(new Position(Rank.EIGHTH, File.B), Piece.KNIGHT);
        squares.put(new Position(Rank.EIGHTH, File.C), Piece.BISHOP);
        squares.put(new Position(Rank.EIGHTH, File.D), Piece.QUEEN);
        squares.put(new Position(Rank.EIGHTH, File.E), Piece.KING);
        squares.put(new Position(Rank.EIGHTH, File.F), Piece.BISHOP);
        squares.put(new Position(Rank.EIGHTH, File.G), Piece.KNIGHT);
        squares.put(new Position(Rank.EIGHTH, File.H), Piece.ROOK);

        for (File file : File.values()) {
            squares.put(new Position(Rank.THIRD, file), new Empty());
            squares.put(new Position(Rank.FOURTH, file), new Empty());
            squares.put(new Position(Rank.FIFTH, file), new Empty());
            squares.put(new Position(Rank.SIXTH, file), new Empty());
        }
        ChessBoard expected = new ChessBoard(squares);

        // when
        ChessBoard board = chessBoardMaker.make();

        // then
        assertThat(board)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
