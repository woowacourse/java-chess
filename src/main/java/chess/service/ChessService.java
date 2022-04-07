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
    private final ChessGame chessGame;

    public ChessService(ChessGameDao boardDao, BoardDao pieceDao) {
        this.chessGameDao = boardDao;
        this.boardDao = pieceDao;
        this.chessGame = generate();
    }

    public ChessGame generate() {
        if (boardDao.isExist()) {
            Map<Position, Piece> pieceMap = boardDao.loadAllPieces();
            State state = chessGameDao.loadState();
            return new ChessGame(new Board(pieceMap, state));
        }
        return new ChessGame();
    }

    public boolean isPlaying() {
        return chessGame.isPlaying();
    }

    public StatusResult processStatus() {
        return chessGame.processStatus();
    }

    public List<PieceDto> getCurrentImages() {
        return chessGame.getCurrentImages();
    }

    public Team getCurrentTeam() {
        return chessGame.getCurrentTurnTeam();
    }

    public void processMove(Position source, Position target) {
        chessGame.move(source, target);
        Map<Position, Piece> pieces = chessGame.getBoard();
        boardDao.deleteByPosition(source);
        boardDao.deleteByPosition(target);
        boardDao.insertPiece(source, pieces.get(source));
        boardDao.insertPiece(target, pieces.get(target));
        if (chessGame.isKingDeath()) {
            boardDao.removeAllPieces();
            chessGameDao.removeAll();
        }
    }

    public boolean isFinish() {
        return chessGame.isFinish();
    }

    public void start() {
        chessGame.start();
        chessGameDao.saveState(chessGame.getState());
    }

    public void load() {
        chessGame.load(boardDao.loadAllPieces(), chessGameDao.loadState());
    }

    public void save() {
        chessGameDao.saveState(chessGame.getState());
        boardDao.saveAllPieces(chessGame.getBoard());
    }
}
