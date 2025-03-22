package chess.piece;

import chess.board.ChessBoard;
import chess.board.Position;

public abstract class Piece {

    protected final Team team;
    protected boolean isFirstMove;

    public Piece(Team team) {
        this.team = team;
        this.isFirstMove = true;
    }

    public Team team() {
        return this.team;
    }

    public void moved() {
        isFirstMove = false;
    }

    // 빈칸인 경우만 체크
    public abstract boolean canMove(Position source, Position destination, ChessBoard board);

    // 적 기물이 있는 경우만 체크
    public abstract boolean canAttack(Position source, Position destination, ChessBoard board);

    public abstract PieceType type();
}
