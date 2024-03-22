package chess.domain.chessBoard.generator;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;

import java.util.ArrayList;
import java.util.List;

public class PieceGenerator {

    public static List<Piece> makeSpecialPieces(Color color) {
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

    public static List<Piece> makeWhitePawnPieces(int amount) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            pieces.add(new WhitePawn());
        }
        return pieces;
    }

    public static List<Piece> makeBlackPawnPieces(int amount) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            pieces.add(new BlackPawn());
        }
        return pieces;
    }
}
