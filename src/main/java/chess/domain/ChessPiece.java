package chess.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class ChessPiece {
    private PieceType type;

    protected ChessPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    abstract Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from);

    /**
     * 인자로 명시된 방향으로 상대편, 아군 말 혹은 게임판의 끝까지 탐색
     *
     * @param pieceTeamProvider 게임판 상태 제공자
     * @param from              탐색 기준점
     * @param direction         탐색 방향
     * @return 이동 가능한 좌표쌍의 집합,
     * 탐색 위치의 마지막에 상대편 말이 있는 경우 해당 좌표를 포함하며 아군 말인경우 포함하지 않음.
     */
    Set<CoordinatePair> probeStraight(PieceTeamProvider pieceTeamProvider, CoordinatePair from, Direction direction) {
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Optional<CoordinatePair> maybeNextCoord = direction.move(from);
        while (maybeNextCoord.isPresent() &&
            !checkCell(pieceTeamProvider.getTeamAt(maybeNextCoord.get()), movableCoordinates, maybeNextCoord.get())) {
            maybeNextCoord = direction.move(maybeNextCoord.get());
        }
        return movableCoordinates;
    }

    /**
     * 해당 좌표의 상태를 검사하고 탐색을 계속해야 할지 결정
     *
     * @param team          현재 좌표의 상태
     * @param movableCoords 이동 가능한 경우 좌표를 추가할 Set 객체
     * @param coordToProbe  탐색할 좌표
     * @return 탐색을 멈춰야 하는 경우 true, 아니면 false
     */
    private boolean checkCell(Team team, Set<CoordinatePair> movableCoords, CoordinatePair coordToProbe) {
        if (team.isEmpty()) {
            movableCoords.add(coordToProbe);
            return false;
        }
        if (team.isEnemy(getType().getTeam())) {
            movableCoords.add(coordToProbe);
            return true;
        }
        return team.isAlly(getType().getTeam());
    }
}
