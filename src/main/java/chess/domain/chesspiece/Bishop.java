package chess.domain.chesspiece;

import chess.domain.game.Team;

public class Bishop extends ChessPiece {
    public Bishop(Team team) {
        super(ChessPieceInfo.BISHOP, team);
    }
}
