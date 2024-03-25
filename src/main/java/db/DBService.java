package db;

import domain.dto.PieceDto;

import java.util.List;

public class DBService {
    private final PieceDao pieceDao = new PieceDao();

    public boolean doesPreviousDataExist() {
        return pieceDao.count() != 0;
    }

    public List<PieceDto> loadPreviousData() {
        return pieceDao.findAll();
    }

    public void updatePiece(final List<PieceDto> pieceDtos) {
        pieceDao.deleteAll();
        for (PieceDto pieceDto : pieceDtos) {
            pieceDao.add(pieceDto);
        }
    }
}
