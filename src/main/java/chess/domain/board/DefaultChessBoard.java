package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.RealPiece;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.statistics.ScoreTable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DefaultChessBoard implements Board {
    private static final int PAWN_SCORE_DISADVANTAGE_SIZE = 2;

    private final Map<Position, Square> board;

    DefaultChessBoard(Map<Position, Square> board) {
        this.board = board;
    }

    public Square findByPosition(Position position) {
        return this.board.get(position);
    }

    public Map<Position, Square> board() {
        return Collections.unmodifiableMap(board);
    }

    public MoveResult move(Position from, Position to) {
        Square fromSquare = this.findByPosition(from);
        return fromSquare.move(createMoveOrder(this, from, to));
    }

    public MoveOrder createMoveOrder(Board board, Position from, Position to) {
        return new MoveOrder(this, from, to);
    }

    public List<Square> getRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<Position> route = new ArrayList<>();
        Position currentPosition = from.getNextPosition(direction);

        while (!currentPosition.equals(to)) {
            route.add(currentPosition);
            currentPosition = currentPosition.getNextPosition(direction);
        }
        return route.stream()
                .map(board::get)
                .collect(toList());
    }

    public Map<Color, Double> getScoreMap() {
        return Arrays.stream(Color.values())
                .collect(toMap(Function.identity(), this::getScore));
    }

    private double getScore(Color color) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> scorePerFiles(realPiecesPerFiles(color, file)))
                .sum();
    }

    private double scorePerFiles(List<RealPiece> realPieces) {
        double score = realPieces.stream()
                .mapToDouble(ScoreTable::convertToScore)
                .sum();
        long pawnCount = realPieces.stream()
                .filter(realPiece -> realPiece instanceof Pawn)
                .count();
        if (pawnCount >= PAWN_SCORE_DISADVANTAGE_SIZE) {
            score -= pawnCount * ScoreTable.getPawnDisadvantageRatio();
        }
        return score;
    }

    private List<RealPiece> realPiecesPerFiles(Color color, File file) {
        return positionStreamPerFiles(file)
                .map(this::findByPosition)
                .filter(Square::hasPiece)
                .map(Square::getPiece)
                .filter(realPiece -> realPiece.isSameColor(color))
                .collect(toList());
    }

    private Stream<Position> positionStreamPerFiles(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank));
    }
}

