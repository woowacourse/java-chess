package chess.service;

import java.util.Map;
import java.util.Objects;

import chess.EmblemMapper;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.ChessGame;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.PieceArrangement.PieceArrangement;
import chess.model.PieceArrangement.SavedPieceArrangement;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Turn;
import chess.model.piece.Piece;

public class ChessGameService {
    private final BoardDao boardDao;
    private GameDao gameDao;
    private ChessGame chessGame;

    public ChessGameService(BoardDao boardDao) {
        this.boardDao = boardDao;
        this.chessGame = new ChessGame(new Turn(), new DefaultArrangement());
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void save() {
        gameDao.save();
        boardDao.save(gameDao.getId(), EmblemMapper.StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
    }

    public Map<String, String> find() {
        return boardDao.findById(gameDao.getId());
    }

    public void move(Position source, Position target) {
        chessGame.move(source, target);
    }

    public void delete() {
        boardDao.deleteById(gameDao.getId());
        gameDao.deleteById(gameDao.getId());
    }

    public void init(Turn turn, PieceArrangement pieceArrangement) {
        if (find().isEmpty()) {
            this.chessGame = new ChessGame(turn, pieceArrangement);
            return;
        }
        this.chessGame = new ChessGame(turn, new SavedPieceArrangement(find()));
    }

    public PieceColor getTurnColor() {
        return chessGame.getTurnColor();
    }

    public Map<Position, Piece> getPiecesByPositions() {
        return chessGame.getBoardValue();
    }

    public boolean isFinished() {
        return chessGame.isFinished();
    }

    public double getScore() {
        return chessGame.getScore();
    }
}
