package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessBoard {
    private static final String ENEMY_CHESS_PIECE_SELECTED_EXCEPTION = "[ERROR] 적 체스 기물을 골랐습니다.";
    private static final String OBSTACLE_IN_PATH_EXCEPTION = "[ERROR] 장애물이 경로에 존재합니다.";
    private static final String UNVALID_SOURCE_POSITION_EXCEPTION = "[ERROR] sourcePosition에 체스 기물이 없습니다.";
    private static final String SAME_TEAM_EXIST_IN_TARGET_POSITION_EXCEPTION = "[ERROR] targetPosition에 같은 팀 체스 기물이 있습니다.";
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
        isChessPieceSameTeam(chessPiece);
        isObstacleExist(chessPiece, sourcePosition, targetPosition);
        isTargetPositionOccupiedBySameTeam(targetPosition);
        killEnemyInTargetPositionIfExist(targetPosition);
        moveChessPiece(chessPiece, sourcePosition, targetPosition);
        nextTurn();
    }

    private void nextTurn() {
        if (turn.isSame(Team.BLACK)) {
            turn = Team.WHITE;
            return;
        }
        turn = Team.BLACK;
    }

    private void moveChessPiece(ChessPiece chessPiece, ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        mapInformation.remove(sourcePosition);
        mapInformation.put(targetPosition, chessPiece);
    }

    private void killEnemyInTargetPositionIfExist(ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = pickChessPiece(targetPosition);
        if (Objects.isNull(chessPiece)) {
            return;
        }
        mapInformation.remove(targetPosition, chessPiece);
    }

    private void isChessPieceSameTeam(ChessPiece chessPiece) {
        if (Objects.isNull(chessPiece)) {
            throw new IllegalArgumentException(UNVALID_SOURCE_POSITION_EXCEPTION);
        }
        if (!chessPiece.isSameTeam(turn)) {
            throw new IllegalArgumentException(ENEMY_CHESS_PIECE_SELECTED_EXCEPTION);
        }
    }

    private void isTargetPositionOccupiedBySameTeam(ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = pickChessPiece(targetPosition);
        if (Objects.nonNull(chessPiece) && chessPiece.isSameTeam(turn)) {
            throw new IllegalArgumentException(SAME_TEAM_EXIST_IN_TARGET_POSITION_EXCEPTION);
        }

    }

    private void isObstacleExist(ChessPiece chessPiece, ChessBoardPosition sourcePosition,
                                 ChessBoardPosition targetPosition) {
        List<ChessBoardPosition> path = chessPiece.getPath(sourcePosition, targetPosition);
        for (ChessBoardPosition chessBoardPosition : path) {
            isEmptyBoardSpace(chessBoardPosition);
        }
    }

    private void isEmptyBoardSpace(ChessBoardPosition chessBoardPosition) {
        if (mapInformation.containsKey(chessBoardPosition)) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_EXCEPTION);
        }
    }

    ChessPiece pickChessPiece(ChessBoardPosition sourcePosition) {
        return mapInformation.keySet()
                .stream()
                .filter(it -> it.equals(sourcePosition))
                .findFirst()
                .map(mapInformation::get)
                .orElse(null);
    }

    public boolean isKingDie() {
        int kingNumber = (int) mapInformation.keySet()
                .stream()
                .map(mapInformation::get)
                .filter(King.class::isInstance)
                .count();
        return kingNumber != 2;
    }
}
