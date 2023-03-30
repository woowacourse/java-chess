package chess.domain.winningstatus;

import chess.domain.piece.Team;

import java.util.Arrays;

public enum GameResult {
    WHITE_WIN(Team.WHITE),
    BLACK_WIN(Team.BLACK),
    DRAW(null);

    private final Team winner;

    GameResult(final Team winner) {
        this.winner = winner;
    }

    static GameResult getResultByWinner(final Team winner) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> winner.equals(gameResult.winner))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팀입니다."));
    }

    public Team getWinner() {
        return winner;
    }
}
