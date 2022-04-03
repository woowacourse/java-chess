package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {
    private static final int MAX_BOARD_INDEX = 8;
    private static final int MIN_BOARD_INDEX = 1;
    private static final int PAWN_PENALTY_CONDITION = 1;
    private static final double PAWN_SAME_COLUMN_PENALTY = 0.5;
    private static final String ENEMY_CHESS_PIECE_SELECTED_EXCEPTION = "[ERROR] 적 체스 기물을 골랐습니다.";
    private static final String OBSTACLE_IN_PATH_EXCEPTION = "[ERROR] 장애물이 경로에 존재합니다.";
    private static final String UNVALID_SOURCE_POSITION_EXCEPTION = "[ERROR] sourcePosition에 체스 기물이 없습니다.";
    private static final String SAME_TEAM_EXIST_IN_TARGET_POSITION_EXCEPTION = "[ERROR] targetPosition에 같은 팀 체스 기물이 있습니다.";
    private static final String IMPOSSIBLE_TO_KILL_EXCEPTION = "[ERROR] 잡을 수 없는 위치에 있는 말입니다.";
    private final Map<ChessBoardPosition, ChessPiece> mapInformation;
    private Team turn;

    private ChessBoard(final Map<ChessBoardPosition, ChessPiece> mapInformation) {
        this.mapInformation = mapInformation;
        turn = Team.WHITE;
    }

    public static ChessBoard initialize() {
        Map<ChessBoardPosition, ChessPiece> mapInformation = new HashMap<>();
        initializeTeam(mapInformation, Team.BLACK);
        initializeTeam(mapInformation, Team.WHITE);
        return new ChessBoard(mapInformation);
    }

    private static void initializeTeam(Map<ChessBoardPosition, ChessPiece> mapInformation, Team team) {
        addChessPieces(mapInformation, Pawn.create(team));
        addChessPieces(mapInformation, Knight.create(team));
        addChessPieces(mapInformation, Bishop.create(team));
        addChessPieces(mapInformation, Rook.create(team));
        addChessPieces(mapInformation, Queen.create(team));
        addChessPieces(mapInformation, King.create(team));
    }

    private static void addChessPieces(
            Map<ChessBoardPosition, ChessPiece> mapInformation, Map<ChessBoardPosition, ChessPiece> chessPieces) {
        mapInformation.putAll(chessPieces);
    }

    public Map<ChessBoardPosition, ChessPiece> getMapInformation() {
        return mapInformation;
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = pickChessPiece(sourcePosition);
        isSameTeamChessPiece(chessPiece);
        isObstacleExist(chessPiece, sourcePosition, targetPosition);
        isTargetPositionOccupiedBySameTeam(targetPosition);
        killEnemyInTargetPositionIfExist(sourcePosition, targetPosition);
        moveChessPiece(chessPiece, sourcePosition, targetPosition);
        nextTurn();
    }

    ChessPiece pickChessPiece(ChessBoardPosition sourcePosition) {
        return mapInformation.keySet()
                .stream()
                .filter(it -> it.equals(sourcePosition))
                .findFirst()
                .map(mapInformation::get)
                .orElse(null);
    }

    private void isSameTeamChessPiece(ChessPiece chessPiece) {
        if (Objects.isNull(chessPiece)) {
            throw new IllegalArgumentException(UNVALID_SOURCE_POSITION_EXCEPTION);
        }
        if (!chessPiece.isSameTeam(turn)) {
            throw new IllegalArgumentException(ENEMY_CHESS_PIECE_SELECTED_EXCEPTION);
        }
    }

    private void isObstacleExist(ChessPiece chessPiece, ChessBoardPosition sourcePosition,
                                 ChessBoardPosition targetPosition) {
        List<ChessBoardPosition> path = chessPiece.getPath(sourcePosition, targetPosition);
        for (ChessBoardPosition chessBoardPosition : path) {
            isBoardSpaceEmpty(chessBoardPosition);
        }
    }

    private void isBoardSpaceEmpty(ChessBoardPosition chessBoardPosition) {
        if (mapInformation.containsKey(chessBoardPosition)) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_EXCEPTION);
        }
    }

    private void isTargetPositionOccupiedBySameTeam(ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = pickChessPiece(targetPosition);
        if (Objects.nonNull(chessPiece) && chessPiece.isSameTeam(turn)) {
            throw new IllegalArgumentException(SAME_TEAM_EXIST_IN_TARGET_POSITION_EXCEPTION);
        }
    }

    private void killEnemyInTargetPositionIfExist(ChessBoardPosition sourcePosition,
                                                  ChessBoardPosition targetPosition) {
        ChessPiece enemyChessPiece = pickChessPiece(targetPosition);
        if (Objects.isNull(enemyChessPiece)) {
            return;
        }
        ChessPiece chessPiece = pickChessPiece(sourcePosition);
        if (!chessPiece.isKillMovement(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException(IMPOSSIBLE_TO_KILL_EXCEPTION);
        }
        mapInformation.remove(targetPosition, chessPiece);
    }

    private void moveChessPiece(ChessPiece chessPiece, ChessBoardPosition sourcePosition,
                                ChessBoardPosition targetPosition) {
        mapInformation.remove(sourcePosition);
        mapInformation.put(targetPosition, chessPiece);
    }

    private void nextTurn() {
        if (turn.isSame(Team.BLACK)) {
            turn = Team.WHITE;
            return;
        }
        turn = Team.BLACK;
    }

    public boolean isKingDie() {
        int kingNumber = (int) mapInformation.keySet()
                .stream()
                .map(mapInformation::get)
                .filter(King.class::isInstance)
                .count();
        return kingNumber != 2;
    }

    public Double calculateScore(Team team) {
        Map<ChessBoardPosition, ChessPiece> specificTeamInformation = extractSpecificTeamInformation(team);
        return calculateOptimizeScore(specificTeamInformation);
    }

    private Map<ChessBoardPosition, ChessPiece> extractSpecificTeamInformation(Team team) {
        return mapInformation.entrySet()
                .stream()
                .filter(it -> it.getValue().isSameTeam(team))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Double calculateOptimizeScore(Map<ChessBoardPosition, ChessPiece> specificTeamInformation) {
        double score = specificTeamInformation.values()
                .stream()
                .mapToDouble(ChessPiece::score)
                .sum();
        return score - calculatePawnPenalty(specificTeamInformation);
    }

    private Double calculatePawnPenalty(Map<ChessBoardPosition, ChessPiece> specificTeamInformation) {
        return countPenaltyPawn(specificTeamInformation) * PAWN_SAME_COLUMN_PENALTY;
    }

    private int countPenaltyPawn(Map<ChessBoardPosition, ChessPiece> specificTeamInformation) {
        return countPawnInSameColumn(specificTeamInformation)
                .stream()
                .filter(it -> it > PAWN_PENALTY_CONDITION)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> countPawnInSameColumn(Map<ChessBoardPosition, ChessPiece> specificTeamInformation) {
        List<Integer> pawnColumns = collectPawnColumns(specificTeamInformation);
        return IntStream.rangeClosed(MIN_BOARD_INDEX, MAX_BOARD_INDEX)
                .boxed()
                .map(it -> Collections.frequency(pawnColumns, it))
                .collect(Collectors.toList());
    }

    private List<Integer> collectPawnColumns(Map<ChessBoardPosition, ChessPiece> specificTeamInformation) {
        return specificTeamInformation.entrySet()
                .stream()
                .filter(it -> it.getValue() instanceof Pawn)
                .map(it -> it.getKey().getColumn())
                .collect(Collectors.toList());
    }

    public Team judgeWinner() {
        double whiteTeamScore = calculateScore(Team.WHITE);
        double blackTeamScore = calculateScore(Team.BLACK);
        if (whiteTeamScore > blackTeamScore) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
