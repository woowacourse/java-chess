package chess.domain.service;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.dto.BoardStatusDto;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.Map;

public class BoardService {
    private Board board = new Board();

    public BoardStatusDto getStatus() {
        return new BoardStatusDto(parseChessBoard(board), parseScore(board), board.getTurn());
    }

    private Map<String, Piece> parseChessBoard(Board board) {
        Map<String, Piece> model = new HashMap<>();
        for (Piece piece : board.getAlivePieces()) {
            model.put(piece.getPosition().toString(), piece);
        }
        return model;
    }

    private Map<String, Double> parseScore(Board board) {
        Map<String, Double> score = new HashMap<>();
        score.put("blackScore", board.calculateScoreByTeam(Team.BLACK));
        score.put("whiteScore", board.calculateScoreByTeam(Team.WHITE));
        return score;
    }

    public void resetBoard() {
        this.board = new Board();
    }

    public void loadGame(Board loadedBoard) {
        this.board = loadedBoard;
    }

    public void movePiece(Position source, Position destination) {
        board.movePiece(source, destination);
    }

    public boolean checkEndOfGame() {
        return !board.isBothKingAlive();
    }

    public Team getWinner() {
        return board.getWinner();
    }
}
