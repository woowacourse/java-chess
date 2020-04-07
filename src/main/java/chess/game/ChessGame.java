package chess.game;

import chess.board.ChessBoardCreater;
import chess.command.Command;
import chess.board.ChessBoard;
import chess.location.Location;
import chess.progress.Progress;
import chess.player.Player;
import chess.result.ChessResult;
import chess.result.ChessScores;
import chess.team.Team;

import static chess.team.Team.BLACK;
import static chess.team.Team.WHITE;

public class ChessGame {
    private final ChessBoard chessBoard;
    private final Player white;
    private final Player black;
    private Team turn;

    public ChessGame() {
        this(ChessBoardCreater.create(), Team.WHITE);
    }

    public ChessGame(ChessBoard chessBoard, Team turn) {
        this.chessBoard = chessBoard;
        white = new Player(new ChessSet(chessBoard.giveMyPiece(WHITE)), WHITE);
        black = new Player(new ChessSet(chessBoard.giveMyPiece(BLACK)), BLACK);
        this.turn = turn;
    }

    public void changeTurn() {
        turn = turn.changeTurn();
    }

    public Progress doOneCommand(Command command) {
        return command.conduct();
    }

    public void movePieceInPlayerChessSet(Location now, Location destination) {
        if (white.is(turn)) {
            white.movePiece(now, destination);
            return;
        }
        black.movePiece(now, destination);
    }

    public void deletePieceIfExistIn(Location location, Team turn) {
        Player counterplayer = getCounterTurnPlayer(turn);
        if (chessBoard.isExistPieceIn(location)) {
            counterplayer.deletePieceIfExistIn(location);
        }
    }

    public ChessResult findWinner() {
        return ChessResult.of(white, black);
    }

    private Player getCounterTurnPlayer(Team turn) {
        Player counterplayer = white;
        if (black.isNotSame(turn)) {
            counterplayer = black;
        }
        return counterplayer;
    }

    public ChessScores calculateScores() {
        return new ChessScores(
                white.calculate(),
                black.calculate()
        );
    }

    public Team getTurn() {
        return turn;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
