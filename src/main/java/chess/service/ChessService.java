package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.BoardDao;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.StatusResult;
import chess.domain.state.turn.State;
import chess.dto.PieceDto;

import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessGameDao chessGameDao;
    private final BoardDao boardDao;

    public ChessService(ChessGameDao boardDao, BoardDao pieceDao) {
        this.chessGameDao = boardDao;
        this.boardDao = pieceDao;
    }

    public ChessGame load() {
        if (!boardDao.isExist()) {
            ChessGame chessGame = new ChessGame();
            boardDao.saveAllPieces(chessGame.getBoard());
            chessGameDao.saveState(chessGame.getState());
        }
        Map<Position, Piece> pieceMap = boardDao.loadAllPieces();
        State state = chessGameDao.loadState();
        return new ChessGame(new Board(pieceMap, state));
    }

    public boolean isReadyGame() {
        if (!boardDao.isExist()) {
            return true;
        }
        ChessGame chessGame = load();
        return chessGame.isFinish();
    }

    public boolean isPlayingGame() {
        if (isReadyGame()) {
            return false;
        }
        ChessGame chessGame = load();
        return chessGame.isPlaying();
    }

    public StatusResult processStatus() {
        ChessGame chessGame = load();
        return chessGame.processStatus();
    }

    public List<PieceDto> getBoardInformation() {
        ChessGame chessGame = load();
        return chessGame.getBoardInformation();
    }

    public Team getCurrentTeam() {
        ChessGame chessGame = load();
        return chessGame.getCurrentTurnTeam();
    }

    public void processMove(Position source, Position target) {
        ChessGame chessGame = load();
        chessGame.move(source, target);
        Map<Position, Piece> pieces = chessGame.getBoard();
        boardDao.deleteByPosition(source);
        boardDao.deleteByPosition(target);
        boardDao.insertPiece(source, pieces.get(source));
        boardDao.insertPiece(target, pieces.get(target));
        chessGameDao.deleteState();
        chessGameDao.saveState(chessGame.getState());
    }

    public void init() {
        ChessGame chessGame = new ChessGame();
        boardDao.removeAllPieces();
        boardDao.saveAllPieces(chessGame.getBoard());
        chessGameDao.deleteState();
        chessGameDao.saveState(chessGame.getState());
    }
}
