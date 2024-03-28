package chess.util;

import chess.model.piece.*;

import java.util.List;

import static chess.model.piece.Side.BLACK;
import static chess.model.piece.Side.WHITE;

public class ChessBoardFixture {
    public static final List<Piece> KNIGHT_MOVEMENT_BOARD = List.of(
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Pawn.from(WHITE), Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Knight.from(WHITE), Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE
    );

    public static final List<Piece> WHITE_FOOLS_MATE_LOSE = List.of(
            Rook.from(BLACK), Knight.from(BLACK), Bishop.from(BLACK), Blank.INSTANCE, King.from(BLACK), Bishop.from(BLACK), Knight.from(BLACK), Rook.from(BLACK),
            Pawn.from(BLACK), Pawn.from(BLACK), Pawn.from(BLACK), Pawn.from(BLACK), Pawn.from(BLACK), Pawn.from(BLACK), Pawn.from(BLACK), Pawn.from(BLACK),
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Pawn.from(BLACK), Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Pawn.from(WHITE), Blank.INSTANCE,
            Pawn.from(WHITE), Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Blank.INSTANCE, Pawn.from(WHITE), Blank.INSTANCE, Blank.INSTANCE,
            Blank.INSTANCE, Pawn.from(WHITE), Pawn.from(WHITE), Pawn.from(WHITE), Pawn.from(WHITE), Blank.INSTANCE, Blank.INSTANCE, Pawn.from(WHITE),
            Rook.from(WHITE), Knight.from(WHITE), Bishop.from(WHITE), Queen.from(WHITE), Queen.from(BLACK), Bishop.from(WHITE), Knight.from(WHITE), Rook.from(WHITE)
    );
}
