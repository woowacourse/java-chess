package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGameProgressor {

    private final ChessGame chessGame;
    private final GameSwitch gameSwitch;

    public ChessGameProgressor() {
        this.chessGame = new ChessGame(Board.create());
        this.gameSwitch = new GameSwitch();
    }

    public Map<Position, Piece> getCurrentBoard() {
        return chessGame.getBoard();
    }
}
