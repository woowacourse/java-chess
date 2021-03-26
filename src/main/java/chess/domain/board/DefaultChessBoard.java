package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.piece.*;
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

    private final Map<Position, Piece> board;

    DefaultChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece getPieceByPosition(Position position) {
        return this.board.get(position);
    }

    public RealPiece getRealPieceByPosition(Position position) {
        if (!this.hasPiece(position)) {
            throw new NoSuchElementException("해당 위치에 말이 없습니다.");
        }
        return (RealPiece)this.board.get(position);
    }

    public boolean hasPiece(Position position) {
        return this.board.get(position).isNotBlank();
    }

    public MoveResult move(MoveOrder moveOrder) {
        Position fromPosition = moveOrder.getFromPosition();
        if (!this.hasPiece(fromPosition)) {
            throw new NoSuchElementException("해당 위치에는 말이 없습니다.");
        }

        RealPiece pieceToMove = this.getRealPieceByPosition(moveOrder.getFromPosition());
        Position toPosition = moveOrder.getToPosition();

        if (pieceToMove.canMove(moveOrder)) {
            Piece piece = this.board.get(toPosition);
            this.board.put(toPosition, pieceToMove);
            this.board.put(fromPosition, new Blank());
            return new MoveResult(piece);
        }

        throw new IllegalArgumentException("기물이 움직일 수 없는 상황입니다.");
    }

    public List<Piece> getRoute(Position from, Position to) {
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
                .filter(this::hasPiece)
                .map(this::getRealPieceByPosition)
                .filter(realPiece -> realPiece.isSameColor(color))
                .collect(toList());
    }

    private Stream<Position> positionStreamPerFiles(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank));
    }
}

