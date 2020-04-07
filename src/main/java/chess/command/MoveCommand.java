package chess.command;

import chess.board.ChessBoard;
import chess.game.ChessGame;
import chess.location.Location;
import chess.location.LocationSubStringUtil;
import chess.progress.Progress;
import chess.team.Team;

public class MoveCommand implements Command {
    private static final String SPACE = " ";
    private static final int NOW_INDEX_IN_MOVE_COMMAND = 1;
    private static final int DESTINATION_INDEX_IN_MOVE_COMMAND = 2;

    private final Location now;
    private final Location destination;
    private final ChessGame chessGame;

    private MoveCommand(Location now
            , Location destination
            , ChessGame chessGame) {
        this.now = now;
        this.destination = destination;
        this.chessGame = chessGame;
    }

    @Override
    public Progress conduct() {
        ChessBoard chessBoard = chessGame.getChessBoard();
        Team turn = chessGame.getTurn();

        if (chessBoard.isNotExist(now)
                || chessBoard.canNotMove(now, destination)
                || chessBoard.isNotCorrectTeam(now, turn)) {
            return Progress.ERROR;
        }
        chessGame.deletePieceIfExistIn(destination, turn);

        chessBoard.move(now, destination);
        chessGame.movePieceInPlayerChessSet(now, destination);

        return chessGame.finishIfKingDie();
    }

    public static MoveCommand of(String rawCommand, ChessGame chessGame) {
        String now = rawCommand.split(SPACE)[NOW_INDEX_IN_MOVE_COMMAND];
        String destination = rawCommand.split(SPACE)[DESTINATION_INDEX_IN_MOVE_COMMAND];

        Location nowLocation = LocationSubStringUtil.substring(now);
        Location destinationLocation = LocationSubStringUtil.substring(destination);

        return new MoveCommand(nowLocation, destinationLocation, chessGame);
    }

    public static MoveCommand of(Location now, Location destination, ChessGame chessGame) {
        return new MoveCommand(now, destination, chessGame);
    }

    public Location getNow() {
        return now;
    }

    public Location getDestination() {
        return destination;
    }
}
