import chess.board.ChessBoard;
import chess.board.Location;
import chess.team.Team;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private final ChessBoard chessBoard;
    private Team turn;
    private final List<ChessSet> teams;

    public ChessGame() {
        chessBoard = new ChessBoard();
        turn = Team.WHITE;
        teams = new ArrayList<>();
        teams.add(new ChessSet(chessBoard.giveMyPiece(Team.WHITE.isBlack())));
        teams.add(new ChessSet(chessBoard.giveMyPiece(Team.BLACK.isBlack())));
    }

    public ChessGame(ChessBoard chessBoard, Team turn, List<ChessSet> teams) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.teams = teams;
    }

    public Progress doOneCommand(String command) {
        if (command.equals("end")) {
            return Progress.END;
        }
        if (command.substring(0, 4).equals("move")) {
            Location now = substring(command, 1);
            Location destination = substring(command, 2);
            if (!chessBoard.canMove(now, destination) || !chessBoard.isCorrectTeam(now, this.turn)) {
                return Progress.ERROR;
            }
            chessBoard.move(now, destination);
        }
        turn = turn.changeTurn();
        return Progress.CONTINUE;
    }

    private Location substring(String str, int index) {
        int row = str.split(" ")[index].charAt(1) - '0';
        char col = str.split(" ")[index].charAt(0);
        return new Location(row, col);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
