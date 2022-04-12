package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import java.util.Collections;
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
    private final Map<ChessBoardPosition, ChessPiece> boardData;
    private Team turn;

    private ChessBoard(Team turn, final Map<ChessBoardPosition, ChessPiece> boardData) {
        this.turn = turn;
        this.boardData = boardData;
    }

    public static ChessBoard initialize(Team turn, Map<ChessBoardPosition, ChessPiece> boardData) {
        return new ChessBoard(turn, boardData);
    }

    public Map<ChessBoardPosition, ChessPiece> getBoardData() {
        return boardData;
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
        return boardData.keySet()
                .stream()
                .filter(it -> it.equals(sourcePosition))
                .findFirst()
                .map(boardData::get)
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
        if (boardData.containsKey(chessBoardPosition)) {
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
        boardData.remove(targetPosition, chessPiece);
    }

    private void moveChessPiece(ChessPiece chessPiece, ChessBoardPosition sourcePosition,
                                ChessBoardPosition targetPosition) {
        boardData.remove(sourcePosition);
        boardData.put(targetPosition, chessPiece);
    }

    private void nextTurn() {
        if (turn.isSame(Team.BLACK)) {
            turn = Team.WHITE;
            return;
        }
        turn = Team.BLACK;
    }

    public boolean isKingDie() {
        int kingNumber = (int) boardData.keySet()
                .stream()
                .map(boardData::get)
                .filter(King.class::isInstance)
                .count();
        return kingNumber != 2;
    }

    public Double calculateScore(Team team) {
        Map<ChessBoardPosition, ChessPiece> specificTeamBoardData = extractSpecificTeamBoardData(team);
        return calculateOptimizeScore(specificTeamBoardData);
    }

    private Map<ChessBoardPosition, ChessPiece> extractSpecificTeamBoardData(Team team) {
        return boardData.entrySet()
                .stream()
                .filter(it -> it.getValue().isSameTeam(team))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Double calculateOptimizeScore(Map<ChessBoardPosition, ChessPiece> specificTeamBoardData) {
        double score = specificTeamBoardData.values()
                .stream()
                .mapToDouble(ChessPiece::score)
                .sum();
        return score - calculatePawnPenalty(specificTeamBoardData);
    }

    private Double calculatePawnPenalty(Map<ChessBoardPosition, ChessPiece> specificTeamBoardData) {
        return countPenaltyPawn(specificTeamBoardData) * PAWN_SAME_COLUMN_PENALTY;
    }

    private int countPenaltyPawn(Map<ChessBoardPosition, ChessPiece> specificTeamBoardData) {
        return countPawnInSameColumn(specificTeamBoardData)
                .stream()
                .filter(it -> it > PAWN_PENALTY_CONDITION)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> countPawnInSameColumn(Map<ChessBoardPosition, ChessPiece> specificTeamBoardData) {
        List<Integer> pawnColumns = collectPawnColumns(specificTeamBoardData);
        return IntStream.rangeClosed(MIN_BOARD_INDEX, MAX_BOARD_INDEX)
                .boxed()
                .map(it -> Collections.frequency(pawnColumns, it))
                .collect(Collectors.toList());
    }

    private List<Integer> collectPawnColumns(Map<ChessBoardPosition, ChessPiece> specificTeamBoardData) {
        return specificTeamBoardData.entrySet()
                .stream()
                .filter(it -> it.getValue() instanceof Pawn)
                .map(it -> it.getKey().getColumn())
                .collect(Collectors.toList());
    }

    public Team getTurn() {
        return turn;
    }
}
