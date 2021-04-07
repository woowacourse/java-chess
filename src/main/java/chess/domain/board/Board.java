package chess.domain.board;


import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;

import java.util.*;

public class Board {
    public static final int MIN_BORDER = 1;
    public static final int MAX_BORDER = 8;
    public static final int PAWN_COUNT_THRESHOLD = 2;
    private static final double TOTAL_SCORE = 38;
    private static final double PAWN_SAME_HORIZONTAL_SCORE = 0.5;
    private final Map<Position, Piece> board;
    private final Map<Team, Double> lostScoreByTeam;

    public Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
        this.lostScoreByTeam = initializeDeadPieceByTeamMap();
    }

    private Map<Team, Double> initializeDeadPieceByTeamMap() {
        Map<Team, Double> result = new EnumMap<>(Team.class);
        result.put(Team.BLACK, 0d);
        result.put(Team.WHITE, 0d);
        return result;
    }

    public Piece findPieceFromPosition(Position position) {
        return board.get(position);
    }

    public Team movePiece(Position target, Position destination, Team turnOwner) {
        Piece targetPiece = findPieceFromPosition(target);
        checkTargetPieceNull(targetPiece);
        checkTeamToPlayTurn(targetPiece, turnOwner);
        checkMovable(targetPiece.searchMovablePositions(target), destination);

        return movePieceIfPossible(targetPiece, target, destination);
    }

    private Team movePieceIfPossible(Piece targetPiece, Position target, Position destination) {
        if (targetPiece.canMove(target, destination, this)) {
            Piece destinationPiece = findPieceFromPosition(destination);
            loseScoreWhenDestinationIsPiece(destinationPiece);

            movePieceToPosition(targetPiece, destination);
            clearPosition(target);

            return targetPiece.getNextTurnOwner();
        }
        throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
    }

    private void checkTargetPieceNull(Piece piece) {
        if (Objects.isNull(piece)) {
            throw new IllegalArgumentException("입력한 시작 좌표에 기물이 존재하지 않습니다.");
        }
    }

    private void checkTeamToPlayTurn(Piece targetPiece, Team turnOwner) {
        if (targetPiece.isSameTeam(turnOwner)) {
            return;
        }
        throw new IllegalArgumentException("해당 팀의 순서가 아닙니다.");
    }

    private void loseScoreWhenDestinationIsPiece(Piece destinationPiece) {
        if (Objects.nonNull(destinationPiece)) {
            lostScoreByTeam.put(destinationPiece.getTeam()
                    , lostScoreByTeam.get(destinationPiece.getTeam()) + destinationPiece.getScore());
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

    public double calculateScore(Team team) {
        double defaultScore = TOTAL_SCORE - lostScoreByTeam.get(team);
        return defaultScore - countOfSameLinePawn(team) * PAWN_SAME_HORIZONTAL_SCORE;
    }

    private int countOfSameLinePawn(Team team) {
        int result = 0;
        for (Horizontal horizontal : Horizontal.values()) {
            int cnt = calculateSameLinePawnCount(team, horizontal);
            result = plusCountWhenSameLinePawnIsMoreThanTwo(result, cnt);
        }
        return result;
    }

    private int plusCountWhenSameLinePawnIsMoreThanTwo(int result, int cnt) {
        if (cnt >= PAWN_COUNT_THRESHOLD) {
            result += cnt;
        }
        return result;
    }

    private int calculateSameLinePawnCount(Team team, Horizontal horizontal) {
        int cnt = 0;
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            cnt = calculateEachHorizontalTotalPawnCount(team, horizontal, cnt, position, piece);
        }
        return cnt;
    }

    private int calculateEachHorizontalTotalPawnCount(Team team, Horizontal horizontal, int cnt, Position position, Piece piece) {
        if (horizontal.isSameHorizontal(position) && piece instanceof Pawn && piece.isSameTeam(team)) {
            cnt++;
        }
        return cnt;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public boolean isAnyKingDead() {
        int cnt = 0;
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            if (Objects.nonNull(entry.getValue()) && entry.getValue().isKing()) {
                cnt += 1;
            }
        }
        return cnt != 2;
    }
}
