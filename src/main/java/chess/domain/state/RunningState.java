package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.implementation.piece.King;
import chess.domain.player.Team;

public class RunningState implements State {

    private Board board;
    private Turn turn;

    public RunningState(Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public State start(String param) {
        if ("new".equals(param)) {
            return new RunningState(Board.of(new EnumRepositoryBoardInitializer()), Turn.from(Team.WHITE));
        }
        if ("load".equals(param)) {
            // 게임 불러오기
        }
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter) {
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
    public State end(String param) {
        if ("save".equals(param)) {
            //게임 저장
        }
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
