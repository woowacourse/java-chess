package chess.service;

import static chess.game.MoveCommand.FROM_POSITION_INDEX;
import static chess.game.MoveCommand.TO_POSITION_INDEX;

import chess.dao.BoardDao;
import chess.dao.DbBoardDao;
import chess.dao.DbPieceDao;
import chess.dao.PieceDao;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import chess.game.Board;
import chess.game.Game;
import chess.game.Result;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Position;
import chess.state.Ready;
import chess.state.Running;
import chess.view.Command;
import java.util.*;

public class ChessService {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessService() {
        this.boardDao = new DbBoardDao();
        this.pieceDao = new DbPieceDao();
    }

    public void initGame() {
        Game game = new Game(Ready.start(Command.START));
        if (pieceDao.isExist()) {
            game.load(boardDao.load(), pieceDao.load());
            return;
        }
        saveCurrentState(game);
    }

    public void restartGame() {
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.deleteAllById(boardId);
        boardDao.deleteById(boardId);
        Game game = new Game(Ready.start(Command.START));
        saveCurrentState(game);
    }

    public void movePiece(final String input) {
        final List<String> splitInput = Arrays.asList(input.split(" "));
        final int boardId = boardDao.findLastlyUsedBoard();
        final Game game = createGame(boardId);
        game.run(splitInput.get(0), splitInput);
        update(boardId, game, splitInput);
    }

    public boolean isRunning() {
        final int boardId = boardDao.findLastlyUsedBoard();
        final Game game = createGame(boardId);

        return !game.isFinished();
    }

    public BoardDto getBoard() {
        final int boardId = boardDao.findLastlyUsedBoard();
        final Map<Position, Piece> pieces = pieceDao.findAllByBoardId(boardId);
        return BoardDto.toDto(pieces);
    }

    public Map<Color, Double> getResult() {
        final Board board = Board.of(getPieces());
        return board.createBoardScore();
    }

    public Result getStatusResult() {
        final Board board = Board.of(getPieces());
        return new Result(board.createBoardScore(), getWinningColor());
    }

    public Result getFinalResult() {
        final Board board = Board.of(getPieces());

        return new Result(board.createBoardScore(), getWinnerColor());
    }

    public Color getWinningColor() {
        final Map<Color, Double> result = getResult();
        Set<Double> values = new HashSet<>(result.values());
        if (values.size() == 1) {
            return Color.NONE;
        }

        final Color winningColor = result.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new NoSuchElementException("결과가 존재하지 않습니다."));

        return winningColor;
    }

    public Color getWinnerColor() {
        final int boardId = boardDao.findLastlyUsedBoard();
        final Map<Position, Piece> pieces = pieceDao.findAllByBoardId(boardId);
        final Color color = boardDao.findTurnById(boardId);
        final Game game = new Game(new Running(pieces, color));

        return game.getWinColor();
    }

    private Game createGame(final int boardId) {
        final Map<Position, Piece> pieces = pieceDao.findAllByBoardId(boardId);
        final Color turn = boardDao.findTurnById(boardId);
        return new Game(new Running(pieces, turn));
    }

    private void saveCurrentState(final Game game) {
        boardDao.save(game.getTurn());
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, game.getBoard().getValue());
    }

    private Map<Position, Piece> getPieces() {
        final int boardId = boardDao.findLastlyUsedBoard();
        return pieceDao.findAllByBoardId(boardId);
    }

    private void update(final int boardId, final Game game, final List<String> splitInput) {
        final Map<Position, Piece> value = game.getBoard().getValue();
        final Position to = Position.of(splitInput.get(TO_POSITION_INDEX));
        final Piece piece = value.get(to);

        boardDao.updateById(boardId, game.getTurn());
        pieceDao.deletePieceByPosition(boardId, splitInput.get(TO_POSITION_INDEX));
        pieceDao.updatePieceByPosition(boardId, splitInput.get(FROM_POSITION_INDEX), PieceDto.toDto(piece, to));
    }
}
