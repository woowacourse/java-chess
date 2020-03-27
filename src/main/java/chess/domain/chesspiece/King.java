package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.KING;

public class King extends ChessPiece {
    public King(Team team) {
        super(KING, team);
    }
}
