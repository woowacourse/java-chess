package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.NotMovedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.function.BiFunction;

public enum InitialPieceRepository {

    BLACK_PAWN_1(NotMovedPawn::of, Player.BLACK, Position.of("A7")),
    BLACK_PAWN_2(NotMovedPawn::of, Player.BLACK, Position.of("B7")),
    BLACK_PAWN_3(NotMovedPawn::of, Player.BLACK, Position.of("C7")),
    BLACK_PAWN_4(NotMovedPawn::of, Player.BLACK, Position.of("D7")),
    BLACK_PAWN_5(NotMovedPawn::of, Player.BLACK, Position.of("E7")),
    BLACK_PAWN_6(NotMovedPawn::of, Player.BLACK, Position.of("F7")),
    BLACK_PAWN_7(NotMovedPawn::of, Player.BLACK, Position.of("G7")),
    BLACK_PAWN_8(NotMovedPawn::of, Player.BLACK, Position.of("H7")),
    BLACK_ROOK_1(Rook::of, Player.BLACK, Position.of("A8")),
    BLACK_KNIGHT_1(Knight::of, Player.BLACK, Position.of("B8")),
    BLACK_BISHOP_1(Bishop::of, Player.BLACK, Position.of("C8")),
    BLACK_QUEEN(Queen::of, Player.BLACK, Position.of("D8")),
    BLACK_KING(King::of, Player.BLACK, Position.of("E8")),
    BLACK_BISHOP_2(Bishop::of, Player.BLACK, Position.of("F8")),
    BLACK_KNIGHT_2(Knight::of, Player.BLACK, Position.of("G8")),
    BLACK_ROOK_2(Rook::of, Player.BLACK, Position.of("H8")),
    WHITE_PAWN_1(NotMovedPawn::of, Player.WHITE, Position.of("A2")),
    WHITE_PAWN_2(NotMovedPawn::of, Player.WHITE, Position.of("B2")),
    WHITE_PAWN_3(NotMovedPawn::of, Player.WHITE, Position.of("C2")),
    WHITE_PAWN_4(NotMovedPawn::of, Player.WHITE, Position.of("D2")),
    WHITE_PAWN_5(NotMovedPawn::of, Player.WHITE, Position.of("E2")),
    WHITE_PAWN_6(NotMovedPawn::of, Player.WHITE, Position.of("F2")),
    WHITE_PAWN_7(NotMovedPawn::of, Player.WHITE, Position.of("G2")),
    WHITE_PAWN_8(NotMovedPawn::of, Player.WHITE, Position.of("H2")),
    WHITE_ROOK_1(Rook::of, Player.WHITE, Position.of("A1")),
    WHITE_KNIGHT_1(Knight::of, Player.WHITE, Position.of("B1")),
    WHITE_BISHOP_1(Bishop::of, Player.WHITE, Position.of("C1")),
    WHITE_QUEEN(Queen::of, Player.WHITE, Position.of("D1")),
    WHITE_KING(King::of, Player.WHITE, Position.of("E1")),
    WHITE_BISHOP_2(Bishop::of, Player.WHITE, Position.of("F1")),
    WHITE_KNIGHT_2(Knight::of, Player.WHITE, Position.of("G1")),
    WHITE_ROOK_2(Rook::of, Player.WHITE, Position.of("H1"));

    private BiFunction<Position, Player, Piece> generator;
    private Player player;
    private Position position;

    InitialPieceRepository(BiFunction<Position, Player, Piece> generator, Player player, Position position) {
        this.generator = generator;
        this.player = player;
        this.position = position;
    }

    public Piece getInitialPiece() {
        return generator.apply(position, player);
    }

    public Position getPosition() {
        return position;
    }
}
