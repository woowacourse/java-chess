package chess.service;

import chess.domain.*;
import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dto.BoardStateDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServiceImpl implements GameService {

    private BoardStateDao boardStateDao;

    public GameServiceImpl() {
        DataSource ds = new DataSourceFactory().createDataSource();
        boardStateDao = new BoardStateDao(ds);
    }

    @Override
    public List<BoardStateDto> findBoardStatesByRoomId(long roomId) {
        try {
            return boardStateDao.findByRoomId(roomId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    @Override
    public GameResult movePiece(CoordinatePair from, CoordinatePair to, long roomId) {
        try {
            ChessGame game = new ChessGame(() -> findBoardStatesMapByRoomId(roomId));
            GameResult result = GameResult.judge(game.getBoardState().values());
            game.move(from, to);

            deleteTargetStateIfPresent(to, roomId);
            updateSrcState(from, to, roomId);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private GameBoardState findBoardStatesMapByRoomId(long roomId) {
        try {
            AbstractChessPieceFactory factory = new ChessPieceFactory();
            Map<CoordinatePair, ChessPiece> board = new HashMap<>();
            CoordinatePair.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

            boardStateDao.findByRoomId(roomId)
                .forEach(dto ->
                    board.put(CoordinatePair.from(dto.getCoordX() + dto.getCoordY()).get(),
                        factory.create(PieceType.valueOf(dto.getType()))));
            return GameBoardState.of(board);
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
}
