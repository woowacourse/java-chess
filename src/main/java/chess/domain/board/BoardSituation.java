package chess.domain.board;

import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class BoardSituation {

    private static final int DUPLICATE_THRESHOLD = 1;
    private Map<Position, Team> boardState;

    private BoardSituation(Map<Position, Team> boardState) {
        this.boardState = boardState;
    }

    public static BoardSituation of(Map<Position, Team> boardState) {
        return new BoardSituation(boardState);
    }

    public boolean canMove(Position target) {
        Team team = boardState.get(target);
        return Objects.isNull(team);
    }

    public boolean canAttack(Position target, chess.domain.player.Team team) {
        Team targetTeam = boardState.get(target);
        return !Objects.isNull(targetTeam) && !team.isSameTeam(targetTeam);
    }

    public boolean existSamePieceInSameFile(Position position, Team team) {
        return boardState.entrySet()
                .stream()
                .filter(entry -> team.isSameTeam(entry.getValue()))
                .filter(entry -> position.isSameFile(entry.getKey()))
                .count() > DUPLICATE_THRESHOLD;
    }
}
