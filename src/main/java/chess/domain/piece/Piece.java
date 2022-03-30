package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public abstract class Piece {

    private final Camp camp;
    private final PieceName pieceName;

    protected Piece(final Camp camp, final PieceName pieceName) {
        this.camp = camp;
        this.pieceName = pieceName;
    }

    public final boolean isBlack() {
        return this.camp == Camp.BLACK;
    }

    public abstract void move(Position beforePosition,
                              Position afterPosition,
                              Consumer<Piece> moveFunction);

    public abstract void capture(Position beforePosition,
                                 Position afterPosition,
                                 Consumer<Piece> moveFunction);

    public boolean isSameCampWith(Piece targetPiece) {
        return this.camp == targetPiece.camp;
    }

    protected abstract boolean canMove(Position beforePosition, Position afterPosition);

    public PieceName pieceName() {
        return this.pieceName;
    }

    public final String getPieceNameCharacter() {
        return this.pieceName.getValue();
    }

    public abstract double getScore();

    public abstract boolean isNullPiece();

    public boolean isSameCampWith(final Camp camp) {
        return this.camp == camp;
    }
}
