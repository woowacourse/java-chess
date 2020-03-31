package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.ROOK;

public class Rook extends ChessPiece {
    public Rook(Team team) {
        super(ROOK, team);
    }
}
