package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final String NAME = "P";
    private static final String WHITE_INIT_FILE = "2";
    private static final String BLACK_INIT_FILE = "7";

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
    public void canMove(Position from, Position to) {
        if (isBlack() && checkMove(from, to, 1, BLACK_INIT_FILE)) {
            return;
        }

        if (!isBlack() && checkMove(from, to, -1, WHITE_INIT_FILE)) {
            return;
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
    }

    private boolean checkMove(Position from, Position to, int movableDistance, String initFile) {
        int fileDistance = from.fileDistance(to);
        if (movableDistance == fileDistance) {
            return true;
        }
        if (from.isSameFile(initFile)) {
            return fileDistance == movableDistance * 2;
        }
        return false;
    }
}
