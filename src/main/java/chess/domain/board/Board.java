package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Position;

import java.util.List;

public class Board {
    public static final int ROW = 8;
    public static final int COLUMN = 8;

    private final Pieces pieces;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Board(final List<Piece> pieces) {
        this(new Pieces(pieces));
    }

    public void moveAndCatchPiece(Color color, Position source, Position target) {
        Piece sourcePiece = pieces.findPieceByPosition(color, source);
        sourcePiece.move(target, this);
        pieces.catchPiece(color);
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }


    public double getWhiteScore() {
        return pieces.getWhiteScore();
    }

    public double getBlackScore() {
        return pieces.getBlackScore();
    }

    public boolean isKingCatch() {
        return pieces.isKingCatch();
    }
}
