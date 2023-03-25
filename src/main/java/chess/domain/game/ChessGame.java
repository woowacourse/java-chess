package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.King;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Team;
import java.util.Collections;
import java.util.Map;

public class ChessGame {

    private static final Piece WHITE_TEAM_KING = new King(Team.WHITE);
    private static final Piece BLACK_TEAM_KING = new King(Team.BLACK);
    private static final int GAME_KING_COUNT = 2;

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

    public Score calculateScore() {
        return Score.fromBoard(board);
    }

    public boolean isGameOver() {
        return generateKingCount() < GAME_KING_COUNT;
    }

    private long generateKingCount() {
        return board.getBoard().values().stream()
            .filter(p -> p.equals(WHITE_TEAM_KING) || p.equals(BLACK_TEAM_KING))
            .count();
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
