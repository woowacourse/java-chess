package domain.game.state;

import domain.game.Board;
import domain.game.MoveResponse;
import domain.game.TeamColor;
import domain.position.Position;

public abstract sealed class GamePlaying implements GameState permits WhiteTurn, BlackTurn {
    @Override
    public boolean isContinuable() {
        return true;
    }

    @Override
    public boolean isTurnOf(TeamColor teamColor) {
        return teamColor.equals(currentTurn());
    }

    @Override
    public MoveResponse move(Board board, Position source, Position destination) {
        return board.movePiece(currentTurn(), source, destination);
    }

    @Override
    public TeamColor getWinner() {
        throw new IllegalStateException("아직 게임이 진행중입니다.");
    }
}
