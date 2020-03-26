package chess.domain.chesspiece;

import chess.domain.game.Team;

public class Knight extends ChessPiece {
    public Knight(Team team) {
        super(ChessPieceInfo.KNIGHT, team);
    }
}
