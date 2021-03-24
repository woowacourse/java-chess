package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.direction.QueenDirections;
import chess.domain.piece.moving.QueenMoving;

public class Queen extends Piece {

    public Queen(TeamColor teamColor, Position position) {
        super("q", teamColor, Score.from(9), new QueenMoving(new QueenDirections(), position));
    }
}
