package chess.service;

import chess.controller.dto.PieceDto;
import chess.controller.dto.ResponseDto;
import chess.dao.ChessDAO;
import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.AutomatedBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private ChessDAO chessDAO = new ChessDAO();
    private Map<Long, ChessGame> chessGames = new HashMap<>();

    public Long createGame() throws SQLException {
        ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
        Long id = chessDAO.createChessGame(chessGame);
        chessGames.put(id, chessGame);
        return id;
    }

    public void start(Long id, List<String> parameters) throws SQLException {
        if ("new".equals(parameters.get(0))) {
            ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
            chessDAO.addBoard(id, chessGame);
            chessGames.put(id, chessGame);
        }
        if ("load".equals(parameters.get(0))) {
            ChessGame chessGame = chessDAO.findGameById(id);
            chessGames.put(id, chessGame);
            System.out.println("load" + chessGames.size());
        }
    }

    public void end(Long id, List<String> parameters) throws SQLException {
        if ("save".equals(parameters.get(0))) {
            System.out.println("save" + chessGames.size());
            chessDAO.addBoard(id, chessGames.get(id));
        }
        if ("".equals(parameters.get(0))) {
            deleteGame(id);
        }
        chessGames.remove(id);
    }

    public void move(Long id, List<String> parameters) {
        chessGames.get(id).move(MoveParameter.of(parameters));
    }

    public void deleteGame(long id) throws SQLException {
        chessDAO.deleteGame(id);
    }

    public ResponseDto getResponseDto(Long id) {
        Map<Position, PieceDto> board = createBoardDto(id);
        Map<Team, Double> scoreDto = createScoreDto(id);
        Team turnDto = createTurnDto(id);
        if (isEnd(id)) {
            Team winner = getWinner(id);
            String message = winner.toString() + "가 승리했습니다.";
            return new ResponseDto(board, scoreDto, turnDto, winner, message);
        }
        return new ResponseDto(board, scoreDto, turnDto);
    }


    public Map<Position, PieceDto> createBoardDto(Long id) {
        return chessGames.get(id)
                .getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> PieceDto.of(entry.getValue())
                ));
    }

    public Map<Team, Double> createScoreDto(Long id) {
        return chessGames.get(id).getStatus();
    }

    public boolean isEnd(Long id) {
        return chessGames.get(id).isEnd();
    }

    public Team createTurnDto(Long id) {
        return chessGames.get(id).getTurn();
    }

    public Team getWinner(Long id) {
        return chessGames.get(id).getWinner();
    }

    public List<Long> getRoomId() throws SQLException {
        return chessDAO.getRoomId();

    }

    public void load(final Long id) throws SQLException {
        ChessGame chessGame = chessDAO.findGameById(id);
        chessGames.put(id, chessGame);
    }

    public void restart(final Long id) {
        ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
        chessGames.put(id, chessGame);
    }
}
