package chess.domain.board;


import chess.domain.piece.*;

import java.util.*;

public class Board {
    private static final double TOTAL_SCORE = 38;
    private static final double PAWN_SAME_HORIZONTAL_SCORE = 0.5;
    private static final double PAWN_SAME_HORIZONTAL_MIN_COUNT = 2;
    public static final int MIN_BORDER = 1;
    public static final int MAX_BORDER = 8;

    private final Map<Position, Piece> board;
    private final Map<Team, Double> deadPieceByTeam;
    private Team lastTurn;

    public Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
        this.deadPieceByTeam = initializeDeadPieceByTeamMap();
        lastTurn = Team.BLACK;
    }

    private Map<Team, Double> initializeDeadPieceByTeamMap() {
        Map<Team, Double> result = new HashMap<>();
        result.put(Team.BLACK, 0d);
        result.put(Team.WHITE, 0d);
        return result;
    }

    public Piece findPieceFromPosition(Position position) {
        return board.get(position);
    }

    public void movePiece(Position target, Position destination) {
        Piece targetPiece = findPieceFromPosition(target);
        List<Position> targetMovablePositions = targetPiece.searchMovablePositions(target);
        checkMovable(targetMovablePositions, destination);
        move(target, destination, targetPiece);
    }

    private void move(Position target, Position destination, Piece targetPiece) {
        if (targetPiece.isMovable(target, destination, this)) {
            checkTurn(targetPiece);
            Piece destinationPiece = findPieceFromPosition(destination);
            exitGameIfKing(destinationPiece);
            putDeadPieces(destinationPiece);
            movePieceToPosition(targetPiece, destination);
            clearPosition(target);
            return;
        }
        throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
    }

    private void checkTurn(Piece targetPiece) {
        if (targetPiece.isSameTeam(lastTurn)) {
            throw new IllegalArgumentException("해당 팀의 차례가 아닙니다.");
        }
        lastTurn = targetPiece.getTeam();
    }

    private void exitGameIfKing(Piece piece) {
        if(piece instanceof King){
            System.exit(0);
        }
    }

    private void putDeadPieces(Piece destinationPiece) {
        if (Objects.nonNull(destinationPiece)) {
            deadPieceByTeam.put(destinationPiece.getTeam()
                    , deadPieceByTeam.get(destinationPiece.getTeam()) + destinationPiece.getScore());
        }
    }

    private void movePieceToPosition(Piece targetPiece, Position destination) {
        if (targetPiece instanceof Pawn && destination.isDeadLine()) {
            targetPiece = new Queen(targetPiece.getTeam());
        }
        board.put(destination, targetPiece);
    }

    private void clearPosition(Position target) {
        board.put(target, null);
    }

    private void checkMovable(List<Position> targetMovablePositions, Position destination) {
        if (targetMovablePositions.contains(destination)) {
            return;
        }
        throw new UnsupportedOperationException("이동 불가능한 좌표입니다.");
    }

    public double calculateScore(Team team) {
        double defaultScore = TOTAL_SCORE - deadPieceByTeam.get(team);
        return defaultScore - countOfSameLinePawn(team) * PAWN_SAME_HORIZONTAL_SCORE;
    }

    private long countOfSameLinePawn(Team team) {
        return findSameHorizontalTotalPawnCount(team);
    }

    private long findSameHorizontalTotalPawnCount(Team team) {
        long total = 0;
        for (Horizontal horizontal : Horizontal.values()) {
            total += findSameHorizontalPawnCount(team, horizontal);
        }
        return total;
    }

    private long findSameHorizontalPawnCount(Team team, Horizontal horizontal) {
        long count = board.entrySet().stream()
                .filter(entry -> horizontal.isSameHorizontal(entry.getKey())
                        && entry.getValue() instanceof Pawn
                        && entry.getValue().isSameTeam(team))
                .count();

        if (count >= PAWN_SAME_HORIZONTAL_MIN_COUNT) {
            return count;
        }
        return 0;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
