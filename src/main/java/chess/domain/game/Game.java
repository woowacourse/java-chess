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
        this.board = new Board();
        this.turn = Team.WHITE;
    }

    public void move(final Square source, final Square target) {
        if (this.isNotMyTurn(source) || this.board.isEmptyPiece(source)) {
            throw new TeamNotMatchException(this.turn);
        }
        this.board.move(source, target);
        this.turn = this.turn.nextTurn(this.turn);
    }

    private boolean isNotMyTurn(final Square source) {
        return this.board.getPiece(source).isAnotherTeam(this.turn);
    }

    public List<Piece> getPieces() {
        return this.board.getPieces();
    }
}
