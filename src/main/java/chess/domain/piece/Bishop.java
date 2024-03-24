package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.position.Direction.*;

public class Bishop extends Piece {
    private static List<Direction> PASSING = List.of(NW, NE, SW, SE);

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        try{
            return PASSING.contains(DirectionJudge.judge(start, destination))
                    && board.pathIsAllEmpty(start.findPath(destination));
        }catch(IllegalStateException e){
            throw new IllegalArgumentException("비숍은 대각선으로만 움직일 수 있습니다.");
        }
    }
}
