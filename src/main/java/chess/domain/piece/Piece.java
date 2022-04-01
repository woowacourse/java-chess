package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import java.util.function.Consumer;

public abstract class Piece {

    private final Camp camp;
    private final PieceName pieceName;

    protected Piece(final Camp camp, final PieceName pieceName) {
        this.camp = camp;
        this.pieceName = pieceName;
    }

    public final boolean isBlack() {
        return camp == Camp.BLACK;
    }

    public abstract void move(Position beforePosition,
                              Position afterPosition,
                              Consumer<Piece> movePiece);

    public abstract void move(final Positions positions,
                              final Consumer<Piece> movePiece);

    protected abstract boolean canMove(Position beforePosition, Position afterPosition);

    public abstract void capture(Position beforePosition,
                                 Position afterPosition,
                                 Consumer<Piece> moveFunction);

    public abstract void capture(final Positions positions,
                                 final Consumer<Piece> moveFunction);

    public boolean isNotSameCampWith(final Piece targetPiece) {
        return camp != targetPiece.camp;
    }

    public boolean isNotSameCampWith(final Camp otherCamp) {
        return this.camp != otherCamp;
    }

    public PieceName pieceName() {
        return pieceName;
    }

    public final String getPieceNameCharacter() {
        return pieceName.getCharacter();
    }

    public abstract double getScore();

    public abstract boolean isNullPiece();
}
