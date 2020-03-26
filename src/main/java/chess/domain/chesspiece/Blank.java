package chess.domain.chesspiece;

import chess.domain.game.Team;

public class Blank extends ChessPiece {
    public Blank(Team team) {
        super(ChessPieceInfo.BLANK, team);
    }
}
