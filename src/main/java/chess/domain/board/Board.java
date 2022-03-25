package chess.domain.board;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

import chess.domain.Color;
import chess.domain.MoveResult;
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

    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public MoveResult move(String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);

        Piece pieceAtFrom = board.getOrDefault(fromPosition, InvalidPiece.getInstance());
        Piece pieceAtTo = board.getOrDefault(toPosition, InvalidPiece.getInstance());

        // 출발 좌표에 기물이 없으면 false다
        if (pieceAtFrom.isInValid()) {
            return MoveResult.FAIL;
        }

        // 출발 좌표에 있는 기물이 목적지로 이동이 불가하면 false다
        boolean movable = pieceAtFrom.movable(fromPosition.calculateDistance(toPosition), pieceAtTo);
        if (!movable) {
            return MoveResult.FAIL;
        }

        // 이동 경로 내 다른 기물이 있을 경우 false다
        if (!pieceAtFrom.isKnight() && isPieceOnTheWay(fromPosition, toPosition)) {
            return MoveResult.FAIL;
        }

        board.put(toPosition, pieceAtFrom);
        board.remove(fromPosition);

        if (pieceAtTo instanceof King) {
            return MoveResult.ENDED;
        }

        return MoveResult.SUCCESS;
    }

    private boolean isPieceOnTheWay(Position fromPosition, Position toPosition) {
        List<Position> positionsOnTheWay = fromPosition.getPositionBetween(toPosition);

        return positionsOnTheWay.stream()
                .anyMatch(board::containsKey);
    }

    public Map<Color, Double> getScore() {
        List<Position> whitePawnPositions = getCollect(Piece::isWhite);
        List<Position> blackPawnPositions = getCollect(Piece::isBlack);

        double scoreForWhitePawns = calculatePawnScore(whitePawnPositions);
        double scoreForBlackPawns = calculatePawnScore(blackPawnPositions);

        Map<Color, Double> chessScore = board.values()
                .stream()
                .filter(piece -> !(piece instanceof Pawn))
                .collect(groupingBy(Color::from, summingDouble(Score::from)));

        chessScore.put(Color.WHITE, chessScore.getOrDefault(Color.WHITE, 0D) + scoreForWhitePawns);
        chessScore.put(Color.BLACK, chessScore.getOrDefault(Color.BLACK, 0D) + scoreForBlackPawns);

        return chessScore;
    }

    private double calculatePawnScore(List<Position> pawnPositions) {
        double pawnScore = pawnPositions.size();

        for (File file : File.values()) {
            long count = pawnPositions.stream()
                    .filter(position -> position.isSameFile(file))
                    .count();

            if (count > 1) {
                pawnScore -= count * 0.5;
            }
        }

        return pawnScore;
    }

    private List<Position> getCollect(Predicate<Piece> condition) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Pawn)
                .filter(entry -> condition.test(entry.getValue()))
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> getBoard() {
        return new LinkedHashMap<>(board);
    }
}
