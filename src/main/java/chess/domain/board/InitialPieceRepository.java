package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.player.Player;
import chess.domain.position.Position;

public enum InitialPieceRepository {

    BLACK_PAWN_1(Pawn.of(Position.of("A7"), Player.BLACK), Position.of("A7")),
    BLACK_PAWN_2(Pawn.of(Position.of("B7"), Player.BLACK), Position.of("B7")),
    BLACK_PAWN_3(Pawn.of(Position.of("C7"), Player.BLACK), Position.of("C7")),
    BLACK_PAWN_4(Pawn.of(Position.of("D7"), Player.BLACK), Position.of("D7")),
    BLACK_PAWN_5(Pawn.of(Position.of("E7"), Player.BLACK), Position.of("E7")),
    BLACK_PAWN_6(Pawn.of(Position.of("F7"), Player.BLACK), Position.of("F7")),
    BLACK_PAWN_7(Pawn.of(Position.of("G7"), Player.BLACK), Position.of("G7")),
    BLACK_PAWN_8(Pawn.of(Position.of("H7"), Player.BLACK), Position.of("H7")),
    BLACK_ROOK_1(Rook.of(Position.of("A8"), Player.BLACK), Position.of("A8")),
    BLACK_KNIGHT_1(Knight.of(Position.of("B8"), Player.BLACK), Position.of("B8")),
    BLACK_BISHOP_1(Bishop.of(Position.of("C8"), Player.BLACK), Position.of("C8")),
    BLACK_QUEEN(Queen.of(Position.of("D8"), Player.BLACK), Position.of("D8")),
    BLACK_KING(King.of(Position.of("E8"), Player.BLACK), Position.of("E8")),
    BLACK_BISHOP_2(Bishop.of(Position.of("F8"), Player.BLACK), Position.of("F8")),
    BLACK_KNIGHT_2(Knight.of(Position.of("G8"), Player.BLACK), Position.of("G8")),
    BLACK_ROOK_2(Rook.of(Position.of("H8"), Player.BLACK), Position.of("H8")),
    WHITE_PAWN_1(Pawn.of(Position.of("A7"), Player.WHITE), Position.of("A7")),
    WHITE_PAWN_2(Pawn.of(Position.of("B7"), Player.WHITE), Position.of("B7")),
    WHITE_PAWN_3(Pawn.of(Position.of("C7"), Player.WHITE), Position.of("C7")),
    WHITE_PAWN_4(Pawn.of(Position.of("D7"), Player.WHITE), Position.of("D7")),
    WHITE_PAWN_5(Pawn.of(Position.of("E7"), Player.WHITE), Position.of("E7")),
    WHITE_PAWN_6(Pawn.of(Position.of("F7"), Player.WHITE), Position.of("F7")),
    WHITE_PAWN_7(Pawn.of(Position.of("G7"), Player.WHITE), Position.of("G7")),
    WHITE_PAWN_8(Pawn.of(Position.of("H7"), Player.WHITE), Position.of("H7")),
    WHITE_ROOK_1(Rook.of(Position.of("A8"), Player.WHITE), Position.of("A8")),
    WHITE_KNIGHT_1(Knight.of(Position.of("B8"), Player.WHITE), Position.of("B8")),
    WHITE_BISHOP_1(Bishop.of(Position.of("C8"), Player.WHITE), Position.of("C8")),
    WHITE_QUEEN(Queen.of(Position.of("D8"), Player.WHITE), Position.of("D8")),
    WHITE_KING(King.of(Position.of("E8"), Player.WHITE), Position.of("E8")),
    WHITE_BISHOP_2(Bishop.of(Position.of("F8"), Player.WHITE), Position.of("F8")),
    WHITE_KNIGHT_2(Knight.of(Position.of("G8"), Player.WHITE), Position.of("G8")),
    WHITE_ROOK_2(Rook.of(Position.of("H8"), Player.WHITE), Position.of("H8"));

    private Piece piece;
    private Position position;

    InitialPieceRepository(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public Piece getPiece() {
        return piece;
    }

    public Position getPosition() {
        return position;
    }
}
