package chess.domain.board;

import chess.domain.order.MoveResult;
import chess.domain.order.MoveRoute;
import chess.domain.order.RouteEntry;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.*;
import chess.domain.statistics.ScoreTable;
import chess.exception.DomainException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class ChessBoard {
    private static final int PAWN_SCORE_DISADVANTAGE_SIZE = 2;

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard from(Map<Position, Piece> realPiecesMap) {
        Map<Position, Piece> board = new HashMap<>(realPiecesMap);
        for (Position position: PositionRepository.positions()) {
            board.merge(position, new Blank(), (existPiece, newPiece) -> existPiece);
        }
        return new ChessBoard(board);
    }

    public Piece getPieceByPosition(Position position) {
        return this.board.get(position);
    }

    public boolean hasPiece(Position position) {
        return this.board.get(position).isNotBlank();
    }

    public MoveResult move(MoveRoute moveRoute) {
        Position fromPosition = moveRoute.getFromPosition();
        Position toPosition = moveRoute.getToPosition();
        Piece pieceToMove = this.getPieceByPosition(moveRoute.getFromPosition());

        validateRealPieceHasBeenChosen(fromPosition);
        validateProperMoveStrategy(pieceToMove, moveRoute);

        Piece pieceAtToPosition = this.board.get(toPosition);
        this.board.put(toPosition, pieceToMove);
        this.board.put(fromPosition, new Blank());
        return new MoveResult(pieceAtToPosition);
    }

    private void validateRealPieceHasBeenChosen(Position fromPosition) {
        if (!this.hasPiece(fromPosition)) {
            throw new DomainException("해당 위치에는 말이 없습니다.");
        }
    }

    private void validateProperMoveStrategy(Piece pieceToMove, MoveRoute moveRoute) {
        if (!pieceToMove.canMove(moveRoute)) {
            throw new DomainException("기물이 움직일 수 없는 상황입니다.");
        }
    }

    public MoveRoute createMoveRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<RouteEntry> route = new ArrayList<>();

        Piece currentPiece;
        RouteEntry currentRouteEntry;

        Position currentPosition = from;
        while (!currentPosition.equals(to)) {
            currentPiece = getPieceByPosition(currentPosition);
            currentRouteEntry = new RouteEntry(currentPosition, currentPiece);
            route.add(currentRouteEntry);

            currentPosition = currentPosition.getNextPosition(direction);
        }
        currentPiece = getPieceByPosition(currentPosition);
        currentRouteEntry = new RouteEntry(currentPosition, currentPiece);
        route.add(currentRouteEntry);

        return new MoveRoute(route);
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

    private double scorePerFiles(List<Piece> realPieces) {
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

    private List<Piece> realPiecesPerFiles(Color color, File file) {
        return positionStreamPerFiles(file)
                .filter(this::hasPiece)
                .map(this::getPieceByPosition)
                .filter(piece -> piece.isSameColor(color))
                .collect(toList());
    }

    private Stream<Position> positionStreamPerFiles(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank));
    }

    public Map<Position, Piece> board() {
        return Collections.unmodifiableMap(this.board);
    }
}
