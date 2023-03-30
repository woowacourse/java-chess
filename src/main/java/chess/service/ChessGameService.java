package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.dao.dto.ChessGameDto;
import chess.dao.dto.PieceDto;
import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessGameService {

    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameService(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public List<Long> loadAllChessGameId() {
        final List<ChessGameDto> chessGames = chessGameDao.findAll();
        return chessGames.stream()
                .map(ChessGameDto::getId)
                .collect(Collectors.toList());
    }

    public ChessGame loadChessGameById(final Long chessGameId) {
        final ChessGameDto chessGame = chessGameDao.findById(chessGameId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 ID 입니다."));
        final List<PieceDto> pieces = pieceDao.findAllByChessGameId(chessGameId);
        return ChessGameFactory.create(chessGame, pieces);
    }

    public void saveChessGame(final ChessGame chessGame) {
        chessGameDao.save(chessGame);
        final ChessGameDto chessGameDto = chessGameDao.findLatest()
                .orElseThrow(() -> new IllegalStateException("저장된 게임이 존재해야 합니다."));
        chessGame.setId(chessGameDto.getId());
        savePieces(chessGame);
    }

    private void savePieces(final ChessGame chessGame) {
        final Board board = chessGame.getBoard();
        final Map<Square, Piece> table = board.getBoard();
        for (final Square square : table.keySet()) {
            pieceDao.save(chessGame.getId(), square, table.get(square));
        }
    }

    public void move(final ChessGame chessGame, final Square source, final Square destination) {
        final Optional<PieceDto> searchPiece = pieceDao.findBySquare(chessGame.getId(), destination);
        final Piece destinationPiece = getDestinationPiece(chessGame, destination);
        if (searchPiece.isEmpty()) {
            pieceDao.save(chessGame.getId(), destination, destinationPiece);
        } else {
            pieceDao.update(chessGame.getId(), destination, destinationPiece);
        }
        pieceDao.delete(chessGame.getId(), source);
        chessGameDao.update(chessGame);
    }

    private Piece getDestinationPiece(final ChessGame chessGame, final Square destination) {
        final Board board = chessGame.getBoard();
        return board.findPieceOf(destination)
                .orElseThrow(() -> new IllegalStateException("데이터가 존재하지 않습니다."));
    }

    public void updateChessGameStateAndTurn(final ChessGame chessGame) {
        chessGameDao.update(chessGame);
    }
}
