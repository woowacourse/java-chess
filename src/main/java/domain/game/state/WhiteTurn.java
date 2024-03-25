package domain.game.state;

import domain.game.Board;
import domain.game.MoveResponse;
import domain.game.TeamColor;
import domain.position.Position;

public class WhiteTurn implements GameState {
    @Override
    public boolean isContinuable() {
        return true;
    }

    @Override
    public boolean isTurnOf(TeamColor teamColor) {
        return teamColor.equals(TeamColor.WHITE);
    }

    @Override
    public TeamColor currentTurn() {
        return TeamColor.WHITE;
    }

    @Override
    public MoveResponse move(Board board, Position source, Position destination) {
        return board.movePiece(TeamColor.WHITE, source, destination);
    }

    @Override
    public TeamColor getWinner() {
        throw new IllegalStateException("아직 게임이 진행중입니다.");
    }
}
