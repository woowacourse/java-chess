package chess.domain.player;

import static chess.domain.piece.PieceColor.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.List;

public class PieceProvider {

    public static List<Piece> blackPieces() {
        return Arrays.asList(
            new Pawn(PieceColor.BLACK),
            new Rook(PieceColor.BLACK),
            new Bishop(PieceColor.BLACK),
            new Knight(PieceColor.BLACK),
            new Queen(PieceColor.BLACK),
            new King(PieceColor.BLACK)
        );
    }

    public static List<Piece> whitePieces() {
        return Arrays.asList(
            new Pawn(WHITE),
            new Rook(WHITE),
            new Bishop(WHITE),
            new Knight(WHITE),
            new Queen(WHITE),
            new King(WHITE)
        );
    }
}
