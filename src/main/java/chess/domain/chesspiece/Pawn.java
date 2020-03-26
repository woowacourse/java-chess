package chess.domain.chesspiece;

import chess.domain.game.Team;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Team team) {
        super(ChessPieceInfo.PAWN, team);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void firstMoveComplete() {
        isFirstMove = false;
    }
}
