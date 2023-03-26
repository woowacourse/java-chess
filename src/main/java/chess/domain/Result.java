package chess.domain;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static final String INVALID_KING_MESSAGE = "King이 체스판 내에 없습니다.";
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
        score.put(Team.WHITE, 0.0);
        score.put(Team.BLACK, 0.0);
        return score;
    }

    private static void calculateScore(Map<Team, Double> score, ChessGame chessGame, Position position) {
        Piece piece = chessGame.findPiece(position);
        Team team = piece.getTeam();
        if (team == Team.EMPTY) {
            return;
        }
        if (piece.getPieceType() == PieceType.PAWN && chessGame.pawnCountByColumnAndTeam(position, team) > 1) {
            score.put(team, score.get(team) + 0.5);
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
