package chess.service;

import chess.controller.dto.PieceDto;
import chess.dao.ChessDAO;
import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.AutomatedBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.domain.state.ReadyState;
import chess.domain.state.RunningState;
import chess.domain.state.State;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private ChessDAO chessDAO = new ChessDAO();
    private State state = new ReadyState();
    private long id;

    public boolean isEnd() {
        return state.isEnd();
    }

    public void start(List<String> parameters) throws SQLException {
        if ("new".equals(parameters.get(0))) {
            ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
            this.id = chessDAO.createChessGame(chessGame);
            state = new RunningState(chessGame);
        }
        if ("load".equals(parameters.get(0))) {
            this.id = Long.valueOf(parameters.get(1));
            ChessGame chessGame = chessDAO.findGameById(this.id);
            state = new RunningState(chessGame);
        }
    }

    public void end(List<String> parameters) throws SQLException {
        if ("save".equals(parameters.get(0))) {
            chessDAO.addBoard(id, state.getChessGame());
        }
        if ("".equals(parameters.get(0))) {
            chessDAO.deleteGame(id);
        }
        state = state.end(parameters);
    }

    public void move(List<String> parameters) {
        state = state.move(MoveParameter.of(parameters));
    }

    public Map<Position, PieceDto> createBoardDto() {
        return state.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> PieceDto.of(entry.getValue())
                ));
    }

    public Map<Team, Double> createScoreDto() {
        return state.getStatus();
    }

    public Team createTurnDto() {
        return state.getChessGame().getTurn();
    }

    public Team getWinner() {
        return state.getWinner();
    }

    public boolean isReady() {
        return state instanceof ReadyState;
    }

    public List<Long> getRoomId() throws SQLException {
        return chessDAO.getRoomId();

    }
}
