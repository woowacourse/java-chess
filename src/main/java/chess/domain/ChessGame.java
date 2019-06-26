package chess.domain;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionManager;
import chess.dto.ChessDTO;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private Board board = new Board();
    private Team team = Team.WHITE;

    public void play(Position source, Position target) {
        if (board.movable(source, target, team)) {
            board.move(source, target);
        }
        team = Team.switchTeam(team);
    }

    public Team getTeam() {
        return team;
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
        for (int i = 1; i <= 8; i++) {
            StringBuilder rank = new StringBuilder();
            for (int j = 1; j <= 8; j++) {
                Piece piece = board.findPiece(PositionManager.getMatchPosition(i, j));
                rank.append((piece.getTeam() == Team.BLACK) ? piece.getSymbol().toUpperCase() : piece.getSymbol());
            }
            ranks.add(rank.toString());
        }
        chessDTO.setRanks(ranks);
        chessDTO.setTurn(team.toString());
        return chessDTO;
    }
}
