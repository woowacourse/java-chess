package chess.service;

import chess.dao.ChessDAO;
import chess.domain.Board;
import chess.domain.Rank;
import chess.domain.Score;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PieceDTO;
import chess.dto.RankDTO;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private final ChessDAO chessDAO;
    private int gameId;

    public ChessService(ChessDAO chessDAO) {
        this.chessDAO = chessDAO;
        gameId = createNewChessGame();
    }

    private int createNewChessGame() {
        return chessDAO.saveGame(new Board(), Team.WHITE.name());
    }

    public List<RankDTO> getBoardStatus() {
        return createRankDTO(chessDAO.findBoardByGameId(gameId).getBoard());
    }

    public String getTeam() {
        return chessDAO.findTurnByGameId(gameId).name();
    }

    private List<RankDTO> createRankDTO(Map<Row, Rank> board) {
        return board.values().stream()
                .map(rank -> new RankDTO(createPieceDTO(rank.getPieces())))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private List<PieceDTO> createPieceDTO(Map<Column, Piece> rank) {
        return rank.values().stream()
                .map(piece -> new PieceDTO(piece.getName(), piece.getColValue(), piece.getRowValue(),
                        piece.getTeamName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean move(String source, String destination) {
        Team team = chessDAO.findTurnByGameId(gameId);
        Board board = chessDAO.findBoardByGameId(gameId);

        boolean isKingDead = board.movePiece(Position.from(source), Position.from(destination), team);

        chessDAO.updatePiece(board.getPiece(Position.from(source)), gameId);
        chessDAO.updatePiece(board.getPiece(Position.from(destination)), gameId);
        chessDAO.updateTurn(Team.switchTeam(team), gameId);

        return isKingDead;
    }

    public double getWhiteTeamScore() {
        return Score.calculateScore(chessDAO.findBoardByGameId(gameId).getBoard(), Team.WHITE).getTotalScore();
    }

    public double getBlackTeamScore() {
        return Score.calculateScore(chessDAO.findBoardByGameId(gameId).getBoard(), Team.BLACK).getTotalScore();
    }

    public String getWinnerTeam() {
        return Team.switchTeam(chessDAO.findTurnByGameId(gameId)).name();
    }

    public void restartChessGame() {
        gameId = createNewChessGame();
    }
}
