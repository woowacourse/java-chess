package chess.service;

import chess.dao.chess.ChessGameDao;
import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.entity.ChessGameEntity;

import java.util.Optional;

public final class ChessGameService {
    private final ChessGameDao chessGameDao;
    private final ChessBoardService chessBoardService;

    public ChessGameService(final ChessGameDao chessGameDao, final ChessBoardService chessBoardService) {
        this.chessGameDao = chessGameDao;
        this.chessBoardService = chessBoardService;
    }

    public ChessGame getChessGame(final Long userId) {
        final Optional<ChessGameEntity> findChessGameEntity = chessGameDao.findByUserId(userId);
        if (findChessGameEntity.isEmpty()) {
            return getNewChessGame(userId);
        }
        final ChessGameEntity chessGameEntity = findChessGameEntity.get();
        return getExistChessGame(chessGameEntity);
    }

    public Long getChessGameId(final Long userId) {
        final Optional<ChessGameEntity> findChessGameEntity = chessGameDao.findByUserId(userId);
        if (findChessGameEntity.isEmpty()) {
            throw new IllegalArgumentException("저장된 데이터가 존재하지 않습니다.");
        }
        return findChessGameEntity.get().getId();
    }

    private ChessGame getNewChessGame(final Long userId) {
        final CampType currentCamp = CampType.WHITE;
        final Long chessGameId = chessGameDao.save(new ChessGameEntity(currentCamp.name(), userId));
        final ChessGame chessGame = new ChessGame(currentCamp);
        chessBoardService.saveAll(chessGameId, chessGame.getChessBoard());
        return chessGame;
    }

    private ChessGame getExistChessGame(final ChessGameEntity chessGameEntity) {
        final ChessBoard chessBoard = getChessBoard(chessGameEntity);
        final String currentCamp = chessGameEntity.getCurrentCamp();
        return new ChessGame(chessBoard, CampType.from(currentCamp));
    }

    private ChessBoard getChessBoard(final ChessGameEntity chessGameEntity) {
        final Long chessGameId = chessGameEntity.getId();
        return chessBoardService.getByChessGameId(chessGameId);
    }
}
