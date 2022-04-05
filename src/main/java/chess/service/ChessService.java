package chess.service;

import chess.domain.Board;
import chess.domain.Rank;
import chess.domain.Score;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.BoardDTO;
import chess.dto.PieceDTO;
import chess.dto.RankDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private final Board board;
    private Team team;

    public ChessService() {
        board = new Board();
        team = Team.WHITE;
    }

    public List<RankDTO> getBoardStatus() {
        return createRankDTO(board.getBoard());
    }

    private List<RankDTO> createRankDTO(Map<Row, Rank> board) {
        return board.values().stream()
                .map(rank -> new RankDTO(createPieceDTO(rank.getPieces())))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private List<PieceDTO> createPieceDTO(Map<Column, Piece> rank) {
        return rank.values().stream()
                .map(piece -> new PieceDTO(piece.getName(), piece.getColValue(), piece.getRowValue(), piece.getTeamName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public double getWhiteTeamScore() {
        return Score.calculateScore(board.getBoard(), Team.WHITE).getTotalScore();
    }

    public double getBlackTeamScore() {
        return Score.calculateScore(board.getBoard(), Team.BLACK).getTotalScore();
    }

    public boolean move(String source, String destination) {
        boolean isKingDead = board.movePiece(Position.from(source), Position.from(destination), team);
        team = Team.switchTeam(team);
        return isKingDead;
    }

    public String getWinnerTeam() {
        return team.name();
    }
}
