package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import java.util.Collections;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private boolean isWhiteTeamTurn;

    public ChessGame(final Board board) {
        this.isWhiteTeamTurn = true;
        this.board = board;
    }

    public void move(final String start, final String end) {
        validateTurn(start);
        board.switchPosition(start, end);
        isWhiteTeamTurn = !isWhiteTeamTurn;
    }

    private void validateTurn(final String start) {
        if (board.findPiece(start).isWhiteTeam() != isWhiteTeamTurn) {
            throw new IllegalArgumentException("상대편 말은 움직일 수 없습니다.");
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board.getBoard());
    }
}
