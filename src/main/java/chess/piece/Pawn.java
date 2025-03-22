package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

public class Pawn extends Piece {

    private static boolean INITIAL_STATE = true;

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(final Position position) {
        if (color.isBlack()) {
            if (INITIAL_STATE) {
                if (this.position.canMoveDown(2) && this.position.moveDown(2).equals(position)) {
                    this.position = this.position.moveDown(2);
                    INITIAL_STATE = false;
                    return;
                }
            }
            if (this.position.canMove(Movement.DOWN) && this.position.move(Movement.DOWN).equals(position)) {
                this.position = this.position.move(Movement.DOWN);
                INITIAL_STATE = false;
                return;
            }
        }

        if (color.isWhite()) {
            if (INITIAL_STATE) {
                if (this.position.canMoveUp(2) && this.position.moveUp(2).equals(position)) {
                    this.position = this.position.moveUp(2);
                    INITIAL_STATE = false;
                    return;
                }
            }
            if (this.position.canMove(Movement.UP) && this.position.move(Movement.UP).equals(position)) {
                this.position = this.position.move(Movement.UP);
                INITIAL_STATE = false;
                return;
            }
        }

        throw new IllegalArgumentException("[ERROR] 움직일 수 없음");
    }

    public Position position() {
        return new Position(position.column(), position.row());
    }

    @Override
    public String name() {
        return "Pawn";
    }
}
