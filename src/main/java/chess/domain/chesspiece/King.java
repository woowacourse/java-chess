package chess.domain.chesspiece;

import chess.domain.game.Team;

public class King extends ChessPiece {
    public King(Team team) {
        super(ChessPieceInfo.KING, team);
    }
}
