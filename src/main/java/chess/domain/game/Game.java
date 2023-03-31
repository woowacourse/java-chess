package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.exception.ErrorCode;
import chess.exception.TeamNotMatchException;
import java.util.List;
import java.util.Map;

public class Game {

    private final Board board;
    private Team turn;

    public Game(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public void move(Square source, Square target) {
        if (isNotCurrentTurn(source) || board.isEmptyPiece(source)) {
            throw new TeamNotMatchException(ErrorCode.TEAM_NOT_MATCH);
        }
        board.move(source, target);
        turn = turn.nextTurn(turn);
    }

    private boolean isNotCurrentTurn(Square square) {
        return board.isSquarePieceNotCurrentTurn(square, turn);
    }

    public boolean isGameEnd() {
        return board.haveOneKing();
    }

    public double calculateScoreOfTeam(Team team) {
        return board.calculateScoreOfTeam(team);
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public Team getTurn() {
        return turn;
    }

    public Map<Square, Piece> getBoard() {
        return board.getBoard();
    }
}
