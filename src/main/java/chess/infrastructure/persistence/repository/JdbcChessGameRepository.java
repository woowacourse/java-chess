package chess.infrastructure.persistence.repository;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameRepository;
import chess.domain.piece.Piece;
import chess.infrastructure.persistence.dao.ChessGameDao;
import chess.infrastructure.persistence.dao.PieceDao;
import chess.infrastructure.persistence.entity.ChessGameEntity;
import chess.infrastructure.persistence.entity.PieceEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcChessGameRepository implements ChessGameRepository {

    private final PieceDao pieceDao;
    private final ChessGameDao chessGameDao;

    public JdbcChessGameRepository(final PieceDao pieceDao, final ChessGameDao chessGameDao) {
        this.pieceDao = pieceDao;
        this.chessGameDao = chessGameDao;
    }

    @Override
    public ChessGame save(final ChessGame chessGame) {
        final List<Piece> pieces = chessGame.pieces();
        final ChessGameEntity chessGameEntity = ChessGameEntity.fromDomain(chessGame);
        chessGameDao.save(chessGameEntity);
        final List<PieceEntity> pieceEntities = pieces.stream()
                .map(it -> PieceEntity.fromDomain(it, chessGameEntity.id()))
                .collect(Collectors.toList());
        pieceDao.saveAll(pieceEntities);
        return chessGameEntity.toDomain(pieceEntities);
    }

    @Override
    public Optional<ChessGame> findById(final Long id) {
        final List<PieceEntity> byAllChessGameId = pieceDao.findByAllChessGameId(id);
        return chessGameDao.findById(id)
                .map(it -> it.toDomain(byAllChessGameId));
    }

    @Override
    public void update(final ChessGame chessGame) {
        chessGameDao.update(ChessGameEntity.fromDomain(chessGame));
        pieceDao.deleteAllByChessGameId(chessGame.id());
        final List<PieceEntity> pieceEntities = chessGame.pieces().stream()
                .map(it -> PieceEntity.fromDomain(it, chessGame.id()))
                .collect(Collectors.toList());
        pieceDao.saveAllWithId(pieceEntities);
    }
}
