package chess.domain.board;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.piece.InvalidPiece;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public boolean move(String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);

        Piece pieceAtFrom = board.getOrDefault(fromPosition, InvalidPiece.getInstance());
        Piece pieceAtTo = board.getOrDefault(toPosition, InvalidPiece.getInstance());

        // 출발 좌표에 기물이 없으면 false다
        if (pieceAtFrom.isInValid()) {
            return false;
        }

        // 출발 좌표에 있는 기물이 목적지로 이동이 불가하면 false다
        boolean movable = pieceAtFrom.movable(fromPosition.calculateDistance(toPosition), pieceAtTo);
        if (!movable) {
            return false;
        }

        // 이동 경로 내 다른 기물이 있을 경우 false다
        if (!pieceAtFrom.isKnight() && isPieceOnTheWay(fromPosition, toPosition)) {
            return false;
        }

        board.put(toPosition, pieceAtFrom);
        board.remove(fromPosition);
        return true;
    }

    private boolean isPieceOnTheWay(Position fromPosition, Position toPosition) {
        List<Position> positionsOnTheWay = fromPosition.getPositionBetween(toPosition);

        return positionsOnTheWay.stream()
                .anyMatch(board::containsKey);
    }

    public Map<Color, Double> getScore() {
        Map<Color, Double> scores = new HashMap<>();

        double blackSum = board.values()
                .stream()
                .filter(Piece::isBlack)
                .mapToDouble(blackPiece -> Score.from(blackPiece))
                .sum();

        double whiteSum = board.values()
                .stream()
                .filter(Piece::isWhite)
                .mapToDouble(whitePiece -> Score.from(whitePiece))
                .sum();

        scores.put(Color.BLACK, blackSum);
        scores.put(Color.WHITE, whiteSum);

        return scores;
    }


    public Map<Position, Piece> getBoard() {
        return new LinkedHashMap<>(board);
    }
}
