package chess.service;

import chess.dao.PieceDao;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.sql.SQLException;

public class ChessWebService {
    private static final int BOARD_CELLS = 64;
    private static final int BOARD_CELLS_COUNT = BOARD_CELLS;

    private final PieceDao dao;

    public ChessWebService(PieceDao dao) {
        this.dao = dao;
    }

    public boolean canContinue(String user_id) throws SQLException {
        int savedCount = dao.countSavedInfo(user_id);
        return savedCount == BOARD_CELLS_COUNT;
    }

    public String turn(Board board) {
        return board.getTeamColor().name();
    }

    public void move(Board board, String source, String target) {
        Position sourcePosition = Position.ofPositionName(source);
        Position targetPosition = Position.ofPositionName(target);
        board.move(sourcePosition, targetPosition);
    }

    public double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }
}
