import chess.board.ChessBoard;
import chess.team.Team;

import java.util.List;

public class ChessGame {
    private final ChessBoard chessBoard;
    private final Team turn;
    private final List<ChessSet> teams;

    public ChessGame(ChessBoard chessBoard, Team turn, List<ChessSet> teams) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.teams = teams;
    }


}
