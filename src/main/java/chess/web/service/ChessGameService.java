package chess.web.service;

import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;
import chess.web.controller.Request;
import chess.web.dao.ChessBoardDao;
import chess.web.dao.PlayerDao;
import java.util.HashMap;
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

    public void move(ChessGame chessGame, String body) {
        Request request = Request.of(body);
        String command = request.command();

        String source = command.substring(0, 2);
        String target = command.substring(2, 4);

        chessGame.move(Position.of(source), Position.of(target));
        removeAll();
        saveAll(chessGame);
    }

    public ChessBoard createChessBoard() {
        Map<String, String> board = chessBoardDao.findAll();
        Map<Position, Piece> chessBoard = new HashMap<>();

        for (String position : board.keySet()) {
            Position currentPosition = Position.of(
                    File.of(position.substring(0, 1).toUpperCase()),
                    Rank.of(position.substring(1, 2)));

            Piece currentPiece = PieceFactory.of(position, board.get(position));
            chessBoard.put(currentPosition, currentPiece);
        }

        return ChessBoard.of(chessBoard);
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
