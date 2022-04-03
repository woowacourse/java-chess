package chess.dto;

import chess.domain.ChessGame;

public class GameResultDTO {
    private final String result;
    private final String enemyName;
    private final String team;
    private final double myScore;
    private final double enemyScore;

    private GameResultDTO(String result, String enemyName, String team, double myScore, double enemyScore) {
        this.result = result;
        this.enemyName = enemyName;
        this.team = team;
        this.myScore = myScore;
        this.enemyScore = enemyScore;
    }

    public static GameResultDTO toResultDTO(ChessGame game, Long memberId) {
        String winResult = findWinResult(game, memberId);
        String enemyName = findEnemyName(game, memberId);
        String team = findTeam(game, memberId);
        double myScore = findMyScore(game, memberId);
        double enemyScore = findEnemyScore(game, memberId);
        return new GameResultDTO(winResult, enemyName, team, myScore, enemyScore);
    }

    private static String findWinResult(ChessGame game, Long memberId) {
        if (game.isTerminated()) {
            return "강제종료";
        }
        if (game.getWinnerId().equals(memberId)) {
            return "승";
        }
        return "패";
    }

    private static String findEnemyName(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return game.getParticipant().getWhiteName();
        }
        return game.getParticipant().getBlackName();
    }

    private static String findTeam(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return "흑";
        }
        return "백";
    }

    private static double findMyScore(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return game.getBlackScore();
        }
        return game.getWhiteScore();
    }

    private static double findEnemyScore(ChessGame game, Long memberId) {
        if (game.getParticipant().getBlackId().equals(memberId)) {
            return game.getWhiteScore();
        }
        return game.getBlackScore();
    }

    public String getResult() {
        return result;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getTeam() {
        return team;
    }

    public double getMyScore() {
        return myScore;
    }

    public double getEnemyScore() {
        return enemyScore;
    }
}
