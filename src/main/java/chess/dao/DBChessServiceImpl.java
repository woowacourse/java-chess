package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import java.util.Map;

public class DBChessServiceImpl implements ChessService {
    private final ChessDao chessDao = new ChessDao();

    @Override
    public Map<String, String> getBoardByGameId(String gameId) {
        return chessDao.getBoardByGameId(gameId);
    }

    @Override
    public boolean move(String gameId, String from, String to) {
        final Map<String, String> boardByGameId = chessDao.getBoardByGameId(gameId);
        final Color color = Color.fromInt(chessDao.getTurnByGameId(gameId));
        final Board board = BoardFactory.newInstanceByDB(boardByGameId, color);
        boolean moveResult = board.move(Position.fromDB(from), Position.fromDB(to));
        if (moveResult) {
            chessDao.move(gameId, from, to, board.getPieceOnPosition(to));
        }
        return moveResult;
    }

    @Override
    public boolean isFinished(String gameId) {
        final Map<String, String> boardByGameId = chessDao.getBoardByGameId(gameId);
        final Board board = BoardFactory.newInstanceByDB(boardByGameId, Color.WHITE);
        return board.isFinished();
    }

    @Override
    public Map<Color, Double> getScore(String gameId) {
        final Map<String, String> boardByGameId = chessDao.getBoardByGameId(gameId);
        final Board board = BoardFactory.newInstanceByDB(boardByGameId, Color.WHITE);
        return board.getScore();
    }
}
