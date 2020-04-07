package chess.game;

import chess.board.ChessBoardCreater;
import chess.command.Command;
import chess.board.ChessBoard;
import chess.location.Location;
import chess.progress.Progress;
import chess.player.Player;
import chess.result.ChessResult;
import chess.result.ChessScores;
import chess.result.Result;
import chess.score.Calculatable;
import chess.score.Score;
import chess.score.ScoreCalculator;
import chess.team.Team;

import static chess.progress.Progress.*;
import static chess.team.Team.BLACK;
import static chess.team.Team.WHITE;

// TODO : 이제 갱신되는 Player들의 Set가 존재한다 -> 그러니 ChessGame들의 책임들을 옮길 수 있다.
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

    private Player getCounterTurnPlayer(Team turn) {
        Player counterplayer = white;
        if (black.isNotSame(turn)) {
            counterplayer = black;
        }
        return counterplayer;
    }

    public Progress finishIfKingDie() {
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
                white.calculate(),
                black.calculate()
        );
    }

    private ChessResult compareScore() {
        Score whiteScore = white.calculate();
        Score blackScore = black.calculate();

        if (whiteScore.isHigherThan(blackScore)) {
            return new ChessResult(Result.WIN, white.getTeamName());
        }
        if (blackScore.isHigherThan(whiteScore)) {
            return new ChessResult(Result.WIN, black.getTeamName());
        }

        return new ChessResult(Result.DRAW);
    }

    public Team getTurn() {
        return turn;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
