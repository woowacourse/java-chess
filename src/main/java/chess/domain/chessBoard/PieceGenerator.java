package chess.domain.chessBoard;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.List;

public class PieceGenerator {

    public static final int PAWN_COUNT_PER_PLAYER = 8;

    public List<Piece> makeSpecialPieces(Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }

    public List<Piece> makePawnPieces(Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < PAWN_COUNT_PER_PLAYER; i++) {
            pieces.add(new Pawn(color));
        }
        return pieces;
    }
}
