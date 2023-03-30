package chess.service;

import chess.dao.chess.ChessGameDao;
import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;
import chess.entity.ChessGameEntity;
import chess.entity.PieceEntity;

import java.util.Map;
import java.util.Optional;

public final class ChessGameService {
    private final ChessGameDao chessGameDao;
    private final ChessBoardService chessBoardService;

    public ChessGameService(final ChessGameDao chessGameDao, final ChessBoardService chessBoardService) {
        this.chessGameDao = chessGameDao;
        this.chessBoardService = chessBoardService;
    }

    public ChessGame getOrCreateChessGame(final long userId) {
        final Optional<ChessGameEntity> findChessGameEntity = chessGameDao.findByUserId(userId);
        if (findChessGameEntity.isEmpty()) {
            return getNewChessGame(userId);
        }
        final ChessGameEntity chessGameEntity = findChessGameEntity.get();
        return getExistChessGame(chessGameEntity);
    }

    public long getChessGameId(final long userId) {
        final Optional<ChessGameEntity> findChessGameEntity = chessGameDao.findByUserId(userId);
        if (findChessGameEntity.isEmpty()) {
            throw new IllegalArgumentException("저장된 데이터가 존재하지 않습니다.");
        }
        return findChessGameEntity.get().getId();
    }

    public void play(final long userId, final Position source, final Position target) {
        final ChessGame chessGame = getOrCreateChessGame(userId);
        chessGame.play(source, target);
        final long chessGameId = getChessGameId(userId);
        deletePieces(source, target, chessGameId);
        savePieces(target, chessGameId, chessGame);
        chessGameDao.updateCurrentCampById(chessGameId, chessGame.getCurrentCamp());
    }

    private void deletePieces(final Position source, final Position target, final long chessGameId) {
        final PieceEntity sourcePiece = PieceEntity.createWithLocation(chessGameId, source.getRank(), source.getFile());
        final PieceEntity targetPiece = PieceEntity.createWithLocation(chessGameId, target.getRank(), target.getFile());
        chessBoardService.deletePieces(sourcePiece, targetPiece);
    }

    private void savePieces(final Position target, final long chessGameId, final ChessGame chessGame) {
        final Map<Position, Piece> chessBoard = chessGame.getChessBoard();
        final Piece piece = chessBoard.get(target);
        final PieceEntity savedPiece = PieceEntity.createWithChessGameId(chessGameId, target.getRank(),
                target.getFile(), piece.getPieceType().name(), piece.getCampType().name());
        chessBoardService.savePiece(savedPiece);
    }

    public void clear(final long userId) {
        final long chessGameId = getChessGameId(userId);
        chessBoardService.deleteByChessGameId(chessGameId);
        chessGameDao.deleteByUserId(userId);
    }

    private ChessGame getNewChessGame(final long userId) {
        final CampType currentCamp = CampType.WHITE;
        final long chessGameId = chessGameDao.insert(new ChessGameEntity(currentCamp.name(), userId));
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
        final long chessGameId = chessGameEntity.getId();
        return chessBoardService.getByChessGameId(chessGameId);
    }
}
