package chess.domain.piece;

import chess.domain.player.PlayerColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ChessPiece {

    WHITE_KING(new King(PlayerColor.WHITE)),
    BLACK_KING(new King(PlayerColor.BLACK)),
    WHITE_QUEEN(new Queen(PlayerColor.WHITE)),
    BLACK_QUEEN(new Queen(PlayerColor.BLACK)),
    WHITE_ROOK(new Rook(PlayerColor.WHITE)),
    BLACK_ROOK(new Rook(PlayerColor.BLACK)),
    WHITE_BISHOP(new Bishop(PlayerColor.WHITE)),
    BLACK_BISHOP(new Bishop(PlayerColor.BLACK)),
    WHITE_KNIGHT(new Knight(PlayerColor.WHITE)),
    BLACK_KNIGHT(new Knight(PlayerColor.BLACK)),
    WHITE_PAWN(new Pawn(PlayerColor.WHITE)),
    BLACK_PAWN(new Pawn(PlayerColor.BLACK));

    private final GamePiece gamePiece;

    ChessPiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;
    }

    public static List<GamePiece> list() {
        return Arrays.stream(values())
                .map(chessPiece -> chessPiece.gamePiece)
                .collect(Collectors.toList());
    }

    public static boolean isKing(GamePiece gamePiece) {
        return WHITE_KING.gamePiece.equals(gamePiece) || BLACK_KING.gamePiece.equals(gamePiece);
    }

    public static boolean isPawn(GamePiece gamePiece) {
        return WHITE_PAWN.gamePiece.equals(gamePiece) || BLACK_PAWN.gamePiece.equals(gamePiece);
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }
}
