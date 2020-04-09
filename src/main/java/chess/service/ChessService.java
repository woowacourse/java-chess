package chess.service;

import chess.board.BoardGenerator;
import chess.board.ChessBoard;
import chess.board.ChessBoardAdapter;
import chess.dao.ChessDao;
import chess.dao.MovementDao;
import chess.entity.ChessGame;
import chess.entity.Movement;
import chess.manager.ChessManager;
import chess.piece.Piece;
import chess.piece.Team;
import chess.service.dto.ChessBoardResponse;
import chess.service.dto.MoveRequest;
import chess.service.dto.MoveResponse;
import chess.service.dto.SavedGameBundleResponse;
import chess.service.dto.SurrenderRequest;
import chess.service.dto.TilesDto;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class ChessService {
    private static final TilesDto EMPTY_BOARD = new TilesDto(new ChessManager(new ChessBoardAdapter(ChessBoard.empty())));

    private final ChessDao chessDao;
    private final MovementDao movementDao;

    public ChessService(ChessDao chessDao, MovementDao movementDao) {
        this.chessDao = chessDao;
        this.movementDao = movementDao;
    }

    public MoveResponse move(MoveRequest moveRequest) throws SQLException {
        ChessGame chessGame = chessDao.findById(moveRequest.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", moveRequest.getId())));

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        restore(moveRequest, chessManager);
        Piece deadPiece = chessManager.move(moveRequest.getSourceKey(), moveRequest.getTargetKey());

        movementDao.save(moveRequest.toEntity());
        if (deadPiece.isKing()) {
            chessGame.endGame(chessManager.getCurrentTeam());
            chessDao.update(chessGame);
        }

        return new MoveResponse(chessManager, moveRequest, deadPiece);
    }

    private void restore(MoveRequest moveRequest, ChessManager chessManager) throws SQLException {
        List<Movement> movements = movementDao.findAllByChessId(moveRequest.getId());
        chessManager.moveAll(movements);
    }

    public ChessBoardResponse save() throws SQLException {
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessDao.save(chessGame);

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        return new ChessBoardResponse(chessGame.getId(), chessManager);
    }

    public SavedGameBundleResponse loadAllSavedGames() throws SQLException {
        return new SavedGameBundleResponse(chessDao.findAllByActive());
    }

    public TilesDto getEmptyBoard() {
        return EMPTY_BOARD;
    }

    public ChessBoardResponse loadSavedGame(Long targetId) throws SQLException {
        chessDao.findById(targetId)
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", targetId)));

        List<Movement> movements = movementDao.findAllByChessId(targetId);
        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        chessManager.moveAll(movements);

        return new ChessBoardResponse(targetId, chessManager);
    }

    public void surrender(SurrenderRequest surrenderRequest) throws SQLException {
        ChessGame chessGame = chessDao.findById(surrenderRequest.getGameId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", surrenderRequest.getGameId())));

        Team winTeam = Team.valueOf(surrenderRequest.getLoseTeam()).getOppositeTeam();
        chessGame.endGame(winTeam);

        chessDao.update(chessGame);
    }
}
