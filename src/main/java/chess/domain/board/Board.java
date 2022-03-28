package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.ArrayList;
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

    public Piece playTurn(Position start, Position target) {
        Piece selectedPiece = getPiece(start);
        Piece targetPiece = getPiece(target);
        if (selectedPiece.isKnight()) {
            return jump(selectedPiece, targetPiece);
        }
        return moveStraight(selectedPiece, targetPiece);
    }

    private Piece jump(Piece selectedPiece, Piece targetPiece) {
        return move(selectedPiece, targetPiece);
    }

    private Piece moveStraight(Piece selectedPiece, Piece targetPiece) {
        Direction direction = Direction.findDirection(selectedPiece.getPosition(), targetPiece.getPosition());
        checkPath(selectedPiece.getPosition(), targetPiece.getPosition(), direction);
        return move(selectedPiece, targetPiece);
    }

    private Piece move(Piece selected, Piece targetPiece) {
        if (selected.isMovable(targetPiece)) {
            updateBoard(selected, targetPiece);
            return targetPiece;
        }
        throw new IllegalArgumentException(INVALID_MOVEMENT_EXCEPTION_MESSAGE);
    }

    private void checkPath(Position start, Position target, Direction direction) {
        if (Position.calculateStraightDistance(start, target) == 1) {
            return;
        }
        Position afterStartTarget = Position.createNextPosition(start, direction);

        for (Position position = afterStartTarget;
             !position.equals(target);
             position = Position.createNextPosition(position, direction)) {
            validateCollision(position);
        }
    }

    private void validateCollision(Position position) {
        if (!getPiece(position).isBlank()) {
            throw new IllegalArgumentException(OBSTACLE_EXCEPTION_MESSAGE);
        }
    }

    private void updateBoard(Piece selectedPiece, Piece targetPiece) {
        updatePiece(targetPiece.getPosition(), selectedPiece);
        updatePiece(selectedPiece.getPosition(), new Blank(targetPiece.getPosition()));
        selectedPiece.updatePosition(targetPiece.getPosition());
    }

    private void updatePiece(Position position, Piece piece) {
        Rank rank = ranks.get(position.getY());
        rank.updatePiece(position.getX(), piece);
    }

    public double calculateBlackScore() {
        return calculateScore(new BlackChoicePieceCondition());
    }

    public double calculateWhiteScore() {
        return calculateScore(new WhiteChoicePieceCondition());
    }

    private double calculateScore(ChoicePieceCondition choicePieceCondition) {
        double totalScore = 0;

        for (int i = 0; i < 8; i++) {
            List<Piece> file = makeFile(i, choicePieceCondition);
            totalScore += calculateFileScore(getPawns(file), getNoPawns(file));
        }

        return totalScore;
    }

    private List<Piece> makeFile(int index, ChoicePieceCondition choicePieceCondition) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPiece(index));
        }

        return file.stream()
                .filter(choicePieceCondition::test)
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

    public boolean isBlack(Position position) {
        return getPiece(position).isBlack();
    }

    public Piece getPiece(Position position) {
        Rank rank = ranks.get(position.getY());
        return rank.getPiece(position.getX());
    }

    public Rank getRank(int rankLine) {
        return ranks.get(rankLine);
    }
}
