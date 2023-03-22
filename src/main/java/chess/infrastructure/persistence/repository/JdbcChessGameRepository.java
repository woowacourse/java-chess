package chess.infrastructure.persistence.repository;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameRepository;
import chess.domain.piece.Piece;
import chess.infrastructure.persistence.dao.ChessGameDao;
import chess.infrastructure.persistence.dao.PieceDao;
import chess.infrastructure.persistence.entity.ChessGameEntity;
import chess.infrastructure.persistence.entity.PieceEntity;
import chess.infrastructure.persistence.mapper.ChessGameMapper;
import chess.infrastructure.persistence.mapper.PieceMapper;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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
        final ChessGameEntity chessGameEntity = ChessGameMapper.fromDomain(chessGame);
        chessGameDao.save(chessGameEntity);
        final List<PieceEntity> pieceEntities = pieces.stream()
                .map(it -> PieceMapper.fromDomain(it, chessGameEntity.id()))
                .collect(toList());
        pieceDao.saveAll(pieceEntities);
        return ChessGameMapper.toDomain(chessGameEntity, pieceEntities);
    }

    @Override
    public Optional<ChessGame> findById(final Long id) {
        final List<PieceEntity> byAllChessGameId = pieceDao.findByAllChessGameId(id);
        return chessGameDao.findById(id)
                .map(it -> ChessGameMapper.toDomain(it, byAllChessGameId));
    }

    @Override
    public void update(final ChessGame chessGame) {
        chessGameDao.update(ChessGameMapper.fromDomain(chessGame));
        pieceDao.deleteAllByChessGameId(chessGame.id());
        pieceDao.saveAll(chessGame.pieces()
                .stream()
                .map(it -> PieceMapper.fromDomain(it, chessGame.id()))
                .collect(toList()));
    }
}
