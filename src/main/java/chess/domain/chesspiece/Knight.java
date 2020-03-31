package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.KNIGHT;

public class Knight extends ChessPiece {
    public Knight(Team team) {
        super(KNIGHT, team);
    }
}
