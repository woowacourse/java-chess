package chess.domain.piece;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;

public final class Piece {

    private final Color color;
    private final PieceRule pieceRule;

    public Piece(Color color, PieceRule pieceRule) {
        this.color = color;
        this.pieceRule = pieceRule;
    }

    public static Piece createWhitePiece(PieceRule pieceRule) {
        return new Piece(WHITE, pieceRule);
    }

    public static Piece createBlackPiece(PieceRule pieceRule) {
        return new Piece(BLACK, pieceRule);
    }

    public Piece move(Position source, Position target, ChessBoard chessBoard) {
        return new Piece(color, pieceRule.move(source, target, chessBoard));
    }

    public String convertedName() {
        return pieceRule.convertedName(color);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameTeamPiece(Piece piece) {
        return isSameColor(piece.color);
    }

    public double score() {
        return pieceRule.score();
    }

    public boolean isPawn() {
        return pieceRule.isPawn();
    }

    public boolean isKing() {
        return pieceRule.isKing();
    }

    public Color color() {
        return color;
    }

    public String name() {
        return pieceRule.name();
    }
}
