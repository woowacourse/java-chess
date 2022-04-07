package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends ChessPiece {

    private static final String NAME = "Q";

    public Queen(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("d1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("d8"));
    }

    @Override
    public void checkMovable(Position from, Position to) {
        int fileDistance = Math.abs(from.fileDistance(to));
        int rankDistance = Math.abs(from.rankDistance(to));

        boolean sameFile = from.isSameFile(to);
        boolean sameRank = from.isSameRank(to);

        if ((!sameFile && !sameRank) && (fileDistance != rankDistance)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 위치입니다.");
        }
    }

    @Override
    public double getScore() {
        return 9.0;
    }

    @Override
    public String convertToImageName() {
        return (getColor().name() + "-queen").toLowerCase();
    }
}
