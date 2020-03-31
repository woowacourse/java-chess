package chess.game;

import chess.command.Command;
import chess.board.ChessBoard;
import chess.location.Location;
import chess.progress.Progress;
import chess.player.Player;
import chess.result.ChessResult;
import chess.result.Result;
import chess.score.Score;
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

    public ChessGame() {
        chessBoard = new ChessBoard();
        white = new Player(new ChessSet(chessBoard.giveMyPiece(WHITE)), WHITE);
        black = new Player(new ChessSet(chessBoard.giveMyPiece(BLACK)), BLACK);
        turn = Team.WHITE;
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

    private void deletePieceIfExistIn(Location destination, Team turn) {
        Player counterplayer = white;
        if (black.isNotSame(turn)) {
            counterplayer = black;
        }
        counterplayer.deletePieceIfExistIn(destination);
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

    private ChessResult compareScore() {
        Score whiteScore = calculateScore(white);
        Score blackScore = calculateScore(black);
        if (whiteScore.isHigherThan(blackScore)) {
            return new ChessResult(Result.WIN, white.getTeamName());
        }
        if (blackScore.isHigherThan(whiteScore)) {
            return new ChessResult(Result.WIN, black.getTeamName());
        }
        return new ChessResult(Result.DRAW, black.getTeamName());
    }

    private Score calculateScore(Player player) {
        Score scoreExceptPawnReduce = player.calculateScoreExceptPawnReduce();
        Score pawnReduceScore = chessBoard.calculateReducePawnScore(player.getTeam());
        return scoreExceptPawnReduce.minus(pawnReduceScore);
    }

    public Team getTurn() {
        return turn;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
