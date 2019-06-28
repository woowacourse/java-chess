package chess.application.chessround;

import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPlayerDTO;
import chess.application.chessround.dto.ChessPointDTO;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieces;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ChessPieceDTO chessPieceDTO = makeChessPieceDTO(point, chessPiece);
            chessPieceDTOs.add(chessPieceDTO);
        }
        return new ChessPlayerDTO(chessPieceDTOs);
    }

    ChessPlayer makeChessPlayerFrom(List<ChessPieceDTO> chessPieceDTOs, boolean isWhiteTeam) {
        Map<ChessPoint, ChessPiece> alivePieces = new HashMap<>();
        for (ChessPieceDTO chessPieceDTO : chessPieceDTOs) {
            ChessPoint chessPoint = makeChessPointFrom(chessPieceDTO);
            ChessPiece chessPiece = makeChessPieceFrom(chessPieceDTO, isWhiteTeam);

            alivePieces.put(chessPoint, chessPiece);
        }
        return ChessPlayer.from(alivePieces);
    }

    ChessPieceDTO makeChessPieceDTO(ChessPoint point, ChessPiece piece) {
        return new ChessPieceDTO(point.getRow(), point.getColumn(), piece.getName());
    }

    ChessPiece makeChessPieceFrom(ChessPieceDTO chessPieceDTO, boolean isWhiteTeam) {
        String chessPieceName = chessPieceDTO.getName();
        ChessPieces chessPieces = ChessPieces.getInstance();

        return chessPieces.find(chessPieceName, isWhiteTeam);
    }

    ChessPointDTO makeChessPointDTO(ChessPoint point) {
        return new ChessPointDTO(point.getRow(), point.getColumn());
    }

    ChessPoint makeChessPointFrom(ChessPieceDTO chessPieceDTO) {
        int row = chessPieceDTO.getRow();
        int column = chessPieceDTO.getColumn();

        return ChessPoint.of(row, column);
    }
}
