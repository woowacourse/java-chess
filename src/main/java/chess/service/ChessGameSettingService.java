package chess.service;

import chess.db.dao.*;
import chess.domain.chessBoard.ChessBoardState;
import chess.dto.ChessPieceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameSettingService {

    private ChessPieceDAO chessPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameSettingService() {
        chessPieceDAO = new ChessPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public Map<String, Object> settingChessBoard() {
        Map<String, Object> model = new HashMap<>();
        List<ChessPieceDTO> chessPieceDTOArrayList = new ArrayList<>();

        CreateChessBoardFromDB.createChessBoard(chessPieceDAO,chessBoardStateDAO).getChessBoard()
                .forEach(((key, value) -> chessPieceDTOArrayList.add(
                        new ChessPieceDTO(key.getPositionToString(), value.getName()))
                )
        );

        model.put("chessPiece", chessPieceDTOArrayList);
        return model;
    }
}
