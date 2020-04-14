package chess.service;

import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.CreateBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessPieceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameSetting extends createBoard {

    public ChessGameSetting() {
        super();
    }

    public Map<String, Object> settingChessBoard() {
        Map<String, Object> model = new HashMap<>();
        List<ChessPieceDTO> chessPieceDTOArrayList = new ArrayList<>();

        createChessBoard().getChessBoard().forEach(((key, value) -> chessPieceDTOArrayList.add(
                new ChessPieceDTO(key.getPositionToString(), value.getName()))
                )
        );

        model.put("chessPiece", chessPieceDTOArrayList);
        return model;
    }
}
