package chess.domain;

import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private ChessBoard chessBoard;
    private final int gameId;

    private ChessGame(int gameId) {
        this.gameId = gameId;
    }

    public static ChessGame create(int gameId) {
        return new ChessGame(gameId);
    }

    public void initialize(Team turn, Map<ChessBoardPosition, ChessPiece> boardData) {
        this.chessBoard = ChessBoard.initialize(turn, boardData);
    }

    public Map<ChessBoardPosition, ChessPiece> getChessBoardInformation() {
        return chessBoard.getMapInformation();
    }

    public boolean isGameEnd() {
        return chessBoard.isKingDie();
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        chessBoard.move(sourcePosition, targetPosition);
    }

    public int getGameId() {
        return gameId;
    }

    public Team getTurn() {
        return chessBoard.getTurn();
    }

    public Map<Team, Double> getTeamScore() {
        Map<Team, Double> teamScore = new HashMap<>();
        teamScore.put(Team.WHITE, chessBoard.calculateScore(Team.WHITE));
        teamScore.put(Team.BLACK, chessBoard.calculateScore(Team.BLACK));
        return teamScore;
    }

    public Team getWinner() {
        double whiteTeamScore = chessBoard.calculateScore(Team.WHITE);
        double blackTeamScore = chessBoard.calculateScore(Team.BLACK);
        if (whiteTeamScore > blackTeamScore) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
