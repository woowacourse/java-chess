package techcourse.fp.chess.dto.request;

import java.util.Map;
import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Piece;

public class ChessGameRequest {

    private final Map<Position, Piece> board;
    private final String name;
    private final String turn;

    public ChessGameRequest(final Map<Position, Piece> board, final String name, final String turn) {
        this.board = board;
        this.name = name;
        this.turn = turn;
    }

    public static ChessGameRequest create(ChessGame chessGame, String gameName) {
        return new ChessGameRequest(chessGame.getBoard(), gameName, chessGame.getTurn().name());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public String getTurn() {
        return turn;
    }
}
