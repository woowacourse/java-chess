package chess.service;

import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.db.dao.WhitePieceDAO;
import chess.domain.chessBoard.ChessBoardState;
import chess.dto.ChessPieceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameSettingService {

    private PieceDAO whitePieceDAO;
    private PieceDAO blackPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameSettingService() {
        whitePieceDAO = new WhitePieceDAO();
        blackPieceDAO = new BlackPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public Map<String, Object> settingChessBoard() {
        Map<String, Object> model = new HashMap<>();
        List<ChessPieceDTO> chessPieceDTOArrayList = new ArrayList<>();

        CreateChessBoardFromDB.createChessBoard(whitePieceDAO,blackPieceDAO,chessBoardStateDAO).getChessBoard()
                .forEach(((key, value) -> chessPieceDTOArrayList.add(
                        new ChessPieceDTO(key.getPositionToString(), value.getName()))
                )
        );

        model.put("chessPiece", chessPieceDTOArrayList);
        return model;
    }
}
