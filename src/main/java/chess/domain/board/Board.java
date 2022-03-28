package chess.domain.board;

import chess.domain.board.state.BlackTurn;
import chess.domain.board.state.BlackWin;
import chess.domain.board.state.BoardInitializer;
import chess.domain.board.state.End;
import chess.domain.board.state.GameStarted;
import chess.domain.board.state.GameState;
import chess.domain.board.state.Playing;
import chess.domain.board.state.WhiteTurn;
import chess.domain.board.state.WhiteWin;
import chess.domain.board.state.Winner;
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
    private static final String IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE = "본인의 기물이 아닙니다.";

    private final Map<Integer, Rank> ranks;

    public Board(Map<Integer, Rank> ranks) {
        this.ranks = ranks;
    }

    public Rank getRank(int rankLine) {
        return ranks.get(rankLine);
    }

    public double calculateBlackScore() {
        double totalScore = 0;

        for (int i = 0; i < 8; i++) {
            List<Piece> file = makeBlackFile(i);
            List<Piece> pawns = getPawns(file);
            List<Piece> noPawns = getNoPawns(file);
            totalScore += calculateFileScore(pawns, noPawns);
        }

        return totalScore;
    }

    private List<Piece> makeBlackFile(int index) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPieces().get(index));
        }

        return file.stream()
                .filter(Piece::isBlack)
                .collect(Collectors.toList());
    }

    public double calculateWhiteScore() {
        double totalScore = 0;

        for (int i = 0; i < 8; i++) {
            List<Piece> file = makeWhiteFile(i);
            List<Piece> pawns = getPawns(file);
            List<Piece> noPawns = getNoPawns(file);
            totalScore += calculateFileScore(pawns, noPawns);
        }

        return totalScore;
    }

    private List<Piece> makeWhiteFile(int index) {
        List<Piece> file = new ArrayList<>();

        for (Rank rank : ranks.values()) {
            file.add(rank.getPieces().get(index));
        }

        return file.stream()
                .filter(piece -> !piece.isBlack() && !piece.isBlank())
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

    private Piece getPiece(Position position) {
        return ranks.get(position.getY())
                .getPieces()
                .get(position.getX());
    }

    public Piece move(Position start, Position target, boolean isBlackTurn) {
        Piece selected = getPiece(start);

        if (selected.isBlack() != isBlackTurn) {
            throw new IllegalArgumentException(IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE);
        }

        if (selected.isKnight()) {
            return jump(start, target);
        }
        return moveStraight(start, target);
    }

    private Piece jump(Position start, Position target) {
        Piece selected = getPiece(start);
        Piece targetPiece = getPiece(target);

        if (selected.isMovable(targetPiece)) {
            updateBoard(target, selected, start, targetPiece);
            return targetPiece;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT_EXCEPTION_MESSAGE);
    }

    private Piece moveStraight(Position start, Position target) {
        Piece selected = getPiece(start);
        Piece targetPiece = getPiece(target);
        Direction direction = Direction.findDirection(start, target);

        checkPath(start, target, direction);

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
}
