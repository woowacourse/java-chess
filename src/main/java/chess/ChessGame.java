package chess;

import chess.board.ChessBoard;
import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

import static chess.Progress.*;
import static chess.team.Team.BLACK;
import static chess.team.Team.WHITE;

// TODO : 점수 계산 로직에서 폰 전략을 빼지 못함
// TODO : 제대로된 점수가 나오는지 로그 찍어서 확인해보기
// TODO : 킹 죽었을 때 게임 종료되는지 확인하기

public class ChessGame {
    private final ChessBoard chessBoard;
    private final Player white;
    private final Player black;

    public ChessGame() {
        chessBoard = new ChessBoard();
        white = new Player(new ChessSet(chessBoard.giveMyPiece(WHITE)), WHITE);
        black = new Player(new ChessSet(chessBoard.giveMyPiece(BLACK)), BLACK);
    }

    public Progress doOneCommand(String command, Team turn) {
        if (command.equals("end")) {
            return END;
        }
        if (command.substring(0, 4).equals("move")) {
            Location now = substring(command, 1);
            Location destination = substring(command, 2);
            if (!chessBoard.canMove(now, destination) || !chessBoard.isCorrectTeam(now, turn)) {
                return ERROR;
            }
            deletePieceIfExistIn(destination, turn);
            chessBoard.move(now, destination);
        }
        return finishIfKingDie();
    }

    private void deletePieceIfExistIn(Location destination, Team turn) {
        Player counterplayer = white;
        if (!black.isSame(turn)) {
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

    private Location substring(String str, int index) {
        int row = str.split(" ")[index].charAt(1) - '0';
        char col = str.split(" ")[index].charAt(0);
        return new Location(row, col);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
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
        if(Result.of(whiteScore, blackScore).isWin()) {
            return new ChessResult(Result.WIN, white.getTeamName());
        }
        if(Result.of(whiteScore, blackScore).isWin()) {
            return new ChessResult(Result.WIN, black.getTeamName());
        }
        return new ChessResult(Result.DRAW, black.getTeamName());
    }

    private Score calculateScore(Player player) {
        Score scoreExceptPawnReduce = player.calculateScoreExceptPawnReduce();
        Score pawnReduceScore = chessBoard.calculateReducePawnScore(player.getTeam());
        return scoreExceptPawnReduce.minus(pawnReduceScore);
    }
}
