package domain;

import common.TransactionContext;
import domain.dao.ChessInformationDao;
import domain.piece.Piece;
import domain.type.Color;
import domain.type.PieceType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final ChessInformationDao chessInformationDao;
    private final TransactionContext transactionContext;
    private String boardId;
    private Board board;
    private Color color;

    public ChessGame(final ChessInformationDao chessInformationDao, final TransactionContext transactionContext) {
        this.chessInformationDao = chessInformationDao;
        this.transactionContext = transactionContext;
    }

    public void initialize(final String boardId) {
        this.boardId = boardId;
        this.board = new Board(new HashMap<>(), new ScoreCalculator());
        this.color = Color.WHITE;
        board.initialize();
    }

    public Color move(final Location start, final Location end) {
        if (color.equals(Color.WHITE)) {
            return convert(board.moveWhite(start, end));
        }
        return convert(board.moveBlack(start, end));
    }

    private Color convert(final Piece piece) {
        if (piece.isSameType(PieceType.KING)) {
            final Color pieceColor = piece.getColor();
            return pieceColor.reverse();
        }
        color = color.reverse();
        return Color.NONE;
    }

    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }

    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    public Color judgeResult() {
        final double whiteScore = board.calculateWhiteScore();
        final double blackScore = board.calculateBlackScore();
        if (whiteScore > blackScore) {
            return Color.WHITE;
        }
        if (whiteScore < blackScore) {
            return Color.BLACK;
        }
        return Color.NONE;
    }

    public void findPreviousGame(final String boardId) {
        transactionContext.workWithTransaction(
            connection -> {
                final Map<Location, Piece> prevBoard = chessInformationDao.find(boardId, connection);
                final Color prevColor = chessInformationDao.findColor(boardId, connection);
                this.board = new Board(prevBoard, new ScoreCalculator());
                this.color = prevColor;
                this.boardId = boardId;
                return null;
            });
    }

    public void save() {
        transactionContext.workWithTransaction(this::save);
    }

    private Void save(final Connection connection) throws SQLException {
        final int count = chessInformationDao.count(boardId, connection);
        if (count == 0) {
            chessInformationDao.insert(board.getBoard(), boardId, color, connection);
            return null;
        }
        chessInformationDao.update(board.getBoard(), boardId, color, connection);
        return null;
    }

    public Board getBoard() {
        return board;
    }
}
