package db;

import domain.dto.PieceDto;
import domain.dto.TurnDto;

import java.util.List;

public class DBService {
    private final PieceDao pieceDao = new PieceDao();
    private final TurnDao turnDao = new TurnDao();

    public boolean isPreviousDataExist() {
        return pieceDao.hasRecords();
    }

    public List<PieceDto> loadPreviousData() {
        return pieceDao.findAll();
    }

    public TurnDto loadPreviousTurn() {
        return turnDao.find();
    }

    public void updatePiece(final List<PieceDto> pieceDtos) {
        pieceDao.deleteAll();
        for (PieceDto pieceDto : pieceDtos) {
            pieceDao.add(pieceDto);
        }
    }

    public void updateTurn(final TurnDto turnDto) {
        turnDao.deleteAll();
        turnDao.update(turnDto);
    }

    public void deletePreviousData() {
        pieceDao.deleteAll();
        turnDao.deleteAll();
    }
}
