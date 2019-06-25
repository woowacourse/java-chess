package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.*;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final double HALF = 0.5;
    private static final int PAWN_DISTANCE = 2;

    private Map<Position, Piece> boardState;
    private boolean isKingDead = false;

    public Board() {
        this.boardState = BoardCreator.initialize();
    }

    public boolean movable(Position source, Position target, Team team) {
        checkExistPiece(source);
        checkDifferentPiece(source, team);
        checkDifferentTeamTarget(source, target);
        checkCanMove(source, target);

        if(boardState.get(source) instanceof Knight){
            return true;
        }

        checkObstacle(source, target);
        checkPawnMovable(source, target);
        return true;
    }

    private void checkExistPiece(Position source) {
        if (isEmpty(source)) {
            throw new IllegalArgumentException("선택된 위치에 말이 존재하지 않습니다.");
        }
    }

    private void checkDifferentPiece(Position source, Team team) {
        if (!boardState.get(source).isOurPiece(team)) {
            throw new IllegalArgumentException("선택된 위치의 말이 상대편 팀의 말입니다.");
        }
    }

    private void checkDifferentTeamTarget(Position source, Position target) {
        if (isSameTeam(source, target)) {
            throw new IllegalArgumentException("말을 움직이고자 하는 위치에 같은 팀의 말이 존재합니다.");
        }
    }

    private void checkCanMove(Position source, Position target) {
        if (!boardState.get(source).canMove(target)) {
            throw new IllegalArgumentException("선택하신 말이 움직일 수 없는 위치입니다.");
        }
    }

    public void checkObstacle(Position source, Position target) {
        List<Position> route = source.getRoutePosition(target);
        for (Position position : route) {
            checkObstacleAtCurrentPosition(position);
        }
    }

    private void checkObstacleAtCurrentPosition(Position position) {
        if (!(boardState.get(position) instanceof Blank)) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private void checkPawnMovable(Position source, Position target) {
        if (boardState.get(source) instanceof Pawn) {
            checkAccessible(source, target, boardState.get(source), boardState.get(target));
            checkMovable(source, target);
        }
    }

    private void checkAccessible(Position source, Position target, Piece sourcePiece, Piece targetPiece) {
        if (inaccessible(source, target, sourcePiece, targetPiece)) {
            throw new IllegalArgumentException("잡을 수 없는 위치입니다.");
        }
    }

    private boolean inaccessible(Position source, Position target, Piece sourcePiece, Piece targetPiece) {
        return !(targetPiece instanceof Blank
                || sourcePiece.isSameTeamWith(targetPiece)
                || source.getDistanceSquare(target) == PAWN_DISTANCE);
    }

    private void checkMovable(Position source, Position target) {
        if (immovable(source, target)) {
            throw new IllegalArgumentException("말을 움직이고자 하는 위치는 폰이 움직일 수 없는 위치입니다.");
        }
    }


    private boolean immovable(Position source, Position target) {
        return isEmpty(target) && source.getDistanceSquare(target) == PAWN_DISTANCE;
    }

    public void move(Position source, Position target){
        movePiece(source, target);
        boardState.get(target).move(target);
    }

    private void movePiece(Position source, Position target) {
        if (boardState.get(target) instanceof King) {
            isKingDead = true;
        }
        boardState.put(target, boardState.get(source));
        boardState.put(source, new Blank(source, Team.BLANK));
    }

    private boolean isEmpty(Position position) {
        return boardState.get(position) instanceof Blank;
    }

    private boolean isSameTeam(Position source, Position target) {
        Piece sourcePiece = boardState.get(source);
        Piece targetPiece = boardState.get(target);
        return sourcePiece.isSameTeamWith(targetPiece);
    }

    public Piece findPiece(Position position) {
        return boardState.get(position);
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
                .map(position -> boardState.get(position))
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
