package chess.domain.chessboard;

import static java.util.Map.entry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Square;
import chess.domain.attribute.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

class ChessboardTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void create() {
        Chessboard chessBoard = Chessboard.create();
        assertThat(chessBoard.getChessboard())
                .contains(
                        entry(Square.of(File.E, Rank.ONE), new King(Color.WHITE)),
                        entry(Square.of(File.E, Rank.EIGHT), new King(Color.BLACK)),
                        entry(Square.of(File.D, Rank.ONE), new Queen(Color.WHITE)),
                        entry(Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK)),
                        entry(Square.of(File.C, Rank.ONE), new Bishop(Color.WHITE)),
                        entry(Square.of(File.F, Rank.ONE), new Bishop(Color.WHITE)),
                        entry(Square.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK)),
                        entry(Square.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK)),
                        entry(Square.of(File.B, Rank.ONE), new Knight(Color.WHITE)),
                        entry(Square.of(File.G, Rank.ONE), new Knight(Color.WHITE)),
                        entry(Square.of(File.B, Rank.EIGHT), new Knight(Color.BLACK)),
                        entry(Square.of(File.G, Rank.EIGHT), new Knight(Color.BLACK)),
                        entry(Square.of(File.A, Rank.ONE), new Rook(Color.WHITE)),
                        entry(Square.of(File.H, Rank.ONE), new Rook(Color.WHITE)),
                        entry(Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK)),
                        entry(Square.of(File.H, Rank.EIGHT), new Rook(Color.BLACK)),
                        entry(Square.of(File.A, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.B, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.C, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.D, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.E, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.F, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.G, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.H, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Square.of(File.A, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.B, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.C, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.D, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.E, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.F, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.G, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Square.of(File.H, Rank.SEVEN), new StartingPawn(Color.BLACK))
                );
    }
}
