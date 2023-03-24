package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.EMPTY;
import static chess.domain.piece.Team.WHITE;

public class ChessGame {

    private final Board board;
    private Team turn;

    private ChessGame(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public static ChessGame create() {
        return new ChessGame(BoardGenerator.createBoard(), WHITE);
    }

    public void movePiece(Position from, Position to) {
        validateTurn(from);
        board.movePiece(from, to);
        changeTurn();
    }

    private void validateTurn(Position from) {
        if (board.isTeamInPositionMatched(from, turn)) {
            return;
        }
        if (turn.isBlack()) {
            throw new IllegalArgumentException("검정색 기물을 움직여 주세요.");
        }
        throw new IllegalArgumentException("하얀색 기물을 움직여 주세요.");
    }

    private void changeTurn() {
        if (isGameEnd()) {
            turn = EMPTY;
            return;
        }
        if (turn.isBlack()) {
            turn = WHITE;
            return;
        }
        turn = BLACK;
    }

    public boolean isGameEnd() {
        return board.isKingDead(WHITE) || board.isKingDead(BLACK);
    }

    public List<List<Piece>> getBoard() {
        return board.getBoard();
    }

    public double getTotalScore(Team team) {
        double basicScore = board.getScores(team)
                .stream()
                .mapToDouble(score -> score)
                .sum();
        return basicScore - board.getMinusScore(team);
    }

    public Team getWinTeam() {
        if (board.isKingDead(WHITE)) {
            return BLACK;
        }
        if (board.isKingDead(BLACK)) {
            return WHITE;
        }

        return decideWinTeamByScore();
    }

    private Team decideWinTeamByScore() {
        double whiteTeamScore = getTotalScore(WHITE);
        double blackTeamScore = getTotalScore(BLACK);
        if (whiteTeamScore > blackTeamScore) {
            return WHITE;
        }
        if (whiteTeamScore < blackTeamScore) {
            return BLACK;
        }
        return EMPTY;
    }

    public Team getTurn() {
        return turn;
    }
}
