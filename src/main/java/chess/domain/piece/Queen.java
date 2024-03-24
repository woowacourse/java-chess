package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.position.Direction.*;

public class Queen extends Piece {
    private static List<Direction> PASSING = List.of(N,S,E,W,NE,NW,SE,SW);

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        try{
            return PASSING.contains(DirectionJudge.judge(start, destination))
                    && board.pathIsAllEmpty(start.findPath(destination));
        }catch(IllegalStateException e){
            throw new IllegalArgumentException("퀸은 대각선 호긍ㄴ 직선으로만 움직일 수 있습니다.");
        }
    }
}
