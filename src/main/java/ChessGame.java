import chess.board.ChessBoard;
import chess.board.Location;
import chess.team.Team;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private final ChessBoard chessBoard;
    private final List<Player> players;

    public ChessGame() {
        chessBoard = new ChessBoard();
        Player white = new Player(new ChessSet(chessBoard.giveMyPiece(Team.WHITE.isBlack())), Team.WHITE);
        Player black = new Player(new ChessSet(chessBoard.giveMyPiece(Team.BLACK.isBlack())), Team.BLACK);
        players = new ArrayList<>();
        players.add(white);
        players.add(black);
    }

    public Progress doOneCommand(String command, Team turn) {
        if (command.equals("end")) {
            return Progress.END;
        }
        if (command.substring(0, 4).equals("move")) {
            Location now = substring(command, 1);
            Location destination = substring(command, 2);
            if (!chessBoard.canMove(now, destination) || !chessBoard.isCorrectTeam(now, turn)) {
                return Progress.ERROR;
            }
            chessBoard.move(now, destination);
            deletePieceIfExistIn(destination, turn);
        }
        return finishIfKingDie();
    }

    private void deletePieceIfExistIn(Location destination, Team turn) {
        Player present = players.stream()
                .filter(player -> player.isNotSameTeam(turn))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        present.deletePieceIfExistIn(destination);
    }

    private Progress finishIfKingDie() {
        if(isExistKingDiePlayer()) {
            return Progress.END;
        }
        return Progress.CONTINUE;
    }

    private boolean isExistKingDiePlayer() {
        return players.stream().anyMatch(Player::hasNotKing);
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
