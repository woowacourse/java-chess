package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.implementation.piece.King;
import chess.domain.player.Team;

public class RunningState implements State {

    private Board board;

    public RunningState(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter, Turn turn) {
        board.move(moveParameter.getSource(), moveParameter.getTarget(), turn);
        if (board.isEnd()) {
            Team winner = board.getBoard().values().stream()
                    .filter(piece -> piece instanceof King)
                    .map(PieceState::getTeam)
                    .findFirst().get();
            return new EndState(board, winner);
        }
        return this;
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("임시 - 게임 실행 중 ");
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public double getPoints(Team team) {
        return board.getScores(team);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Team getWinner() {
        throw new UnsupportedOperationException("게임이 아직 끝나지 않았습니다.");
    }
}
