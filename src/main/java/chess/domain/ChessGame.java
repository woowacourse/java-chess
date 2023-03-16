package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private boolean isLowerTeamTurn;

    public ChessGame(final Board board) {
        this.isLowerTeamTurn = true;
        this.board = board;
    }

    public void move(final String start, final String end) {
        validateTurn(start);
        board.switchPosition(start, end);
        isLowerTeamTurn = !isLowerTeamTurn;
    }

    private void validateTurn(final String start) {
        if (board.findPiece(start).isNameLowerCase() != isLowerTeamTurn) {
            throw new IllegalArgumentException("상대편 말은 움직일 수 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return board.getChessBoard();
    }
}
