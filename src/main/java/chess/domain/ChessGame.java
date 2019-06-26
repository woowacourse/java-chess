package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionManager;
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
        ChessDTO chessDTO = new ChessDTO();
        List<String> ranks = new ArrayList<>();
        for (int i = MIN_BOARD_COORDINATE; i <= MAX_BOARD_COORDINATE; i++) {
            StringBuilder rank = new StringBuilder();
            for (int j = MIN_BOARD_COORDINATE; j <= MAX_BOARD_COORDINATE; j++) {
                Piece piece = board.findPiece(PositionManager.getMatchPosition(j, i));
                rank.append((piece.getTeam() == Team.BLACK) ? piece.getSymbol().toUpperCase() : piece.getSymbol());
            }
            ranks.add(rank.toString());
        }
        chessDTO.setRanks(ranks);
        chessDTO.setTurn(turn.toString());
        return chessDTO;
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
