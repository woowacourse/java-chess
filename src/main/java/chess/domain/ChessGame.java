package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Player;
import chess.domain.player.Players;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private final Players players;

    public ChessGame(Map<Point, Piece> board) {
        this.board = new Board(board);
        this.players = new Players(new Player(Team.WHITE), new Player(Team.BLACK));
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }
}
