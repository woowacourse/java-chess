package chess.service;

import chess.dao.ChessDAO;
import chess.dao.GameDAO;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.dto.ChessDTO;
import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
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

    public void updateTurn(GameIdDTO gameIdDTO, String turn) {
        gameDAO.updateTurn(gameIdDTO, turn);
    }

    public TurnDTO findTurn(GameIdDTO gameIdDTO) {
        return new TurnDTO(gameDAO.findTurn(gameIdDTO));
    }

    public GameIdDTO findGameIdByUser(String whiteUser, String blackUser) {
        return new GameIdDTO(gameDAO.findGameIdByUser(new GameDTO(whiteUser, blackUser)));
    }

    public void deleteGame(GameIdDTO gameIdDTO) {
        gameDAO.deleteGame(gameIdDTO);
    }

    public void initBoard(Map<String, Piece> board, GameIdDTO gameIdDTO) {
        List<ChessDTO> chessDTOS = new ArrayList<>();
        for (String position : board.keySet()) {
            chessDTOS.add(new ChessDTO(board.get(position).getColor(),
                    board.get(position).getPiece(), position));
        }
        chessDao.savePieces(chessDTOS, gameIdDTO);
    }

    public void savePieces(List<ChessDTO> chessDTO, GameIdDTO id) {
        chessDao.savePieces(chessDTO, id);
    }

    public Map<String, Piece> findPieces(GameIdDTO gameIdDTO) {
        List<ChessDTO> findChessDTOS = chessDao.findAllPiece(gameIdDTO);
        Map<String, Piece> pieces = new HashMap<>();
        for (ChessDTO chessDto : findChessDTOS) {
            pieces.put(chessDto.getPosition(), Pieces.findPiece(chessDto.getColor(), chessDto.getPiece()));
        }
        return pieces;
    }

    public void deletePiece(String position, GameIdDTO gameIdDTO) {
        chessDao.deletePiece(position, gameIdDTO);
    }
}
