package chess.dao;

import chess.dto.PieceDTO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPieceDAO implements PieceDAO {
    private static final Map<PositionEntity, PieceEntity> entities = new HashMap<>();
    private static PieceDAO inMemoryPieceDAO;

    public static PieceDAO getInstance() {
        if (inMemoryPieceDAO == null) {
            inMemoryPieceDAO = new InMemoryPieceDAO();
        }
        return inMemoryPieceDAO;
    }

    @Override
    public void addPiece(PieceDTO pieceDTO) throws SQLException {
        PositionEntity positionEntity = new PositionEntity(pieceDTO.getPosition());
        PieceEntity pieceEntity = new PieceEntity(pieceDTO.getPieceType(), pieceDTO.getTeam());
        entities.put(positionEntity, pieceEntity);
    }

    @Override
    public void updatePiece(PieceDTO pieceDTO) throws SQLException {
        PieceEntity pieceEntity = entities.get(pieceDTO.getPosition());
        pieceEntity.setPieceType(pieceDTO.getPieceType());
        pieceEntity.setTeam(pieceDTO.getTeam());
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
