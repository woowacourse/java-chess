package chess.service;

import chess.controller.dto.ResponseDto;
import chess.dao.ChessDAO;
import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.AutomatedBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {
    private ChessDAO chessDAO = new ChessDAO();
    private Map<Long, ChessGame> chessGames = new HashMap<>();

    public Long createGame() {
        ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
        Long id = chessDAO.createChessGame(chessGame);
        chessGames.put(id, chessGame);
        return id;
    }

    public void restart(final Long id) {
        ChessGame chessGame = new ChessGame(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
        chessGames.put(id, chessGame);
    }

    public void load(final Long id) {
        ChessGame chessGame = chessDAO.findGameById(id);
        chessGames.put(id, chessGame);
    }

    public void save(final Long id) {
        chessDAO.addBoard(id, chessGames.get(id));
        chessGames.remove(id);
    }

    public void remove(final Long id) {
        chessDAO.deleteGame(id);
        chessGames.remove(id);
    }

    public void move(Long id, List<String> parameters) {
        chessGames.get(id).move(MoveParameter.of(parameters));
    }

    public List<Position> getMovablePositions(Long id, Position source) {
        return chessGames.get(id).getMovablePositions(source);
    }

    public ResponseDto getResponseDto(Long id) {
        return ResponseDto.of(chessGames.get(id));
    }

    public List<Long> getRoomId() {
        return chessDAO.getRoomId();
    }
}
