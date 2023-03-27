package chess.domain;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static final String INVALID_KING_MESSAGE = "King이 체스판 내에 없습니다.";
    public static final double INIT_SCORE = 0.0;
    public static final int HANDICAP_PAWN_COUNT = 1;
    public static final double HANDICAP_PAWN_SCORE = 0.5;
    private final Team winner;
    private final Map<Team, Double> score;

    private Result(Team winner, Map<Team, Double> score) {
        this.winner = winner;
        this.score = score;
    }

    static Result from(ChessGame chessGame) {
        Map<Team, Double> score = initScore();
        for (Position position : chessGame.getBoard().keySet()) {
            calculateScore(score, chessGame, position);
        }
        return new Result(determineWinner(chessGame), score);
    }

    private static Map<Team, Double> initScore() {
        Map<Team, Double> score = new HashMap<>();
        score.put(Team.WHITE, INIT_SCORE);
        score.put(Team.BLACK, INIT_SCORE);
        return score;
    }

    private static void calculateScore(Map<Team, Double> score, ChessGame chessGame, Position position) {
        Piece piece = chessGame.findPiece(position);
        Team team = piece.getTeam();
        if (team == Team.EMPTY) {
            return;
        }
        if (piece.getPieceType() == PieceType.PAWN && chessGame.pawnCountByColumnAndTeam(position, team) > HANDICAP_PAWN_COUNT) {
            score.put(team, score.get(team) + HANDICAP_PAWN_SCORE);
            return;
        }
        score.put(team, score.get(team) + Score.findScoreBy(piece));
    }

    private static Team determineWinner(ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            return Team.EMPTY;
        }
        return chessGame.getBoard().keySet().stream()
                .map(chessGame::findPiece)
                .filter(piece -> piece.getPieceType() == PieceType.KING)
                .map(Piece::getTeam)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_KING_MESSAGE));
    }

    public Team getWinner() {
        return winner;
    }

    public Map<Team, Double> getScore() {
        return score;
    }
}
