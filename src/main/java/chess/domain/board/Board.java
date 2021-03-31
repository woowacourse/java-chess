package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Board {
    private static final int PAWN_SCORE_DISADVANTAGE_SIZE = 2;

    private final List<Square> board;

    protected Board(List<Square> board) {
        this.board = board;
    }

    public Square findByPosition(Position position) {
        return this.board.stream()
                .filter(square -> square.isSamePosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 포지션을 찾을 수 없습니다."));
    }

    public Color findColorByPosition(Position position) {
        Piece colorablePiece = findByPosition(position).getPiece();
        return colorablePiece.getColor();
    }

    public MoveResult move(Position from, Position to) {
        Square fromSquare = this.findByPosition(from);
        return fromSquare.move(createMoveOrder(from, to));
    }

    public MoveOrder createMoveOrder(Position from, Position to) {
        return new MoveOrder(Direction.of(from, to), getRoute(from, to), findByPosition(from), findByPosition(to));
    }

    private List<Square> getRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<Position> route = new ArrayList<>();
        Position currentPosition = from.getNextPosition(direction);

        while (!currentPosition.equals(to)) {
            route.add(currentPosition);
            currentPosition = currentPosition.getNextPosition(direction);
        }
        return route.stream()
                .map(this::findByPosition)
                .collect(toList());
    }

    public Map<Color, Double> getScoreMap() {
        return Color.getUserColors().stream()
                .collect(toMap(Function.identity(), this::getScore));
    }

    private double getScore(Color color) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> scorePerFiles(piecesPerFiles(color, file)))
                .sum();
    }

    private double scorePerFiles(List<Piece> pieces) {
        double score = pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
        long pawnCount = pieces.stream()
                .filter(Piece::isPawn)
                .count();
        if (pawnCount >= PAWN_SCORE_DISADVANTAGE_SIZE) {
            score -= pawnCount * Pawn.SCORE_DISADVANTAGE_RATIO;
        }
        return score;
    }

    private List<Piece> piecesPerFiles(Color color, File file) {
        return positionStreamPerFiles(file)
                .map(this::findByPosition)
                .map(Square::getPiece)
                .filter(piece -> piece.getColor() == color)
                .collect(toList());
    }

    private Stream<Position> positionStreamPerFiles(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank));
    }
}
