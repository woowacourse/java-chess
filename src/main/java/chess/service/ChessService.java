package chess.service;

import chess.dao.BoardDao;
import chess.dao.DbBoardDao;
import chess.dao.DbPieceDao;
import chess.dao.PieceDao;
import chess.game.Board;
import chess.game.Game;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.piece.detail.Name;
import chess.position.Position;
import chess.status.Ready;
import chess.status.Running;
import chess.view.Command;
import java.util.*;
import java.util.stream.Collectors;

public class ChessService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessService() {
        this.boardDao = new DbBoardDao();
        this.pieceDao = new DbPieceDao();
    }

    private void updateBoard(final int boardId, final Color turn) {
        boardDao.updateById(boardId, turn);
    }

    private void updatePiece(final int boardId, final Map<Position, Piece> board) {
        pieceDao.deleteAllById(boardId);
        pieceDao.saveAll(boardId, board);
    }

    public Map<Position, Piece> getBoard() {
        final int boardId = boardDao.findLastlyUsedBoard();
        return pieceDao.findAllByBoardId(boardId);
    }

    public void initGame() {
        Game game = new Game(Ready.start(Command.START));
        if (pieceDao.isExist()) {
            game.load(boardDao.load(), pieceDao.load());
            return;
        }

        boardDao.save(game.getTurn());
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, game.getBoard().getValue());
    }

    public void restartGame() {
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.deleteAllById(boardId);
        boardDao.deleteById(boardId);

        Game game = new Game(Ready.start(Command.START));
        boardDao.save(game.getTurn());
        final int newBoardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(newBoardId, game.getBoard().getValue());
    }

    public Map<Color, Double> getResult() {
        final Board board = Board.of(getBoard());
        return board.createBoardScore();
    }

    public Color getWinningColor() {
        final Map<Color, Double> result = getResult();
        Set<Double> values = new HashSet<>(result.values());

        if (values.size() == 1) {
            return Color.NONE;
        }

        final Optional<Map.Entry<Color, Double>> max = result.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return max.get().getKey();
    }

    public void movePiece(final String input) {
        final List<String> splitInput = Arrays.asList(input.split(" "));
        final int boardId = boardDao.findLastlyUsedBoard();
        final Map<Position, Piece> pieces = pieceDao.findAllByBoardId(boardId);
        final Color turn = boardDao.findTurnById(boardId);
        final Game game = new Game(new Running(pieces, turn));
        game.run(splitInput.get(0), splitInput);
        save(boardId, game);
    }

    public boolean isRunning() {
        final int boardId = boardDao.findLastlyUsedBoard();
        final Map<Position, Piece> pieces = pieceDao.findAllByBoardId(boardId);
        final Color turn = boardDao.findTurnById(boardId);
        final Game game = new Game(new Running(pieces, turn));

        return !game.isFinished();
    }

    private void save(final int boardId, final Game game) {
        updatePiece(boardId, game.getBoard().getValue());
        updateBoard(boardId, game.getTurn());
    }

    public Color getWinnerColor() {
        final int boardId = boardDao.findLastlyUsedBoard();
        final Map<Position, Piece> pieces = pieceDao.findAllByBoardId(boardId);
        final List<Piece> winPiece = pieces.values().stream()
                .filter(value -> value.getName() == Name.KING)
                .collect(Collectors.toList());

        if (winPiece.get(0).getColor() == Color.WHITE) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
