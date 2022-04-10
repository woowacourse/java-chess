package chess.database.dao;

import java.util.HashMap;
import java.util.Map;

import chess.database.dto.BoardDto;
import chess.database.dto.PieceDto;
import chess.database.dto.PointDto;
import chess.database.dto.RouteDto;

public class FakeBoardDao implements BoardDao {

    private final Map<String, Map<PointDto, PieceDto>> memoryDatabase;

    public FakeBoardDao() {
        this.memoryDatabase = new HashMap<>();
    }

    @Override
    public void saveBoard(BoardDto boardDto, String roomName) {
        this.memoryDatabase.put(roomName, new HashMap<>(boardDto.getPointPieces()));
    }

    @Override
    public BoardDto readBoard(String roomName) {
        return new BoardDto(memoryDatabase.get(roomName));
    }

    @Override
    public void deletePiece(PointDto destination, String roomName) {
        Map<PointDto, PieceDto> pointPieces = memoryDatabase.get(roomName);
        pointPieces.remove(destination);
    }

    @Override
    public void updatePiece(RouteDto routeDto, String roomName) {
        Map<PointDto, PieceDto> pointPieces = memoryDatabase.get(roomName);
        PieceDto piece = pointPieces.get(routeDto.getSource());
        pointPieces.remove(routeDto.getSource());
        pointPieces.put(routeDto.getDestination(), piece);
    }

    public void removeBoard(String roomName) {
        memoryDatabase.remove(roomName);
    }
}
