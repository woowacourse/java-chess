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
import java.util.Optional;

public class ChessService {
    private ChessDAO chessDAO = new ChessDAO();
    private Map<Long, ChessGame> chessGames = new HashMap<>();

    public Long createGame() {
        ChessGame chessGame = ChessGame.of(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
        Long id = chessDAO.createChessGame(chessGame);
        chessGames.put(id, chessGame);
        return id;
    }

    public void restart(final Long id) {
        ChessGame chessGame = ChessGame.of(Board.of(new AutomatedBoardInitializer()), Turn.from(Team.WHITE));
        chessGames.put(id, chessGame);
    }

    public void load(final Long id) {
        ChessGame chessGame = findChessGame(id);
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

    public void move(final Long id, final List<String> parameters) {
        loadIfNotExisting(id);
        ChessGame chessGame = chessGames.get(id);
        chessGame.move(MoveParameter.of(parameters));
    }

    public List<Position> getMovablePositions(final Long id, final Position source) {
        loadIfNotExisting(id);
        ChessGame chessGame = chessGames.get(id);
        return chessGame.getMovablePositions(source);
    }

    public ResponseDto getResponseDto(final Long id) {
        if (!chessGames.containsKey(id)) {
            ChessGame chessGame = findChessGame(id);
            return ResponseDto.of(chessGame);
        }
        return ResponseDto.of(chessGames.get(id));
    }

    public List<Long> getRoomId() {
        return chessDAO.getRoomId();
    }

    private ChessGame findChessGame(final Long id) {
        Optional<ChessGame> chessGameOptional = chessDAO.findGameById(id);
        return chessGameOptional.orElseThrow(() -> new IllegalArgumentException("잘못된 게임 번호입니다."));
    }

    private void loadIfNotExisting(final Long id) {
        if (!chessGames.containsKey(id)) {
            load(id);
        }
    }
}
