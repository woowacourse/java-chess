package chess.result;

import chess.player.Player;
import chess.score.Score;

class ChessResultCreater {
    static ChessResult findWinner(Player white, Player black) {
        if (isExistKingDiePlayer(white, black)) {
            return decideChessResultIfKingDie(white, black);
        }
        return compareScore(white, black);
    }

    private static boolean isExistKingDiePlayer(Player white, Player black) {
        return white.hasNotKing() || black.hasNotKing();
    }

    private static ChessResult decideChessResultIfKingDie(Player white, Player black) {
        if (white.hasNotKing()) {
            return new ChessResult(Result.WIN, black.getTeamName());
        }
        return new ChessResult(Result.WIN, white.getTeamName());
    }

    private static ChessResult compareScore(Player white, Player black) {
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
}
