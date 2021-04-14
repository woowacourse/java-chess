package chess.domain.board;


import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    public static final int MIN_BORDER = 1;
    public static final int MAX_BORDER = 8;
    private static final double PAWN_SAME_HORIZONTAL_MIN_COUNT = 2;

    private final Map<Position, Piece> board;
    private final Map<Team, Double> deadPieceByTeam;
    private final BoardStatus boardStatus;
    private boolean gameOver;

    public Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
        this.deadPieceByTeam = initializeDeadPieceByTeamMap();
        this.boardStatus = new BoardStatus();
        gameOver = false;
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

    public boolean movePiece(Position target, Position destination) {
        Piece targetPiece = findPieceFromPosition(target);
        List<Position> targetMovablePositions = targetPiece.searchMovablePositions(target);
        try {
            checkMovable(targetMovablePositions, destination);
            return move(target, destination, targetPiece);
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> movablePositions(Position target) {
        Piece targetPiece = findPieceFromPosition(target);
        List<Position> targetMovablePositions = targetPiece.searchMovablePositions(target);

        return targetMovablePositions.stream()
                .filter(destination -> board.get(target).isMovable(target, destination, this))
                .map(Position::convertToString)
                .collect(Collectors.toList());
    }

    private boolean move(Position target, Position destination, Piece targetPiece) {
        if (targetPiece.isMovable(target, destination, this)) {
            checkTurn(targetPiece);
            Piece destinationPiece = findPieceFromPosition(destination);
            putDeadPieces(destinationPiece);
            movePieceToPosition(targetPiece, destination);
            clearPosition(target);
            checkDeadKing(destinationPiece);
            return true;
        }
        return false;
    }

    private void checkTurn(Piece targetPiece) {
        if (targetPiece.isSameTeam(boardStatus.getLastTurn())) {
            throw new IllegalArgumentException("해당 팀의 차례가 아닙니다.");
        }
        boardStatus.nextTurn();
    }

    private void checkDeadKing(Piece piece) {
        if (Objects.isNull(piece) && !gameOver) {
            return;
        }
        gameOver = piece.isKing();
    }

    private void putDeadPieces(Piece destinationPiece) {
        if (Objects.nonNull(destinationPiece)) {
            deadPieceByTeam.put(destinationPiece.getTeam()
                    , deadPieceByTeam.get(destinationPiece.getTeam()) + destinationPiece.getScore());
        }
    }

    private void movePieceToPosition(Piece targetPiece, Position destination) {
        if (targetPiece.canPromotion(destination)) {
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

    public void applyStatus() {
        boardStatus.calculateScore(deadPieceByTeam,
                findSameHorizontalTotalPawnCount(Team.BLACK),
                findSameHorizontalTotalPawnCount(Team.WHITE));
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

    public double score(Team team) {
        return boardStatus.score(team);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Team turn() {
        return boardStatus.getLastTurn();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }
}
