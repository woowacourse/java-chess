package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.exception.TeamNotMatchException;
import java.util.List;

public class Game {
    private final Board board;
    private Team turn;

    public Game() {
        board = new Board();
        turn = Team.WHITE;
    }

    public void move(Square source, Square target) {
        if (isNotMyTurn(source) || board.isEmptyPiece(source)) {
            throw new TeamNotMatchException(turn);
        }
        board.move(source, target);
        turn = turn.nextTurn(turn);
    }

    private boolean isNotMyTurn(Square source) {
        return board.getPiece(source).isAnotherTeam(turn);
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
