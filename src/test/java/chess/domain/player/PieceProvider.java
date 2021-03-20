package chess.domain.player;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.Arrays;
import java.util.List;

public class PieceProvider {

    public static List<Piece> blackPieces() {
        return Arrays.asList(
                new BlackPawn(),
                new Rook(PieceColor.BLACK),
                new Bishop(PieceColor.BLACK),
                new Knight(PieceColor.BLACK),
                new Queen(PieceColor.BLACK),
                new King(PieceColor.BLACK)
        );
    }

    public static List<Piece> whitePieces() {
        return Arrays.asList(
                new WhitePawn(),
                new Rook(PieceColor.WHITE),
                new Bishop(PieceColor.WHITE),
                new Knight(PieceColor.WHITE),
                new Queen(PieceColor.WHITE),
                new King(PieceColor.WHITE)
        );
    }
}
