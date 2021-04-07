package chess.domain.piece;

import chess.domain.piece.black.*;
import chess.domain.piece.white.*;

import java.util.Arrays;
import java.util.List;

public class PieceFactory {

    public static List<ChessPiece> createPieces() {
        return Arrays.asList(
                BlackRook.createWithCoordinate(0, 0),
                BlackKnight.createWithCoordinate(0, 1),
                BlackBishop.createWithCoordinate(0, 2),
                BlackQueen.createWithCoordinate(0, 3),
                BlackKing.createWithCoordinate(0, 4),
                BlackBishop.createWithCoordinate(0, 5),
                BlackKnight.createWithCoordinate(0, 6),
                BlackRook.createWithCoordinate(0, 7),
                BlackPawn.createWithCoordinate(1, 0),
                BlackPawn.createWithCoordinate(1, 1),
                BlackPawn.createWithCoordinate(1, 2),
                BlackPawn.createWithCoordinate(1, 3),
                BlackPawn.createWithCoordinate(1, 4),
                BlackPawn.createWithCoordinate(1, 5),
                BlackPawn.createWithCoordinate(1, 6),
                BlackPawn.createWithCoordinate(1, 7),

                WhiteRook.createWithCoordinate(7, 0),
                WhiteKnight.createWithCoordinate(7, 1),
                WhiteBishop.createWithCoordinate(7, 2),
                WhiteQueen.createWithCoordinate(7, 3),
                WhiteKing.createWithCoordinate(7, 4),
                WhiteBishop.createWithCoordinate(7, 5),
                WhiteKnight.createWithCoordinate(7, 6),
                WhiteRook.createWithCoordinate(7, 7),
                WhitePawn.createWithCoordinate(6, 0),
                WhitePawn.createWithCoordinate(6, 1),
                WhitePawn.createWithCoordinate(6, 2),
                WhitePawn.createWithCoordinate(6, 3),
                WhitePawn.createWithCoordinate(6, 4),
                WhitePawn.createWithCoordinate(6, 5),
                WhitePawn.createWithCoordinate(6, 6),
                WhitePawn.createWithCoordinate(6, 7)
        );
    }

}
