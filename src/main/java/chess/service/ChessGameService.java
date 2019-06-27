package chess.service;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;
import chess.domain.factory.ChessPieceFactory;
import chess.domain.piece.ChessPiece;
import chess.persistence.AbstractDataSourceFactory;
import chess.persistence.dao.BoardStateDao;
import chess.persistence.dao.TurnDao;
import chess.persistence.dto.BoardStateDto;
import chess.persistence.dto.TurnDto;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessGameService {
    private BoardStateDao boardStateDao;
    private TurnDao turnDao;

    public ChessGameService(AbstractDataSourceFactory dataSourceFactory) {
        boardStateDao = new BoardStateDao(dataSourceFactory.createDataSource());
        turnDao = new TurnDao(dataSourceFactory.createDataSource());
    }

    public List<Optional<Long>> createBoardState(Map<ChessCoordinate, PieceType> boardState, long roomId) {
        return boardState.entrySet().stream()
                .map(entry -> getBoardStateDto(roomId, entry))
                .map(this::tryInsertBoardState)
                .collect(Collectors.toList());
    }

    private BoardStateDto getBoardStateDto(long roomId, Map.Entry<ChessCoordinate, PieceType> entry) {
        BoardStateDto dto = new BoardStateDto();
        dto.setRoomId(roomId);
        dto.setCoordX(entry.getKey().getX().getSymbol());
        dto.setCoordY(entry.getKey().getY().getSymbol());
        dto.setType(entry.getValue().name());
        return dto;
    }

    private Optional<Long> tryInsertBoardState(BoardStateDto dto) {
        try {
            return Optional.of(boardStateDao.addState(dto));
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드 상태를 넣을 수 없습니다.");
        }
    }

    public Map<ChessCoordinate, ChessPiece> findBoardStatesByRoomId(Long roomId) {
        try {
            ChessPieceFactory factory = new ChessPieceFactory();
            Map<ChessCoordinate, ChessPiece> board = new HashMap<>();
            ChessCoordinate.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

            boardStateDao.findByRoomId(roomId).forEach(dto ->
                    board.put(ChessCoordinate.valueOf(dto.getCoordX() + dto.getCoordY()),
                            factory.create(PieceType.valueOf(dto.getType()))));
            return board;
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드를 찾을 수 없습니다.");
        }
    }

    public void updateChessPiecePosition(ChessCoordinate from, ChessCoordinate to, long roomId) {
        try {
            List<BoardStateDto> boardStates = boardStateDao.findByRoomId(roomId);

            deleteBoardStateByTo(to, boardStates);
            updateBoardState(from, to, boardStates);
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드를 찾을 수 없습니다.");
        }
    }

    private void updateBoardState(ChessCoordinate from, ChessCoordinate to, List<BoardStateDto> boardStates) {
        boardStates.stream()
                .filter(dto -> dto.getCoordX().equals(from.getX().getSymbol()))
                .filter(dto -> dto.getCoordY().equals(from.getY().getSymbol())).findFirst().ifPresent(dto -> {
            dto.setCoordX(to.getX().getSymbol());
            dto.setCoordY(to.getY().getSymbol());
            try {
                boardStateDao.updateCoordById(dto);
            } catch (SQLException e) {
                throw new IllegalArgumentException("보드 상태를 업데이트 할 수 없습니다.");
            }
        });
    }

    private void deleteBoardStateByTo(ChessCoordinate to, List<BoardStateDto> boardStates) {
        boardStates.stream().filter(dto -> dto.getCoordX().equals(to.getX().getSymbol()))
                .filter(dto -> dto.getCoordY().equals(to.getY().getSymbol())).findFirst().ifPresent(dto -> {
            try {
                boardStateDao.deleteById(dto.getId());
            } catch (SQLException e) {
                throw new IllegalArgumentException("보드 상태를 지울 수 없습니다.");
            }
        });
    }

    public void createTurn(Team team, long id) {
        try {
            TurnDto turn = new TurnDto();
            turn.setCurrent(team.toString());
            turn.setRoomId(id);
            turnDao.addTurn(turn);
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 만들 수 없습니다.");
        }
    }

    public Optional<TurnDto> findTurnByRoomId(long id) {
        try {
            return turnDao.findById(id);
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 찾을 수 없습니다.");
        }
    }

    public void updateTurnByRoomId(Team team, long roomId) {
        try {
            TurnDto turn = new TurnDto();
            turn.setCurrent(team.name());
            turn.setRoomId(roomId);
            turnDao.updateCoordById(turn);
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 업데이트 할 수 없습니다.");
        }
    }
}
