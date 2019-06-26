package chess.service;

import chess.domain.*;
import chess.domain.boardcell.ChessPiece;
import chess.domain.boardcell.ChessPieceFactory;
import chess.domain.boardcell.PieceType;
import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.GameSessionDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.GameSessionDto;
import chess.service.dto.CoordinatePairDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameService {
    private GameSessionDao sessionDao;
    private BoardStateDao boardStateDao;

    public GameService() {
        DataSource ds = DataSourceFactory.getInstance().createDataSource();
        sessionDao = new GameSessionDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    public List<BoardStateDto> findBoardStatesBySessionId(long sessionId) {
        return boardStateDao.findBySessionId(sessionId);
    }

    public GameResult movePiece(CoordinatePair from, CoordinatePair to, long sessionId) {
        ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
        game.move(from, to);

        deleteTargetStateIfPresent(to, sessionId);
        updateSrcState(from, to, sessionId);
        GameResult result = GameResult.judge(game.getBoardState().values());
        GameSessionDto sess = sessionDao.findById(sessionId).orElseThrow(() -> new IllegalStateException("결과를 반영할 세션을 찾을 수 없습니다."));
        sess.setState(result.name());
        sessionDao.updateSession(sess);
        return result;
    }

    private LivingPieceGroup findBoardStatesMapBySessionId(long roomId) {
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();

        boardStateDao.findBySessionId(roomId)
            .forEach(dto ->
                board.put(CoordinatePair.of(dto.getCoordX() + dto.getCoordY()).get(),
                    ChessPieceFactory.create(PieceType.valueOf(dto.getType()))));
        return LivingPieceGroup.of(board);
    }

    private void deleteTargetStateIfPresent(CoordinatePair to, long roomId) {
        boardStateDao.findByRoomIdAndCoordinate(roomId, to.getXSymbol(), to.getYSymbol())
            .ifPresent(dto -> tryDeleteBoardStateById(dto.getId()));
    }

    private void tryDeleteBoardStateById(long id) {
        boardStateDao.deleteById(id);
    }

    private void updateSrcState(CoordinatePair from, CoordinatePair to, long roomId) {
        boardStateDao.findByRoomIdAndCoordinate(roomId, from.getXSymbol(), from.getYSymbol())
            .ifPresent(dto -> {
                dto.setCoordX(to.getXSymbol());
                dto.setCoordY(to.getYSymbol());
                tryUpdateBoardState(dto);
            });
    }

    private void tryUpdateBoardState(BoardStateDto dto) {
        boardStateDao.updateCoordById(dto);
    }

    public List<CoordinatePairDto> findMovableCoordinates(long sessionId, CoordinatePair from) {
        ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
        return game.getMovableCoordinates(from).stream()
            .map(coord -> {
                CoordinatePairDto dto = new CoordinatePairDto();
                dto.setX(coord.getXSymbol());
                dto.setY(coord.getYSymbol());
                return dto;
            })
            .collect(Collectors.toList());
    }

    public ScoreCounter calculateScore(long sessionId) {
        ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
        return new ScoreCounter(game.getBoardState());
    }

    public GameResult judgeResult(long sessionId) {
        ChessGame game = new ChessGame(() -> findBoardStatesMapBySessionId(sessionId));
        return GameResult.judgeScore(game.getBoardState());
    }
}
