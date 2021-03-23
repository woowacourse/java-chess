package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Position;

import java.util.List;
import java.util.function.Predicate;

public class Board {
    public static final int ROW = 8;
    public static final int COLUMN = 8;

    private final Pieces pieces;

    private Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Board(final List<Piece> pieces) {
        this(new Pieces(pieces));
    }

    public void movePiece(Color color, Position source, Position target) {
        Piece sourcePiece = pieces.findControllablePieceByPosition(color, source);
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

    public boolean isCaughtKing() {
        return pieces.isCaughtKing();
    }

    public boolean isNoneMatchByFilteredPieces(final Predicate<Piece> filteredCondition, final Predicate<Piece> noneMatchedCondition) {
        return pieces.isNoneMatchByFilteredPieces(filteredCondition, noneMatchedCondition);
    }

    public boolean isEnemyExist(final Position target, final Color color) {
        return pieces.isEnemyExist(target, color);
    }

}
