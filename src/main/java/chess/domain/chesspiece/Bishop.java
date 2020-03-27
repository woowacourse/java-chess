package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.BISHOP;

public class Bishop extends ChessPiece {
    public Bishop(Team team) {
        super(BISHOP, team);
    }
}
