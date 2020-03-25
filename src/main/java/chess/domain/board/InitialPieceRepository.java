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
    WHITE_PAWN_1(Pawn.of(Position.of("A2"), Player.WHITE), Position.of("A2")),
    WHITE_PAWN_2(Pawn.of(Position.of("B2"), Player.WHITE), Position.of("B2")),
    WHITE_PAWN_3(Pawn.of(Position.of("C2"), Player.WHITE), Position.of("C2")),
    WHITE_PAWN_4(Pawn.of(Position.of("D2"), Player.WHITE), Position.of("D2")),
    WHITE_PAWN_5(Pawn.of(Position.of("E2"), Player.WHITE), Position.of("E2")),
    WHITE_PAWN_6(Pawn.of(Position.of("F2"), Player.WHITE), Position.of("F2")),
    WHITE_PAWN_7(Pawn.of(Position.of("G2"), Player.WHITE), Position.of("G2")),
    WHITE_PAWN_8(Pawn.of(Position.of("H2"), Player.WHITE), Position.of("H2")),
    WHITE_ROOK_1(Rook.of(Position.of("A1"), Player.WHITE), Position.of("A1")),
    WHITE_KNIGHT_1(Knight.of(Position.of("B1"), Player.WHITE), Position.of("B1")),
    WHITE_BISHOP_1(Bishop.of(Position.of("C1"), Player.WHITE), Position.of("C1")),
    WHITE_QUEEN(Queen.of(Position.of("D1"), Player.WHITE), Position.of("D1")),
    WHITE_KING(King.of(Position.of("E1"), Player.WHITE), Position.of("E1")),
    WHITE_BISHOP_2(Bishop.of(Position.of("F1"), Player.WHITE), Position.of("F1")),
    WHITE_KNIGHT_2(Knight.of(Position.of("G1"), Player.WHITE), Position.of("G1")),
    WHITE_ROOK_2(Rook.of(Position.of("H1"), Player.WHITE), Position.of("H1"));

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
