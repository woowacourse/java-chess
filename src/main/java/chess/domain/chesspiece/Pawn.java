package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.PAWN;

public class Pawn extends ChessPiece {
    boolean isFirstMove = true;

    public Pawn(Team team) {
        super(PAWN, team);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void firstMoveComplete() {
        isFirstMove = false;
    }
}
