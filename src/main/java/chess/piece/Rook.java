package chess.piece;

import chess.Position;
import chess.Team;

public class Rook extends Piece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public void move(Position position) {
        if (!canMove(position)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        //말 이동
    }

    private boolean canMove(Position position) {
        return isCorrectDirection(position);
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) || this.position.isHorizontal(position);
    }

//    boolean 방향맞는지확인();
//    boolean 거리가맞는지확인();
//    boolean 길목확인하는애();


}
