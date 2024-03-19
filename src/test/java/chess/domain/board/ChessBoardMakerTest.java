package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.Piece;
import chess.domain.square.piece.Type;
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
        squares.put(new Position(Rank.FIRST, File.A), new Piece(Type.ROOK, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.B), new Piece(Type.KNIGHT, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.C), new Piece(Type.BISHOP, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.D), new Piece(Type.QUEEN, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.E), new Piece(Type.KING, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.F), new Piece(Type.BISHOP, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.G), new Piece(Type.KNIGHT, Color.BLACK));
        squares.put(new Position(Rank.FIRST, File.H), new Piece(Type.ROOK, Color.BLACK));

        for (File file : File.values()) {
            squares.put(new Position(Rank.SECOND, file), new Piece(Type.PAWN, Color.BLACK));
            squares.put(new Position(Rank.SEVENTH, file), new Piece(Type.PAWN, Color.WHITE));
        }

        squares.put(new Position(Rank.EIGHTH, File.A), new Piece(Type.ROOK, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.B), new Piece(Type.KNIGHT, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.C), new Piece(Type.BISHOP, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.D), new Piece(Type.QUEEN, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.E), new Piece(Type.KING, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.F), new Piece(Type.BISHOP, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.G), new Piece(Type.KNIGHT, Color.WHITE));
        squares.put(new Position(Rank.EIGHTH, File.H), new Piece(Type.ROOK, Color.WHITE));

        for (File file : File.values()) {
            squares.put(new Position(Rank.THIRD, file), Empty.getInstance());
            squares.put(new Position(Rank.FOURTH, file), Empty.getInstance());
            squares.put(new Position(Rank.FIFTH, file), Empty.getInstance());
            squares.put(new Position(Rank.SIXTH, file), Empty.getInstance());
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
