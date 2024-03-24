package chess.model.piece;

import static chess.model.Direction.DOWN;
import static chess.model.Direction.DOWN_DOWN;
import static chess.model.Direction.DOWN_LEFT;
import static chess.model.Direction.DOWN_RIGHT;
import static chess.model.Direction.UP;
import static chess.model.Direction.UP_LEFT;
import static chess.model.Direction.UP_UP;
import static chess.model.material.Color.BLACK;
import static chess.model.material.Color.WHITE;

import chess.model.Direction;
import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;
import chess.model.position.Row;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final List<Direction> WHITE_ATTACK_DIRECTIONS = List.of(UP_LEFT, UP_LEFT);
    private static final List<Direction> BLACK_ATTACK_DIRECTIONS = List.of(DOWN_LEFT, DOWN_RIGHT);
    private static final Row WHITE_INITIAL_ROW = Row.TWO;
    private static final Row BLACK_INITIAL_ROW = Row.SEVEN;

    public Pawn(Type type, Color color) {
        super(type, color);
    }

    @Override
    public void move(Position source, Position target, Map<Position, Piece> pieces) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.get(target);
        if (sourcePiece.isEnemy(targetPiece)) {
            validateAttack(source, target, sourcePiece);
            return;
        }
        validateDirection(source, target, sourcePiece);
        validateRoute(source, target, pieces);
    }

    public void validateAttack(Position source, Position target, Piece sourcePiece) {
        Direction direction = Direction.findDirectionByDelta(source, target);
        if (sourcePiece.isSameColor(WHITE) && WHITE_ATTACK_DIRECTIONS.contains(direction)) {
            return;
        }
        if (sourcePiece.isSameColor(BLACK) && BLACK_ATTACK_DIRECTIONS.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("Pawn은 공격 시 전방 대각선 1칸 이동만 가능합니다.");
    }

    public void validateDirection(Position source, Position target, Piece sourcePiece) {
        Direction direction = Direction.findDirectionByDelta(source, target);
        if (sourcePiece.isSameColor(WHITE) && whiteCanMove(direction, source)) {
            return;
        }
        if (sourcePiece.isSameColor(BLACK) && blackCanMove(direction, source)) {
            return;
        }
        throw new IllegalArgumentException("Pawn은 1칸 전진 이동 혹은 최초 2칸 전진 이동만 가능합니다.");
    }

    private boolean whiteCanMove(Direction direction, Position source) {
        if (direction == UP_UP && source.isSameRow(WHITE_INITIAL_ROW)) {
            return true;
        }
        return direction == UP;
    }

    private boolean blackCanMove(Direction direction, Position source) {
        if (direction == DOWN_DOWN && source.isSameRow(BLACK_INITIAL_ROW)) {
            return true;
        }
        return direction == DOWN;
    }
}
