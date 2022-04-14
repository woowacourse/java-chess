package chess.web.service;

import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import chess.web.dao.ChessBoardDao;
import chess.web.dao.PlayerDao;
import java.util.Map;

public class ChessGameService {

    private final ChessBoardDao chessBoardDao;
    private final PlayerDao playerDao;

    public ChessGameService(ChessBoardDao chessBoardDao, PlayerDao playerDao) {
        this.chessBoardDao = chessBoardDao;
        this.playerDao = playerDao;
    }

    public void start() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        removeAll();
        saveAll(chessGame);
    }

    public void move(ChessGame chessGame, String source, String target) {
        chessGame.move(Position.of(source), Position.of(target));
        removeAll();
        saveAll(chessGame);
    }

    public ChessBoard createChessBoard() {
        return ChessBoard.of(chessBoardDao.findAll());
    }

    private void removeAll() {
        chessBoardDao.deleteAll();
        playerDao.deleteAll();
    }

    private void saveAll(ChessGame chessGame) {
        Map<Position, Piece> chessBoard = chessGame.getBoard();
        for (Position position : chessBoard.keySet()) {
            chessBoardDao.save(position, chessBoard.get(position));
        }
        playerDao.save(Color.of(chessGame.getTurn()));
    }
}
