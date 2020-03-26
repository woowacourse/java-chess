package chess.domain.chesspiece;

import chess.domain.game.Team;

public class Queen extends ChessPiece {
    public Queen(Team team) {
        super(ChessPieceInfo.QUEEN, team);
    }
}
