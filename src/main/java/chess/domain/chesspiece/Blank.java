package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.BLANK;

public class Blank extends ChessPiece {
    public Blank(Team team) {
        super(BLANK, team);
    }
}
