package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.AutomatedBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.implementation.piece.King;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class RunningState implements State {

    private ChessGame chessGame;

    public RunningState(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State start(List<String> parameters) {
        if ("new".equals(parameters.get(0))) {
            ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
            return new RunningState(chessGame);
        }
        if ("load".equals(parameters.get(0))) {
            // 게임 불러오기
        }
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State move(MoveParameter moveParameter) {
        chessGame.move(moveParameter);
        if (chessGame.isEnd()) {
            Team winner = chessGame.getBoard().values().stream()
                    .filter(piece -> piece instanceof King)
                    .map(PieceState::getTeam)
                    .findFirst().get();
            return new EndState(chessGame, winner);
        }
        return this;
    }

    @Override
    public State end(List<String> parameters) {
        if ("save".equals(parameters.get(0))) {
            return new ReadyState();
            //게임 저장
        }
        throw new UnsupportedOperationException("임시 - 게임 실행 중 ");
    }

    @Override
    public Map<Position, PieceState> getBoard() {
        return chessGame.getBoard();
    }

    @Override
    public Map<Team, Double> getStatus() {
        return chessGame.getStatus();
    }

    @Override
    public ChessGame getChessGame() {
        return chessGame;
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
