package chess.repository;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.dto.ChessRequestDto;
import chess.dto.MoveRequestDto;
import chess.dto.TurnChangeRequestDto;
import chess.dto.TurnRequestDto;

import java.util.List;
import java.util.Map;

public class ChessRepositoryImpl implements ChessRepository {
    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessRepositoryImpl() {
        this.pieceDao = new PieceDao();
        this.turnDao = new TurnDao();
    }

    @Override
    public void initializePieceStatus(final Map<String, String> board) {
        for (Map.Entry<String, String> boardStatus : board.entrySet()) {
            pieceDao.initializePieceStatus(boardStatus.getValue(), boardStatus.getKey());
        }
    }

    @Override
    public void initializeTurn() {
        turnDao.initializeTurn();
    }

    @Override
    public List<ChessRequestDto> showAllPieces() {
        return pieceDao.showAllPieces();
    }

    @Override
    public List<TurnRequestDto> showCurrentTurn() {
        return turnDao.showCurrentTurn();
    }

    @Override
    public void movePiece(final MoveRequestDto moveRequestDto) {
        pieceDao.movePiece(moveRequestDto);
    }

    @Override
    public void changeTurn(final TurnChangeRequestDto turnChangeRequestDto) {
        turnDao.changeTurn(turnChangeRequestDto);
    }

    @Override
    public void removeAllPieces() {
        pieceDao.removeAllPieces();
    }

    @Override
    public void removeTurn() {
        turnDao.removeTurn();
    }

    @Override
    public void removePiece(final MoveRequestDto moveRequestDto) {
        pieceDao.removePiece(moveRequestDto);
    }
}
