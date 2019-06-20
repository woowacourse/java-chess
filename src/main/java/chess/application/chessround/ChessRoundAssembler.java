package chess.application.chessround;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPlayer;
import chess.domain.chessround.dto.ChessPieceDTO;
import chess.domain.chessround.dto.ChessPlayerDTO;

import java.util.ArrayList;
import java.util.List;

class ChessRoundAssembler {
    private static ChessRoundAssembler chessRoundAssembler = null;

    private ChessRoundAssembler() {
    }

    static ChessRoundAssembler getInstance() {
        if (chessRoundAssembler == null) {
            chessRoundAssembler = new ChessRoundAssembler();
        }
        return chessRoundAssembler;
    }

    ChessPlayerDTO makeChessPlayerDTO(ChessPlayer whitePlayer) {
        List<ChessPieceDTO> chessPieceDTOs = new ArrayList<>();

        for (ChessPoint point : whitePlayer.getAllChessPoints()) {
            ChessPiece chessPiece = whitePlayer.get(point);
            ChessPieceDTO chessPieceDTO = new ChessPieceDTO(point.getRow(), point.getColumn(), chessPiece.getName());
            chessPieceDTOs.add(chessPieceDTO);
        }
        return new ChessPlayerDTO(chessPieceDTOs);
    }
}
