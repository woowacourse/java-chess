package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final String NAME = "P";
    private static final String WHITE_INIT_FILE = "2";
    private static final String BLACK_INIT_FILE = "7";
    private static final int BLACK_MOVABLE_DISTANCE = 1;
    private static final int WHITE_MOVABLE_DISTANCE = -1;
    private static final int PAWN_MOVE_IN_INIT_FILE = 2;
    private static final int PAWN_MOVE_RANGE = 1;

    public Pawn(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        List<Position> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add(new Position((char) i + WHITE_INIT_FILE));
        }
        return list;
    }

    @Override
    public List<Position> getInitBlackPosition() {
        List<Position> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add(new Position((char) i + BLACK_INIT_FILE));
        }
        return list;
    }

    @Override
    public void checkMovable(Position from, Position to) {
        if (from.isSameRank(to)) {
            checkMoveOfColor(from, to);
            return;
        }

        checkCrossMove(from, to);
    }

    private void checkMoveOfColor(Position from, Position to) {
        if (isBlack() && checkMove(from, to, BLACK_MOVABLE_DISTANCE, BLACK_INIT_FILE)) {
            return;
        }

        if (!isBlack() && checkMove(from, to, WHITE_MOVABLE_DISTANCE, WHITE_INIT_FILE)) {
            return;
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }

    private boolean checkMove(Position from, Position to, int movableDistance, String initFile) {
        int fileDistance = from.fileDistance(to);
        if (movableDistance == fileDistance && from.isSameRank(to)) {
            return true;
        }
        if (from.isSameFile(initFile)) {
            return fileDistance == movableDistance * PAWN_MOVE_IN_INIT_FILE;
        }
        return false;
    }

    public void checkCrossMove(Position from, Position to) {
        int fileDistance = from.fileDistance(to);
        if (isPawnMovableDistanceRange(from, to)) {
            if (isBlack() && fileDistance == BLACK_MOVABLE_DISTANCE) {
                return;
            }
            if (!isBlack() && fileDistance == WHITE_MOVABLE_DISTANCE) {
                return;
            }
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }

    private boolean isPawnMovableDistanceRange(Position from, Position to) {
        return Math.abs(from.rankDistance(to)) == PAWN_MOVE_RANGE;
    }

    public void validateStraight(Position source, Position target) {
        if (target.findDirection(source) != Direction.NORTH && target.findDirection(source) != Direction.SOUTH) {
            throw new IllegalArgumentException("폰은 대각선에 상대 기물이 존재해야합니다");
        }
    }

    public void validateCross(Position source, Position target) {
        if (target.findDirection(source) == Direction.NORTH || target.findDirection(source) == Direction.SOUTH) {
            throw new IllegalArgumentException("폰은 대각선 이동으로 적을 잡을 수 있습니다.");
        }
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return 1.0;
    }

    @Override
    public String convertToImageName() {
        return (getColor().name() + "-pawn").toLowerCase();
    }
}
