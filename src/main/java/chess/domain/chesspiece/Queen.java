package chess.domain.chesspiece;

import chess.domain.game.Team;

import static chess.domain.chesspiece.ChessPieceInfo.QUEEN;

public class Queen extends ChessPiece {
    public Queen(Team team) {
        super(QUEEN, team);
    }
}
