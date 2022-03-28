package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Score;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> squares) {
        this.pieces = squares;
    }

    private static double scoreOfPiece(Entry<Position, Piece> positionPiece) {
        return Score.valueOf(positionPiece.getValue()).getValue();
    }

    public void move(Position from, Position to) {
        Piece sourcePiece = pieces.get(from);
        Piece targetPiece = pieces.get(to);

        validateNotSameColor(sourcePiece, targetPiece);

        if (!sourcePiece.canMove(targetPiece, from, to) && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        validateNotHurdle(from, to);

        pieces.replace(to, sourcePiece);
        pieces.replace(from, new EmptyPiece());
    }

    public boolean isSameColor(Position position, Color color) {
        return findByPosition(position).getColor() == color;
    }

    private void validateNotSameColor(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotHurdle(Position from, Position to) {
        List<Position> route = findByPosition(from).getRoute(from, to);

        for (Position position : route) {
            if (!findByPosition(position).isEmpty()) {
                throw new IllegalArgumentException("이동할 수 없다.");
            }
        }
    }

    public Piece findByPosition(Position position) {
        return pieces.get(position);
    }

    public Map<Color, Double> getColorsTotalScore() {
        Map<Color, Double> totalScore = new EnumMap<>(Color.class);
        totalScore.put(Color.WHITE, getTotalScore(Color.WHITE));
        totalScore.put(Color.BLACK, getTotalScore(Color.BLACK));

        return totalScore;
    }

    private double getTotalScore(Color color) {
        return pieces.entrySet().stream()
                .filter(positionPiece -> isSameColor(positionPiece.getKey(), color))
                .mapToDouble(Board::scoreOfPiece)
                .sum();
    }

    public boolean isKingAlive(Position to) {
        return !findByPosition(to).isKing();
    }
}
