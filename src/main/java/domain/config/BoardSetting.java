package domain.config;

import domain.Location;
import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Arrays;

public enum BoardSetting {

    WHITE_ROOK_A1(Location.of(1, 1), Rook.makeWhite()),
    WHITE_KNIGHT_A2(Location.of(2, 1), Knight.makeWhite()),
    WHITE_BISHOP_A3(Location.of(3, 1), Bishop.makeWhite()),
    WHITE_QUEEN_A4(Location.of(4, 1), Queen.makeWhite()),
    WHITE_KING_A5(Location.of(5, 1), King.makeWhite()),
    WHITE_BISHOP_A6(Location.of(6, 1), Bishop.makeWhite()),
    WHITE_KNIGHT_A7(Location.of(7, 1), Knight.makeWhite()),
    WHITE_ROOK_A8(Location.of(8, 1), Rook.makeWhite()),
    WHITE_PAWN_B1(Location.of(1, 2), Pawn.makeWhite()),
    WHITE_PAWN_B2(Location.of(2, 2), Pawn.makeWhite()),
    WHITE_PAWN_B3(Location.of(3, 2), Pawn.makeWhite()),
    WHITE_PAWN_B4(Location.of(4, 2), Pawn.makeWhite()),
    WHITE_PAWN_B5(Location.of(5, 2), Pawn.makeWhite()),
    WHITE_PAWN_B6(Location.of(6, 2), Pawn.makeWhite()),
    WHITE_PAWN_B7(Location.of(7, 2), Pawn.makeWhite()),
    WHITE_PAWN_B8(Location.of(8, 2), Pawn.makeWhite()),
    BLACK_ROOK_H1(Location.of(1, 8), Rook.makeBlack()),
    BLACK_KNIGHT_H2(Location.of(2, 8), Knight.makeBlack()),
    BLACK_BISHOP_H3(Location.of(3, 8), Bishop.makeBlack()),
    BLACK_QUEEN_H4(Location.of(4, 8), Queen.makeBlack()),
    BLACK_KING_H5(Location.of(5, 8), King.makeBlack()),
    BLACK_BISHOP_H6(Location.of(6, 8), Bishop.makeBlack()),
    BLACK_KNIGHT_H7(Location.of(7, 8), Knight.makeBlack()),
    BLACK_ROOK_H8(Location.of(8, 8), Rook.makeBlack()),
    BLACK_PAWN_G1(Location.of(1, 7), Pawn.makeBlack()),
    BLACK_PAWN_G2(Location.of(2, 7), Pawn.makeBlack()),
    BLACK_PAWN_G3(Location.of(3, 7), Pawn.makeBlack()),
    BLACK_PAWN_G4(Location.of(4, 7), Pawn.makeBlack()),
    BLACK_PAWN_G5(Location.of(5, 7), Pawn.makeBlack()),
    BLACK_PAWN_G6(Location.of(6, 7), Pawn.makeBlack()),
    BLACK_PAWN_G7(Location.of(7, 7), Pawn.makeBlack()),
    BLACK_PAWN_G8(Location.of(8, 7), Pawn.makeBlack());

    private final Location location;
    private final Piece piece;

    BoardSetting(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static Piece findPiece(final Location location) {
        return Arrays.stream(BoardSetting.values())
            .filter(setting -> setting.location.equals(location))
            .findFirst()
            .map(setting -> setting.piece)
            .orElse(EmptyPiece.make());
    }
}
