package chess.domain;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.dto.ChessDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessGame {
    private static final int MIN_BOARD_COORDINATE = 1;
    private static final int MAX_BOARD_COORDINATE = 8;
    private Board board;
    private Team turn;

    public ChessGame() {
        this.board = new Board();
        turn = Team.WHITE;
    }

    public ChessGame(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public void play(Position source, Position target) {
        if (board.movable(source, target, turn)) {
            board.move(source, target);
        }
        turn = Team.switchTeam(turn);
    }

    public Team getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }

    public double getStatus(Team team) {
        return board.getScore(team);
    }

    public boolean isGameEnd() {
        return board.isKingDead();
    }

    public ChessDTO toDTO() {
        List<String> ranks = new ArrayList<>();
        for (int row = MIN_BOARD_COORDINATE; row <= MAX_BOARD_COORDINATE; row++) {
            makeRank(ranks, row);
        }

        return new ChessDTO(ranks, turn.toString());
    }

    private void makeRank(List<String> ranks, int row) {
        StringBuilder rankBuilder = new StringBuilder();
        for (int col = MIN_BOARD_COORDINATE; col <= MAX_BOARD_COORDINATE; col++) {
            Piece piece = board.findPiece(Positions.matchWith(col, row));
            rankBuilder.append((piece.isOurPiece(Team.BLACK)) ? piece.getSymbol().toUpperCase() : piece.getSymbol());
        }
        ranks.add(rankBuilder.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) &&
                turn == chessGame.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, turn);
    }
}
