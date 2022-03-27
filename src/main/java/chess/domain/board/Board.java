package chess.domain.board;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.piece.InvalidPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Board {

    private static final double PAWN_SUBTRACT_UNIT = 0.5;
    private static final double DEFAULT_SCORE = 0D;

    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public MoveResult move(String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);

        Piece pieceAtFrom = board.getOrDefault(fromPosition, InvalidPiece.getInstance());
        Piece pieceAtTo = board.getOrDefault(toPosition, InvalidPiece.getInstance());

        if (isInValidMove(fromPosition, toPosition, pieceAtFrom, pieceAtTo)) {
            return MoveResult.FAIL;
        }

        movePiece(fromPosition, toPosition, pieceAtFrom);
        return getMoveResult(pieceAtTo);
    }

    private boolean isInValidMove(Position from, Position to, Piece pieceAtFrom, Piece pieceAtTo) {
        return isInvalidFrom(pieceAtFrom) || isNotMovable(from, to, pieceAtFrom, pieceAtTo)
            || canNotGoOnTheWay(from, to, pieceAtFrom);
    }

    private boolean isInvalidFrom(Piece pieceAtFrom) {
        return pieceAtFrom.isInValid();
    }

    private boolean isNotMovable(Position from, Position to, Piece pieceAtFrom, Piece pieceAtTo) {
        return !pieceAtFrom.movable(from.calculateDistance(to), pieceAtTo);
    }

    private boolean canNotGoOnTheWay(Position from, Position to, Piece pieceAtFrom) {
        return !pieceAtFrom.isKnight() && isPieceOnTheWay(from, to);
    }

    private boolean isPieceOnTheWay(Position fromPosition, Position toPosition) {
        List<Position> positionsOnTheWay = fromPosition.getPositionBetween(toPosition);

        return positionsOnTheWay.stream()
            .anyMatch(board::containsKey);
    }

    private void movePiece(Position from, Position to, Piece pieceAtFrom) {
        board.put(to, pieceAtFrom);
        board.remove(from);
    }

    private MoveResult getMoveResult(Piece pieceAtTo) {
        if (pieceAtTo instanceof King) {
            return MoveResult.ENDED;
        }
        return MoveResult.SUCCESS;
    }

    public Map<Color, Double> getScore() {
        List<Position> whitePawnPositions = getPawnPositionsByColor(Piece::isWhite);
        List<Position> blackPawnPositions = getPawnPositionsByColor(Piece::isBlack);

        double whitePawnScore = calculatePawnScore(whitePawnPositions);
        double blackPawnScore = calculatePawnScore(blackPawnPositions);

        return calculateChessScore(whitePawnScore, blackPawnScore);
    }

    private List<Position> getPawnPositionsByColor(Predicate<Piece> condition) {
        return board.entrySet()
            .stream()
            .filter(entry -> entry.getValue() instanceof Pawn)
            .filter(entry -> condition.test(entry.getValue()))
            .map(Entry::getKey)
            .collect(Collectors.toList());
    }

    private double calculatePawnScore(List<Position> pawnPositions) {
        double pawnScore = pawnPositions.size();
        for (File file : File.values()) {
            long count = pawnPositions.stream()
                .filter(position -> position.isSameFile(file))
                .count();

            pawnScore = subtractPawnScore(pawnScore, count);
        }
        return pawnScore;
    }

    private double subtractPawnScore(double pawnScore, long count) {
        if (count > 1) {
            pawnScore -= count * PAWN_SUBTRACT_UNIT;
        }
        return pawnScore;
    }

    private Map<Color, Double> calculateChessScore(double whitePawnScore, double blackPawnScore) {
        Map<Color, Double> chessScore = getChessScoreWithoutPawn();

        chessScore.put(Color.WHITE,
            chessScore.getOrDefault(Color.WHITE, DEFAULT_SCORE) + whitePawnScore);
        chessScore.put(Color.BLACK,
            chessScore.getOrDefault(Color.BLACK, DEFAULT_SCORE) + blackPawnScore);

        return chessScore;
    }

    private Map<Color, Double> getChessScoreWithoutPawn() {
        return board.values()
            .stream()
            .filter(piece -> !(piece instanceof Pawn))
            .collect(groupingBy(Color::from, summingDouble(Score::from)));
    }

    public Map<Position, Piece> getBoard() {
        return new LinkedHashMap<>(board);
    }
}
