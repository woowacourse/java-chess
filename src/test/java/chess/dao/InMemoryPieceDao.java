package chess.dao;

import chess.dto.PieceDto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPieceDao implements PieceDao {
    private static final Map<PositionEntity, PieceEntity> entities = new HashMap<>();
    private static PieceDao inMemoryPieceDao;

    public static PieceDao getInstance() {
        if (inMemoryPieceDao == null) {
            inMemoryPieceDao = new InMemoryPieceDao();
        }
        return inMemoryPieceDao;
    }

    @Override
    public void addPiece(PieceDto pieceDto) throws SQLException {
        PositionEntity positionEntity = new PositionEntity(pieceDto.getPosition());
        PieceEntity pieceEntity = new PieceEntity(pieceDto.getPieceType(), pieceDto.getTeam());
        entities.put(positionEntity, pieceEntity);
    }

    @Override
    public void updatePiece(PieceDto pieceDto) throws SQLException {
        PieceEntity pieceEntity = entities.get(pieceDto.getPosition());
        pieceEntity.setPieceType(pieceDto.getPieceType());
        pieceEntity.setTeam(pieceDto.getTeam());
    }
}

class PositionEntity {
    private String position;

    public PositionEntity(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

class PieceEntity {
    private String pieceType;
    private String team;

    public PieceEntity(String pieceType, String team) {
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
