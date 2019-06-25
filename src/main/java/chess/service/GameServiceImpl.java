package chess.service;

import chess.domain.*;
import chess.domain.boardcell.CellFactory;
import chess.domain.boardcell.ChessPiece;
import chess.domain.boardcell.PieceType;
import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.GameSessionDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.GameSessionDto;
import chess.service.dto.CoordinatePairDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameServiceImpl implements GameService {
    private GameSessionDao sessionDao;
    private BoardStateDao boardStateDao;

    public GameServiceImpl() {
        DataSource ds = DataSourceFactory.getInstance().createDataSource();
        sessionDao = new GameSessionDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    @Override
    public List<BoardStateDto> findBoardStatesBySessionId(long sessionId) {
        try {
            return boardStateDao.findBySessionId(sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public GameResult movePiece(CoordinatePair from, CoordinatePair to, long sessionId) {
        try {
            ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
            game.move(from, to);

            deleteTargetStateIfPresent(to, sessionId);
            updateSrcState(from, to, sessionId);
            GameResult result = GameResult.judge(game.getBoardState().values());
            GameSessionDto sess = sessionDao.findById(sessionId).orElseThrow(() -> new IllegalStateException("결과를 반영할 세션을 찾을 수 없습니다."));
            sess.setState(result.name());
            sessionDao.updateSession(sess);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private LivingPieceGroup findBoardStatesMapBySessionId(long roomId) {
        try {
            Map<CoordinatePair, ChessPiece> board = new HashMap<>();

            boardStateDao.findBySessionId(roomId)
                .forEach(dto ->
                    board.put(CoordinatePair.of(dto.getCoordX() + dto.getCoordY()).get(),
                        CellFactory.create(PieceType.valueOf(dto.getType()))));
            return LivingPieceGroup.of(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private void deleteTargetStateIfPresent(CoordinatePair to, long roomId) throws SQLException {
        boardStateDao.findByRoomIdAndCoordinate(roomId, to.getX().getSymbol(), to.getY().getSymbol())
            .ifPresent(dto -> tryDeleteBoardStateById(dto.getId()));
    }

    private void tryDeleteBoardStateById(long id) {
        try {
            boardStateDao.deleteById(id);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private void updateSrcState(CoordinatePair from, CoordinatePair to, long roomId) throws SQLException {
        boardStateDao.findByRoomIdAndCoordinate(roomId, from.getX().getSymbol(), from.getY().getSymbol())
            .ifPresent(dto -> {
                dto.setCoordX(to.getX().getSymbol());
                dto.setCoordY(to.getY().getSymbol());
                tryUpdateBoardState(dto);
            });
    }

    private void tryUpdateBoardState(BoardStateDto dto) {
        try {
            boardStateDao.updateCoordById(dto);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public List<CoordinatePairDto> findMovableCoordinates(long sessionId, CoordinatePair from) {
        LivingPieceGroup state = findBoardStatesMapBySessionId(sessionId);
        return state.at(from).getMovableCoordinates(coord -> state.at(coord).getType().getTeam(), from).stream()
            .map(coord -> {
                CoordinatePairDto dto = new CoordinatePairDto();
                dto.setX(coord.getX().getSymbol());
                dto.setY(coord.getY().getSymbol());
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public ScoreCounter calculateScore(long sessionId) {
        ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
        return new ScoreCounter(game.getBoardState());
    }

    @Override
    public GameResult judgeResult(long sessionId) {
        ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
        return GameResult.judgeScore(game.getBoardState());
    }
}
