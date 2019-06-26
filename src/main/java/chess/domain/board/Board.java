package chess.domain.board;

import chess.domain.position.Position;
import chess.domain.Team;
import chess.domain.pieces.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final double HALF = 0.5;
    private static final int PAWN_DISTANCE = 2;

    private Map<Position, Piece> boardState;
    private boolean isKingDead;

    public Board() {
        this.boardState = BoardCreator.initialize();
        this.isKingDead = false;
    }

    public boolean movable(Position source, Position target, Team team) {
        checkMyPieceIsEmpty(source);
        checkSourceIsMine(source, team);
        checkDifferentTeamTarget(source, target);
        checkCanMove(source, target);

        if (findPiece(source) instanceof Knight) {
            return true;
        }

        checkObstacle(source, target);
        checkPawnMovable(source, target);

        return true;
    }

    private void checkMyPieceIsEmpty(Position source) {
        if (isEmpty(source)) {
            throw new IllegalArgumentException("선택된 위치에 말이 존재하지 않습니다.");
        }
    }

    private void checkSourceIsMine(Position source, Team team) {
        if (!findPiece(source).isOurPiece(team)) {
            throw new IllegalArgumentException("선택된 위치의 말이 상대편 팀의 말입니다.");
        }
    }

    private void checkDifferentTeamTarget(Position source, Position target) {
        if (isSameTeam(source, target)) {
            throw new IllegalArgumentException("말을 움직이고자 하는 위치에 같은 팀의 말이 존재합니다.");
        }
    }

    private void checkCanMove(Position source, Position target) {
        if (!findPiece(source).canMove(target)) {
            throw new IllegalArgumentException("선택하신 말이 움직일 수 없는 위치입니다.");
        }
    }

    public Piece findPiece(Position position) {
        return boardState.get(position);
    }

    public void checkObstacle(Position source, Position target) {
        List<Position> route = source.getRoutePosition(target);
        for (Position position : route) {
            checkObstacleAtCurrentPosition(position);
        }
    }

    private void checkObstacleAtCurrentPosition(Position position) {
        if (!(findPiece(position) instanceof Blank)) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private void checkPawnMovable(Position source, Position target) {
        if (findPiece(source) instanceof Pawn && !(findPiece(target) instanceof Blank)) {
            checkAccessible(source, target);
        }
    }

    private void checkAccessible(Position source, Position target) {
        if (isCatchForward(source, target)) {
            throw new IllegalArgumentException("정면으로 잡을 수 없습니다.");
        }
        if (!isCatchDiagonally(source, target)) {
            throw new IllegalArgumentException("폰은 대각선으로만 잡을 수 있습니다.");
        }
    }

    private boolean isCatchForward(Position source, Position target) {
        return !findPiece(source).isSameTeamWith(findPiece(target))
                && source.getDistanceSquare(target) == 1;
    }

    private boolean isCatchDiagonally(Position source, Position target) {
        return !(findPiece(source).isSameTeamWith(findPiece(target))
                && source.getDistanceSquare(target) == PAWN_DISTANCE);
    }

    public void move(Position source, Position target) {
        movePiece(source, target);
        findPiece(target).move(target);
    }

    private void movePiece(Position source, Position target) {
        if (findPiece(target) instanceof King) {
            isKingDead = true;
        }
        boardState.put(target, findPiece(source));
        boardState.put(source, new Blank(source, Team.BLANK));
    }

    private boolean isEmpty(Position position) {
        return findPiece(position) instanceof Blank;
    }

    private boolean isSameTeam(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);
        return sourcePiece.isSameTeamWith(targetPiece);
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public double getScore(Team team) {
        List<Piece> sameTeamPieces = getSameTeamPieces(team);

        double totalScore = sameTeamPieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();

        totalScore -= Pawn.getSameColumnSamePawnCount(getSameTeamPawn(sameTeamPieces)) * HALF;

        return totalScore;
    }

    private List<Piece> getSameTeamPieces(Team team) {
        return boardState.keySet().stream()
                .map(this::findPiece)
                .filter(piece -> piece.isOurPiece(team))
                .collect(Collectors.toList());
    }

    private List<Pawn> getSameTeamPawn(List<Piece> sameTeamPieces) {
        return sameTeamPieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .map(piece -> (Pawn) piece)
                .collect(Collectors.toList());
    }
}
