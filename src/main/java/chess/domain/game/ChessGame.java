package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import java.util.Collections;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(final Board board) {
        this.turn = Turn.WHITE_TEAM_TURN;
        this.board = board;
    }

    public void move(final Position source, final Position destination) {
        validateTurn(source);
        board.isMovable(source, destination);
        board.switchPosition(source, destination);
        switchTurn();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board.getBoard());
    }

    private void validateTurn(final Position source) {
        if (!turn.isCorrectTurn(board.findPiece(source))) {
            throw new IllegalArgumentException("상대편 말은 움직일 수 없습니다.");
        }
    }

    private void switchTurn() {
        if (turn.isWhiteTeamTurn()) {
            turn = Turn.BLACK_TEAM_TURN;
            return;
        }
        if (turn.isBlackTeamTurn()) {
            turn = Turn.WHITE_TEAM_TURN;
        }
    }
}
