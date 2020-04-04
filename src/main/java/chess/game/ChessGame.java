package chess.game;

import chess.board.ChessBoardCreater;
import chess.command.Command;
import chess.board.ChessBoard;
import chess.location.Location;
import chess.piece.type.Piece;
import chess.progress.Progress;
import chess.player.Player;
import chess.result.ChessResult;
import chess.result.ChessScores;
import chess.result.Result;
import chess.score.Calculatable;
import chess.score.Score;
import chess.score.ScoreCalcultor;
import chess.team.Team;

import static chess.location.LocationSubStringUtil.substring;
import static chess.progress.Progress.*;
import static chess.team.Team.BLACK;
import static chess.team.Team.WHITE;

public class ChessGame {
    private final ChessBoard chessBoard;
    private final Player white;
    private final Player black;
    private Team turn;
    private Calculatable calculatable;

    public ChessGame() {
        chessBoard = ChessBoardCreater.create();
        white = new Player(new ChessSet(chessBoard.giveMyPiece(WHITE)), WHITE);
        black = new Player(new ChessSet(chessBoard.giveMyPiece(BLACK)), BLACK);
        turn = Team.WHITE;
        calculatable = new ScoreCalcultor();
    }

    public void changeTurn() {
        turn = turn.changeTurn();
    }

    // State 패턴
    public Progress doOneCommand(Command command) {
        return command.conduct();
    }

    public Progress doMoveCommand(String command) {
        Location now = substring(command, 1);
        Location destination = substring(command, 2);

        if (chessBoard.isNotExist(now)
                || chessBoard.canNotMove(now, destination)
                || chessBoard.isNotCorrectTeam(now, turn)) {
            return Progress.ERROR;
        }

        deletePieceIfExistIn(destination, turn);
        chessBoard.move(now, destination);

        return finishIfKingDie();
    }

    public Progress webDoMoveCommand(Location now, Location destination) {
        if (chessBoard.isNotExist(now)
                || chessBoard.canNotMove(now, destination)
                || chessBoard.isNotCorrectTeam(now, turn)) {
            return Progress.ERROR;
        }

        deletePieceIfExistIn(destination, turn);
        chessBoard.move(now, destination);

        return finishIfKingDie();
    }

    private void deletePieceIfExistIn(Location location, Team turn) {
        Player counterplayer = getCounterTurnPlayer(turn);
        if (chessBoard.isExistPieceIn(location)) {
            Piece toBeRemovedPiece = chessBoard.getPieceIn(location);
            counterplayer.deletePieceIfExistIn(toBeRemovedPiece);
        }
    }

    private Player getCounterTurnPlayer(Team turn) {
        Player counterplayer = white;
        if (black.isNotSame(turn)) {
            counterplayer = black;
        }
        return counterplayer;
    }

    private Progress finishIfKingDie() {
        if (isExistKingDiePlayer()) {
            return END;
        }
        return Progress.CONTINUE;
    }

    private boolean isExistKingDiePlayer() {
        return white.hasNotKing() || black.hasNotKing();
    }

    public ChessResult findWinner() {
        if (isExistKingDiePlayer()) {
            if (white.hasNotKing()) {
                return new ChessResult(Result.WIN, black.getTeamName());
            }
            return new ChessResult(Result.WIN, white.getTeamName());
        }
        return compareScore();
    }

    public ChessScores calculateScores() {
        return new ChessScores(
                calculatable.calculate(chessBoard, white),
                calculatable.calculate(chessBoard, black)
        );
    }

    private ChessResult compareScore() {
        Score whiteScore = calculatable.calculate(chessBoard, white);
        Score blackScore = calculatable.calculate(chessBoard, black);
        if (whiteScore.isHigherThan(blackScore)) {
            return new ChessResult(Result.WIN, white.getTeamName());
        }
        if (blackScore.isHigherThan(whiteScore)) {
            return new ChessResult(Result.WIN, black.getTeamName());
        }
        return new ChessResult(Result.DRAW, black.getTeamName());
    }

    public Team getTurn() {
        return turn;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
