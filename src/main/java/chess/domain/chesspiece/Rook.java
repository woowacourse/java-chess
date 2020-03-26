package chess.domain.chesspiece;

import chess.domain.game.Team;

public class Rook extends ChessPiece {
    public Rook(Team team) {
        super(ChessPieceInfo.ROOK, team );
    }
}
