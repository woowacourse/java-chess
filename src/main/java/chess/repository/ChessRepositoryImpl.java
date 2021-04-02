package chess.repository;

import chess.dao.ChessDao;
import chess.dto.ChessRequestDto;
import chess.dto.PieceRequestDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChessRepositoryImpl implements ChessRepository {
    private final ChessDao chessDao;

    public ChessRepositoryImpl() {
        this.chessDao = new ChessDao();
    }

    @Override
    public void initializePieceStatus(final Map<String, String> board) throws SQLException {
        for (Map.Entry<String, String> boardStatus : board.entrySet()) {
            PieceRequestDto pieceRequestDto = new PieceRequestDto(
                    boardStatus.getValue(), boardStatus.getKey());
            chessDao.initializePieceStatus(pieceRequestDto);
        }
    }

    @Override
    public void initializeTurn() throws SQLException {
        chessDao.initializeTurn();
    }

    @Override
    public List<ChessRequestDto> showAllPieces() throws SQLException {
        return chessDao.showAllPieces();
    }

    @Override
    public void removeAllPieces() throws SQLException {
        chessDao.removeAllPieces();
    }

    @Override
    public void removeTurn() throws SQLException {
        chessDao.removeTurn();
    }
}
