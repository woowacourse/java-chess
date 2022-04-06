package chess.service;

import chess.dao.ChessDAO;
import chess.dao.GameDAO;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.dto.ChessDTO;
import chess.dto.GameDTO;
import chess.dto.TurnDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessDAO chessDao = new ChessDAO();
    private final GameDAO gameDAO = new GameDAO();

    public void saveGame(String whiteUser, String blackUser, String turn) {
        gameDAO.saveGameInformation(new GameDTO(whiteUser, blackUser), new TurnDTO(turn));
    }

    public void updateTurn(int id, String turn) {
        gameDAO.updateTurn(id, turn);
    }

    public String findTurn(int id) {
        return gameDAO.findTurn(id);
    }

    public int findGameIdByUser(String whiteUser, String blackUser) {
        return gameDAO.findGameIdByUser(new GameDTO(whiteUser, blackUser));
    }

    public void deleteGame(int gameId) {
        gameDAO.deleteGame(gameId);
    }

    public void initBoard(Map<String, Piece> board, int gameId) {
        List<ChessDTO> chessDTOS = new ArrayList<>();
        for (String position : board.keySet()) {
            chessDTOS.add(new ChessDTO(board.get(position).getColor(),
                    board.get(position).getPiece(), position));
        }
        chessDao.savePieces(chessDTOS, gameId);
    }

    public void savePieces(List<ChessDTO> chessDTO, int gameId) {
        chessDao.savePieces(chessDTO, gameId);
    }

    public Map<String, Piece> findPieces(int gameId) {
        List<ChessDTO> findChessDTOS = chessDao.findAllPiece(gameId);
        Map<String, Piece> pieces = new HashMap<>();
        for (ChessDTO chessDto : findChessDTOS) {
            pieces.put(chessDto.getPosition(), Pieces.findPiece(chessDto.getColor(), chessDto.getPiece()));
        }
        return pieces;
    }

    public void deletePiece(String position, int gameId) {
        chessDao.deletePiece(position, gameId);
    }
}
