package chess.web.dto;

import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;

public enum PieceType {

    KING_WHITE("king white", new King(Color.WHITE)),
    QUEEN_WHITE("queen white", new Queen(Color.WHITE)),
    ROOK_WHITE("rook white", new Rook(Color.WHITE)),
    KNIGHT_WHITE("knight white", new Knight(Color.WHITE)),
    BISHOP_WHITE("bishop white", new Bishop(Color.WHITE)),
    PAWN_WHITE("pawn white", new Pawn(Color.WHITE)),
    KING_BLACK("king black", new King(Color.BLACK)),
    QUEEN_BLACK("queen black", new Queen(Color.BLACK)),
    ROOK_BLACK("rook black", new Rook(Color.BLACK)),
    KNIGHT_BLACK("knight black", new Knight(Color.BLACK)),
    BISHOP_BLACK("bishop black", new Bishop(Color.BLACK)),
    PAWN_BLACK("pawn black", new Pawn(Color.BLACK));

    private final String type;
    private final Piece piece;

    PieceType(String value, Piece piece) {
        this.type = value;
        this.piece = piece;
    }

    public static PieceType of(String pieceType) {
        return Arrays.stream(values())
                .filter(value -> value.type.equals(pieceType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 유형입니다."));
    }

    public Piece getPiece() {
        return piece;
    }

    public String getType() {
        return type;
    }
}
