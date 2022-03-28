package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.Square;
import chess.model.board.BoardInitializer;
import chess.model.board.ChessInitializer;
import chess.model.piece.Bishop;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ChessInitializerTest {
    @Test
    void initBoard() {
        BoardInitializer boardInitializer = new ChessInitializer();
        List<Piece> pieces = boardInitializer.initPieces();
        assertThat(pieces).contains(new Pawn(Color.BLACK, Square.of("a7")), new Pawn(Color.BLACK, Square.of("b7")),
                new Pawn(Color.BLACK, Square.of("c7")), new Pawn(Color.BLACK, Square.of("d7")),
                new Pawn(Color.BLACK, Square.of("e7")), new Pawn(Color.BLACK, Square.of("f7")),
                new Pawn(Color.BLACK, Square.of("g7")), new Pawn(Color.BLACK, Square.of("h7")),
                new Rook(Color.BLACK, Square.of("a8")), new Knight(Color.BLACK, Square.of("b8")),
                new Bishop(Color.BLACK, Square.of("c8")), new Queen(Color.BLACK, Square.of("d8")),
                new King(Color.BLACK, Square.of("e8")), new Bishop(Color.BLACK, Square.of("f8")),
                new Knight(Color.BLACK, Square.of("g8")), new Rook(Color.BLACK, Square.of("h8")),
                new Pawn(Color.WHITE, Square.of("c2")), new Pawn(Color.WHITE, Square.of("d2")),
                new Pawn(Color.WHITE, Square.of("e2")), new Pawn(Color.WHITE, Square.of("f2")),
                new Pawn(Color.WHITE, Square.of("g2")), new Pawn(Color.WHITE, Square.of("h2")),
                new Rook(Color.WHITE, Square.of("a1")), new Knight(Color.WHITE, Square.of("b1")),
                new Bishop(Color.WHITE, Square.of("c1")), new Queen(Color.WHITE, Square.of("d1")),
                new King(Color.WHITE, Square.of("e1")), new Bishop(Color.WHITE, Square.of("f1")),
                new Knight(Color.WHITE, Square.of("g1")), new Rook(Color.WHITE, Square.of("h1"))
                );
    }
}
