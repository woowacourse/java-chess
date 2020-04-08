package chess.service;

import chess.board.BoardGenerator;
import chess.board.ChessBoard;
import chess.board.ChessBoardAdapter;
import chess.dao.ChessDAO;
import chess.dao.MovementDAO;
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

    private final ChessDAO chessDAO;
    private final MovementDAO movementDAO;


    public ChessService(ChessDAO chessDAO, MovementDAO movementDAO) {
        this.chessDAO = chessDAO;
        this.movementDAO = movementDAO;
    }

    public MoveResponse move(MoveRequest moveRequest) throws SQLException {
        ChessGame chessGame = chessDAO.findById(moveRequest.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", moveRequest.getId())));

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        restore(moveRequest, chessManager);
        Piece deadPiece = chessManager.move(moveRequest.getSourceKey(), moveRequest.getTargetKey());

        movementDAO.save(moveRequest.toEntity());
        if (deadPiece.isKing()) {
            chessGame.endGame(chessManager.getCurrentTeam());
            chessDAO.update(chessGame);
        }

        return new MoveResponse(chessManager, moveRequest, deadPiece);
    }

    private void restore(MoveRequest moveRequest, ChessManager chessManager) throws SQLException {
        List<Movement> movements = movementDAO.findAllByChessId(moveRequest.getId());
        chessManager.moveAll(movements);
    }

    public ChessBoardResponse save() throws SQLException {
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessDAO.save(chessGame);

        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        return new ChessBoardResponse(chessGame.getId(), chessManager);
    }

    public SavedGameBundleResponse loadAllSavedGames() throws SQLException {
        return new SavedGameBundleResponse(chessDAO.findAllByActive());
    }

    public TilesDto getEmptyBoard() {
        return EMPTY_BOARD;
    }

    public ChessBoardResponse loadSavedGame(Long targetId) throws SQLException {
        chessDAO.findById(targetId)
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", targetId)));

        List<Movement> movements = movementDAO.findAllByChessId(targetId);
        ChessManager chessManager = new ChessManager(BoardGenerator.create());
        chessManager.moveAll(movements);

        return new ChessBoardResponse(targetId, chessManager);
    }

    public void surrender(SurrenderRequest surrenderRequest) throws SQLException {
        ChessGame chessGame = chessDAO.findById(surrenderRequest.getGameId())
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 게임(%d)입니다.", surrenderRequest.getGameId())));

        Team winTeam = Team.valueOf(surrenderRequest.getLoseTeam()).getOppositeTeam();
        chessGame.endGame(winTeam);

        chessDAO.update(chessGame);
    }
}
