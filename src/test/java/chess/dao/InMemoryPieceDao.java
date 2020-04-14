package chess.dao;

import chess.dto.PieceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPieceDao implements PieceDao {
    private static final Map<PositionRepository, PieceRepository> repository = new HashMap<>();
    private static PieceDao inMemoryPieceDao;

    public static PieceDao getInstance() {
        if (inMemoryPieceDao == null) {
            inMemoryPieceDao = new InMemoryPieceDao();
        }
        return inMemoryPieceDao;
    }

    @Override
    public void addPiece(PieceDto pieceDto) throws SQLException {
        PositionRepository positionRepository = new PositionRepository(pieceDto.getPosition());
        PieceRepository pieceRepository = new PieceRepository(pieceDto.getPieceType(), pieceDto.getTeam());
        repository.put(positionRepository, pieceRepository);
    }

    @Override
    public void updatePiece(PieceDto pieceDto) throws SQLException {
        PieceRepository pieceRepository = repository.get(pieceDto.getPosition());
        pieceRepository.setPieceType(pieceDto.getPieceType());
        pieceRepository.setTeam(pieceDto.getTeam());
    }

    @Override
    public List<PieceDto> findPiece() throws SQLException {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (PositionRepository positionRepository : repository.keySet()) {
            PieceRepository pieceRepository = repository.get(positionRepository);
            String position = positionRepository.getPosition();
            String team = pieceRepository.getTeam();
            String pieceType = pieceRepository.getPieceType();
            PieceDto pieceDto = new PieceDto(position, team, pieceType);
            pieceDtos.add(pieceDto);
        }
        return pieceDtos;
    }
}

class PositionRepository {
    private String position;

    public PositionRepository(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

class PieceRepository {
    private String pieceType;
    private String team;

    public PieceRepository(String pieceType, String team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getTeam() {
        return team;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
