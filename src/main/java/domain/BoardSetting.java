package domain;

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

    WHITE_ROOK_1_1(Location.of(1, 1), Rook.makeWhite()),
    WHITE_KNIGHT_1_2(Location.of(2, 1), Knight.makeWhite()),
    WHITE_BISHOP_1_3(Location.of(3, 1), Bishop.makeWhite()),
    WHITE_QUEEN_1_4(Location.of(4, 1), Queen.makeWhite()),
    WHITE_KING_1_5(Location.of(5, 1), King.makeWhite()),
    WHITE_BISHOP_1_6(Location.of(6, 1), Bishop.makeWhite()),
    WHITE_KNIGHT_1_7(Location.of(7, 1), Knight.makeWhite()),
    WHITE_ROOK_1_8(Location.of(8, 1), Rook.makeWhite()),
    WHITE_PAWN_2_1(Location.of(1, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_2(Location.of(2, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_3(Location.of(3, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_4(Location.of(4, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_5(Location.of(5, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_6(Location.of(6, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_7(Location.of(7, 2), Pawn.makeWhite()),
    WHITE_PAWN_2_8(Location.of(8, 2), Pawn.makeWhite()),
    BLACK_ROOK_8_1(Location.of(1, 8), Rook.makeBlack()),
    BLACK_KNIGHT_8_2(Location.of(2, 8), Knight.makeBlack()),
    BLACK_BISHOP_8_3(Location.of(3, 8), Bishop.makeBlack()),
    BLACK_QUEEN_8_4(Location.of(4, 8), Queen.makeBlack()),
    BLACK_KING_8_5(Location.of(5, 8), King.makeBlack()),
    BLACK_BISHOP_8_6(Location.of(6, 8), Bishop.makeBlack()),
    BLACK_KNIGHT_8_7(Location.of(7, 8), Knight.makeBlack()),
    BLACK_ROOK_8_8(Location.of(8, 8), Rook.makeBlack()),
    BLACK_PAWN_7_1(Location.of(1, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_2(Location.of(2, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_3(Location.of(3, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_4(Location.of(4, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_5(Location.of(5, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_6(Location.of(6, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_7(Location.of(7, 7), Pawn.makeBlack()),
    BLACK_PAWN_7_8(Location.of(8, 7), Pawn.makeBlack());

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
