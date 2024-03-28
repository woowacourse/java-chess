package chess.util;

import chess.model.piece.*;

import java.util.List;

public class ChessBoardFixture {
    public static final List<Piece> KNIGHT_MOVEMENT_BOARD = List.of(
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Pawn.from(Side.WHITE), Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Knight.from(Side.WHITE), Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE
    );
}
