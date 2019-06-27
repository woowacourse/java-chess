package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;
import chess.domain.Point;

import java.util.List;

public class SingleMovePiece extends Piece {

    SingleMovePiece(ChessTeam team, PieceInfo info, List<MoveVector> directions) {
        super(team, info, directions);
    }

    @Override
    public Direction move(Point p1, Point p2) {
        return move(MoveVector.findVector(p1.direction(p2)));
    }
}
