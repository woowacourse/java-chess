package chess.service;

import chess.domain.*;
import chess.persistence.DataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.RoomDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.RoomDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessService {

    private RoomDao roomDao;
    private BoardStateDao boardStateDao;

    public ChessService() {
        DataSource ds = new DataSourceFactory().createDataSource();
        roomDao = new RoomDao(ds);
        boardStateDao = new BoardStateDao(ds);
    }

    public Optional<Long> createRoom(RoomDto room) {
        try {
            return Optional.of(roomDao.addRoom(room));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<RoomDto> findRoomById(long id) {
        try {
            return roomDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<RoomDto> findRoomByTitle(String title) {
        try {
            return roomDao.findByTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<RoomDto> findLatestNRooms(int topN) {
        try {
            return roomDao.findLatestN(topN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public List<Optional<Long>> createBoardState(Map<ChessCoordinate, PieceType> boardState, long roomId) {
        return boardState.entrySet().stream()
                .map(entry -> {
                    BoardStateDto dto = new BoardStateDto();
                    dto.setRoomId(roomId);
                    dto.setCoordX(entry.getKey().getX().getSymbol());
                    dto.setCoordY(entry.getKey().getY().getSymbol());
                    dto.setType(entry.getValue().name());
                    return dto;
                })
                .map(this::tryInsertBoardState)
                .collect(Collectors.toList());
    }

    public Map<ChessCoordinate, ChessPiece> findBoardStatesByRoomId(Long roomId) {
        try {
            AbstractChessPieceFactory factory = new ChessPieceFactory();
            Map<ChessCoordinate, ChessPiece> board = new HashMap<>();
            ChessCoordinate.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

            boardStateDao.findByRoomId(roomId).forEach(dto ->
                    board.put(ChessCoordinate.valueOf(dto.getCoordX() + dto.getCoordY()),
                            factory.create(PieceType.valueOf(dto.getType()))));
            return board;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    private Optional<Long> tryInsertBoardState(BoardStateDto dto) {
        try {
            return Optional.of(boardStateDao.addState(dto));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void updateChessPiecePosition(ChessCoordinate from, ChessCoordinate to, long roomId) {
        try {
            List<BoardStateDto> boardStates = boardStateDao.findByRoomId(roomId);

            boardStates.stream().filter(dto -> dto.getCoordX().equals(to.getX().getSymbol()))
                    .filter(dto -> dto.getCoordY().equals(to.getY().getSymbol())).findFirst().ifPresent(dto -> {
                        try {
                            boardStateDao.deleteById(dto.getId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
            });

            boardStates.stream()
                    .filter(dto -> dto.getCoordX().equals(from.getX().getSymbol()))
                    .filter(dto -> dto.getCoordY().equals(from.getY().getSymbol())).findFirst().ifPresent(dto -> {
                dto.setCoordX(to.getX().getSymbol());
                dto.setCoordY(to.getY().getSymbol());
                try {
                    boardStateDao.updateCoordById(dto);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
