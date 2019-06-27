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
        return Optional.of(boardStateDao.addState(dto));
    }

    public Map<ChessCoordinate, ChessPiece> findBoardStatesByRoomId(Long roomId) {
        ChessPieceFactory factory = new ChessPieceFactory();
        Map<ChessCoordinate, ChessPiece> board = new HashMap<>();
        ChessCoordinate.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

        boardStateDao.findByRoomId(roomId).forEach(dto ->
                board.put(ChessCoordinate.valueOf(dto.getCoordX() + dto.getCoordY()),
                        factory.create(PieceType.valueOf(dto.getType()))));
        return board;
    }

    public void updateChessPiecePosition(ChessCoordinate from, ChessCoordinate to, long roomId) {
        List<BoardStateDto> boardStates = boardStateDao.findByRoomId(roomId);

        deleteBoardStateByTo(to, boardStates);
        updateBoardState(from, to, boardStates);
    }

    private void updateBoardState(ChessCoordinate from, ChessCoordinate to, List<BoardStateDto> boardStates) {
        boardStates.stream()
                .filter(dto -> dto.getCoordX().equals(from.getX().getSymbol()))
                .filter(dto -> dto.getCoordY().equals(from.getY().getSymbol())).findFirst().ifPresent(dto -> {
            dto.setCoordX(to.getX().getSymbol());
            dto.setCoordY(to.getY().getSymbol());
            boardStateDao.updateCoordById(dto);
        });
    }

    private void deleteBoardStateByTo(ChessCoordinate to, List<BoardStateDto> boardStates) {
        boardStates.stream().filter(dto -> dto.getCoordX().equals(to.getX().getSymbol()))
                .filter(dto -> dto.getCoordY().equals(to.getY().getSymbol())).findFirst().ifPresent(dto -> {

            boardStateDao.deleteById(dto.getId());

        });
    }

    public void createTurn(Team team, long id) {
        TurnDto turn = new TurnDto();
        turn.setCurrent(team.toString());
        turn.setRoomId(id);
        turnDao.addTurn(turn);
    }

    public Optional<TurnDto> findTurnByRoomId(long id) {
        return turnDao.findById(id);

    }

    public void updateTurnByRoomId(Team team, long roomId) {
        TurnDto turn = new TurnDto();
        turn.setCurrent(team.name());
        turn.setRoomId(roomId);
        turnDao.updateTurnByRoomId(turn);
    }
}
