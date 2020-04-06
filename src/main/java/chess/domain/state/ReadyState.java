package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class ReadyState implements State {

    @Override
    public State start(List<String> parameters) {
        if ("new".equals(parameters.get(0))) {
            ChessGame chessGame = new ChessGame(Board.of(new EnumRepositoryBoardInitializer()), Turn.from(Team.WHITE));
            return new RunningState(chessGame);
        }
        if ("load".equals(parameters.get(0))) {
            // 게임 불러오기
        }
        throw new IllegalArgumentException("잘못된 시작입니다.");
    }

    @Override
    public State move(MoveParameter moveParameter) {
        throw new UnsupportedOperationException("게임이 시작되지 않았습니다.");
    }

    @Override
    public State end(List<String> parameters) {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public Map<Position, PieceState> getBoard() {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public Map<Team, Double> getStatus() {
        throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
    }

    @Override
    public ChessGame getChessGame() {
        throw new UnsupportedOperationException("게임이 아직 시작되지 않았습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Team getWinner() {
        throw new UnsupportedOperationException("게임이 아직 시작되지 않았습니다.");
    }
}
