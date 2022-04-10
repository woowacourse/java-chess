package chess.service;

import chess.dao.ChessDao;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.state.Running;
import chess.domain.state.StateFactory;
import chess.dto.ChessGameDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessService {

    private static final int PIECE_NAME_INDEX = 0;
    private static final int PIECE_COLOR_INDEX = 1;

    private final ChessDao chessDao = new ChessDao();
    private ChessGame chessGame;

    public ChessGameDto newGame() {
        chessDao.initBoard(new BoardInitializer().init());
        chessGame = new ChessGame(new Running(Color.from(chessDao.findCurrentColor()), convertToBoard(chessDao.findBoard())));
        return new ChessGameDto(chessDao.findBoard(), chessGame.status());
    }

    private Board convertToBoard(final Map<String, List<String>> boardData) {
        final Map<Position, Piece> board = new HashMap<>();
        for (final Map.Entry<String, List<String>> entry : boardData.entrySet()) {
            board.put(Position.from(entry.getKey()), PieceFactory.of(entry.getValue().get(PIECE_NAME_INDEX), entry.getValue().get(PIECE_COLOR_INDEX)));
        }
        return new Board(() -> board);
    }

    public ChessGameDto move(final String from, final String to) {
        chessGame.move(Position.from(from), Position.from(to));
        final var nextColor = Color.from(chessDao.findCurrentColor()).next();
        chessDao.updateBoard(from, to, nextColor.name());
        return new ChessGameDto(chessDao.findBoard(), chessGame.status());
    }

    public ChessGameDto loadGame() {
        chessGame = new ChessGame(StateFactory.of(Color.from(chessDao.findCurrentColor()), convertToBoard(chessDao.findBoard())));
        return new ChessGameDto(chessDao.findBoard(), chessGame.status());
    }
}
