package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.piece.info.Score;
import chess.domain.position.Cross;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public static final char BLACK_INITIAL_Y = '7';
    public static final char WHITE_INITIAL_Y = '2';

    public Pawn(Position position, Color color) {
        super(position, Name.PAWN, color, new Score(1));
    }

    private void checkAbleToAttack(Position target, Pieces pieces) {
        Piece targetPiece = pieces.findByPosition(target);
        if (targetPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 공격하려는 위치에 상대방 말이 없습니다.");
        }
        pieces.removePieceByPosition(target);
        this.position = target;
    }

    private boolean moveByBlackRule(Position target, Pieces pieces) {
        if (this.position.sameY(BLACK_INITIAL_Y) &&
                this.position.yDistance(target) > 0 && this.position.yDistance(target) <= 2) {
            Cross pawnCross = Cross.findCrossByTwoPosition(position, target);
            pawnCross.hasPieceInPath(position, target, pieces);
            this.position = target;
            return true;
        }
        if (this.position.yDistance(target) == 1) {
            Cross pawnCross = Cross.findCrossByTwoPosition(position, target);
            pawnCross.hasPieceInPath(position, target, pieces);
            this.position = target;
            return true;
        }
        return false;
    }

    private boolean moveByWhiteRule(Position target, Pieces pieces) {
        if (this.position.sameY(WHITE_INITIAL_Y) &&
                target.yDistance(this.position) > 0 && target.yDistance(this.position) <= 2) {
            Cross pawnCross = Cross.findCrossByTwoPosition(position, target);
            pawnCross.hasPieceInPath(position, target, pieces);
            this.position = target;
            return true;
        }
        if (target.yDistance(this.position) == 1) {
            Cross pawnCross = Cross.findCrossByTwoPosition(position, target);
            pawnCross.hasPieceInPath(position, target, pieces);
            this.position = target;
            return true;
        }
        return false;
    }

    @Override
    public void checkMovable(Piece targetPiece, Direction direction) {
        if (color.same(Color.BLACK) && !Direction.blackPawnDirection().contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 올바른 방향이 아닙니다.");
        }
        if (color.same(Color.WHITE) && !Direction.whitePawnDirection().contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 올바른 방향이 아닙니다.");
        }
        if (Direction.diagonalDirection().contains(direction) && targetPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 공격하려는 위치에 상대방 말이 없습니다.");
        }
        if ((this.position.sameY(BLACK_INITIAL_Y) || this.position.sameY(WHITE_INITIAL_Y)) &&
                (Math.abs(this.position.yDistance(targetPiece.position)) <= 2)) {
            System.out.println(Math.abs(this.position.yDistance(targetPiece.position)));
            return;
        }
        if (Math.abs(this.position.yDistance(targetPiece.position)) != 1) {
            throw new IllegalArgumentException("[ERROR] 움직일 수 없는 위치입니다.");
        }
    }
}
