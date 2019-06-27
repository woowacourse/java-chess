package chess.application.chessround;

import chess.application.chessround.dto.*;
import chess.chessview.ChessBlock;
import chess.chessview.ChessBoard;
import chess.chessview.RowOfChessBlocks;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    ChessPieceDTO makeChessPieceDTO(ChessPoint point, ChessPiece piece) {
        return new ChessPieceDTO(point.getRow(), point.getColumn(), piece.getName());
    }

    ChessPointDTO makeChessPointDTO(ChessPoint point) {
        return new ChessPointDTO(point.getRow(), point.getColumn());
    }

    ChessBoardDTO makeChessBoardDTO(ChessBoard chessBoard) {
        List<RowOfChessBlocksDTO> rowOfChessBlocksDTOs = new ArrayList<>();
        for (RowOfChessBlocks rowOfChessBlocks : chessBoard) {
            List<ChessBlock> chessBlocks = rowOfChessBlocks.stream().collect(Collectors.toList());
            rowOfChessBlocksDTOs.add(new RowOfChessBlocksDTO(chessBlocks));
        }
        return new ChessBoardDTO(rowOfChessBlocksDTOs);
    }
}
