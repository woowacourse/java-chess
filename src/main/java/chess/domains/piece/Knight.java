package chess.domains.piece;

import chess.domains.position.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor, PieceType.KNIGHT.name, PieceType.KNIGHT.score);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        return (Math.abs(current.xGapBetween(target)) == ONE_BLOCK && Math.abs(current.yGapBetween(target)) == TWO_BLOCKS)
                || (Math.abs(current.xGapBetween(target)) == TWO_BLOCKS && Math.abs(current.yGapBetween(target)) == ONE_BLOCK);
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return null;
    }
}
