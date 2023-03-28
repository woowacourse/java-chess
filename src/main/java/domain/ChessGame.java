package domain;

import common.JdbcContext;
import domain.piece.Piece;
import domain.repository.ChessRepository;
import domain.type.Color;
import domain.type.PieceType;
import java.util.HashMap;

public final class ChessGame {

    private final ChessRepository chessRepository;
    private final JdbcContext jdbcContext;
    private String boardId;
    private Board board;
    private Color color;

    public ChessGame(final ChessRepository chessRepository, final JdbcContext jdbcContext) {
        this.chessRepository = chessRepository;
        this.jdbcContext = jdbcContext;
    }

    public void initialize(final String boardId) {
        this.boardId = boardId;
        this.board = new Board(new HashMap<>());
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
        jdbcContext.makeTransactionUnit(() -> {
            this.board = chessRepository.findBoardById(boardId);
            this.color = chessRepository.findLastColorFromBoardById(boardId);
            this.boardId = boardId;
            return null;
        });
    }

    public void save() {
        jdbcContext.makeTransactionUnit(() -> {
            if (chessRepository.existBoard(boardId)) {
                chessRepository.update(boardId, board.getBoard(), color);
                return null;
            }
            chessRepository.insert(boardId, board.getBoard(), color);
            return null;
        });
    }

    public Board getBoard() {
        return board;
    }
}
