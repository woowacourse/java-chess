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

    KING_WHITE(new King(Color.WHITE)),
    QUEEN_WHITE(new Queen(Color.WHITE)),
    ROOK_WHITE(new Rook(Color.WHITE)),
    KNIGHT_WHITE(new Knight(Color.WHITE)),
    BISHOP_WHITE(new Bishop(Color.WHITE)),
    PAWN_WHITE(new Pawn(Color.WHITE)),
    KING_BLACK(new King(Color.BLACK)),
    QUEEN_BLACK(new Queen(Color.BLACK)),
    ROOK_BLACK(new Rook(Color.BLACK)),
    KNIGHT_BLACK(new Knight(Color.BLACK)),
    BISHOP_BLACK(new Bishop(Color.BLACK)),
    PAWN_BLACK(new Pawn(Color.BLACK));

    private final Piece piece;

    PieceType(Piece piece) {
        this.piece = piece;
    }

    public static Piece parsePiece(PieceType pieceType) {
        return Arrays.stream(values())
                .filter(value -> value == pieceType)
                .map(PieceType::getPiece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 유형입니다."));
    }

    public Piece getPiece() {
        return piece;
    }
}
