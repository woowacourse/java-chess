package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import chess.exception.PieceMessage;
import chess.factory.BoardFactory;
import java.util.Collections;
import java.util.Map;

public class ChessGame {

    private Board board;
    private boolean isLowerTeamTurn;

    public ChessGame(final Board board, final boolean isLowerTeamTurn) {
        this.board = board;
        this.isLowerTeamTurn = isLowerTeamTurn;
    }

    public void move(final String start, final String end) {
        Position startPosition = Position.from(start);
        Position endPosition = Position.from(end);

        validateTurn(startPosition);

        board.switchPosition(startPosition, endPosition);
        isLowerTeamTurn = !isLowerTeamTurn;
    }

    private void validateTurn(final Position start) {
        if (board.findPieceFromPosition(start).isNameLowerCase() != isLowerTeamTurn) {
            throw new IllegalArgumentException(PieceMessage.ONLY_MOVE_MINE.getMessage());
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board.getBoard());
    }

    public double calculateScoreOfLowerTeam() {
        return board.getScoreOfLowerTeam();
    }

    public double calculateScoreOfUpperTeam() {
        return board.getScoreOfUpperTeam();
    }

    public boolean isKingDead() {
        return board.isKingDead();
    }

    public boolean isUpperTeamWin() {
        return board.isUpperTeamWin();
    }

    public void initGame() {
        this.board = BoardFactory.createBoard();
        this.isLowerTeamTurn = true;
    }

    public boolean isLowerTeamTurn() {
        return isLowerTeamTurn;
    }
}
