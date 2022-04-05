package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private static final String INVALID_MOVEMENT_EXCEPTION_MESSAGE = "이동이 불가능한 위치입니다.";
    private static final String OBSTACLE_EXCEPTION_MESSAGE = "경로에 기물이 존재합니다.";

    private final Map<Integer, Rank> ranks;

    public Board(Map<Integer, Rank> ranks) {
        this.ranks = ranks;
    }

    public Piece movePiece(Position start, Position target) {
        Piece selected = getPiece(start);

        if (selected.isKnight()) {
            return jump(start, target);
        }
        return moveStraight(start, target);
    }

    private Piece jump(Position start, Position target) {
        return executeMove(start, target);
    }

    private Piece moveStraight(Position start, Position target) {
        checkPath(start, target, Direction.findDirection(start, target));
        return executeMove(start, target);
    }

    private void checkPath(Position start, Position target, Direction direction) {
        if (start.calculateStraightDistance(target) == 1) {
            return;
        }
        Position afterStartTarget = start.createNextPosition(direction);

        for (Position position = afterStartTarget;
             !position.equals(target);
             position = position.createNextPosition(direction)) {
            validateCollision(position);
        }
    }

    private void validateCollision(Position position) {
        if (!getPiece(position).isBlank()) {
            throw new IllegalArgumentException(OBSTACLE_EXCEPTION_MESSAGE);
        }
    }

    private Piece executeMove(Position start, Position target) {
        Piece selected = getPiece(start);
        Piece targetPiece = getPiece(target);

        if (selected.isMovable(targetPiece)) {
            updateBoard(target, selected, start, targetPiece);
            return targetPiece;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT_EXCEPTION_MESSAGE);
    }

    private void updateBoard(Position target, Piece selected, Position start, Piece targetPiece) {
        updatePiece(target, selected);
        updatePiece(start, new Blank(start));
        selected.updatePosition(targetPiece.getPosition());
    }

    private void updatePiece(Position position, Piece piece) {
        ranks.get(position.getY())
                .getPieces()
                .set(position.getX(), piece);
    }

    public double calculateBlackScore() {
        return calculateScore(Piece::isBlack);
    }

    public double calculateWhiteScore() {
        return calculateScore(piece -> !piece.isBlack() && !piece.isBlank());
    }

    private double calculateScore(PieceIdentifier pieceIdentifier) {
        double totalScore = 0;

        for (int i = 0; i < 8; i++) {
            List<Piece> file = makeFile(i, pieceIdentifier);
            List<Piece> pawns = getPawns(file);
            List<Piece> noPawns = getNoPawns(file);
            totalScore += calculateFileScore(pawns, noPawns);
        }

        return totalScore;
    }

    private List<Piece> makeFile(int index, PieceIdentifier pieceIdentifier) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPieces().get(index));
        }

        return file.stream()
                .filter(pieceIdentifier::identify)
                .collect(Collectors.toList());
    }

    private List<Piece> getPawns(List<Piece> file) {
        return file.stream()
                .filter(Piece::isPawn)
                .collect(Collectors.toList());
    }

    private List<Piece> getNoPawns(List<Piece> file) {
        return file.stream()
                .filter(piece -> !piece.isPawn())
                .collect(Collectors.toList());
    }

    private double calculateFileScore(List<Piece> pawns, List<Piece> noPawns) {
        double pawnsScore = calculatePiecesScore(pawns);
        double noPawnsScore = calculatePiecesScore(noPawns);

        if (pawns.size() >= 2) {
            pawnsScore *= 0.5;
        }

        return pawnsScore + noPawnsScore;
    }

    private double calculatePiecesScore(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public Piece getPiece(Position position) {
        return ranks.get(position.getY())
                .getPieces()
                .get(position.getX());
    }

    public Rank getRank(int rankLine) {
        return ranks.get(rankLine);
    }

    public Map<String, Piece> toMap() {
        Map<String, Piece> map = new HashMap<>();
        List<Piece> pieces = new ArrayList<>();
        for (Rank value : ranks.values()) {
            pieces.addAll(value.getPieces());
        }
        for (Piece piece : pieces) {
            map.put(piece.getPosition().getPosition(), piece);
        }
        return map;
    }
}
