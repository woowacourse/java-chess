package chess.domain;

import java.util.function.BiFunction;

import chess.domain.piece.*;

public enum ChessPieceInfo {
    BLACK_PAWN(Player.BLACK, Type.PAWN, BlackPawn::valueOf, "♟"),
    WHITE_PAWN(Player.WHITE, Type.PAWN, WhitePawn::valueOf, "♙"),
    BLACK_ROOK(Player.BLACK, Type.ROOK, Rook::valueOf, "♜"),
    WHITE_ROOK(Player.WHITE, Type.ROOK, Rook::valueOf, "♖"),
    BLACK_KNIGHT(Player.BLACK, Type.KNIGHT, Knight::valueOf, "♞"),
    WHITE_KNIGHT(Player.WHITE, Type.KNIGHT, Knight::valueOf, "♘"),
    BLACK_BISHOP(Player.BLACK, Type.BISHOP, Bishop::valueOf, "♝"),
    WHITE_BISHOP(Player.WHITE, Type.BISHOP, Bishop::valueOf, "♗"),
    BLACK_QUEEN(Player.BLACK, Type.QUEEN, Queen::valueOf, "♛"),
    WHITE_QUEEN(Player.WHITE, Type.QUEEN, Queen::valueOf, "♕"),
    BLACK_KING(Player.BLACK, Type.KING, King::valueOf, "♚"),
    WHITE_KING(Player.WHITE, Type.KING, King::valueOf, "♔"),
    EMPTY(Player.EMPTY, Type.EMPTY, EmptyPiece::valueOf, "");

    private Player player;
    private Type type;
    private BiFunction<Player, Position, Piece> generator;
    private String image;

    ChessPieceInfo(Player player, Type type, BiFunction<Player, Position, Piece> generator, String image) {
        this.player = player;
        this.type = type;
        this.generator = generator;
        this.image = image;
    }

    public static Piece generatePiece(Player player, Type type, Position position) {
        for (ChessPieceInfo chessPieceInfo : ChessPieceInfo.values()) {
            if (chessPieceInfo.player.equals(player) && (chessPieceInfo.type.equals(type))) {
                return chessPieceInfo.generator.apply(player, position);
            }
        }
        throw new IllegalArgumentException("체스 말을 생성할 수 없습니다.");
    }

    public static String getPieceImage(Player player, Type type) {
        for (ChessPieceInfo chessPieceInfo : ChessPieceInfo.values()) {
            if (chessPieceInfo.player.equals(player) && (chessPieceInfo.type.equals(type))) {
                return chessPieceInfo.image;
            }
        }
        throw new IllegalArgumentException("해당 이미지를 찾을 수 없습니다.");
    }

    public static ChessPieceInfo getChessPiece(Player player, Type type) {
        for (ChessPieceInfo chess : ChessPieceInfo.values()) {
            if (chess.player.equals(player) && (chess.type.equals(type))) {
                return chess;
            }
        }
        throw new IllegalArgumentException("해당 체스 말을 찾을 수 없습니다.");
    }

    public Piece generate(Position position) {
        return generator.apply(player, position);
    }

    public String getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }
}
