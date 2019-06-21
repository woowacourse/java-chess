package chess.controller.chessround;

import chess.domain.chessround.dto.ChessPieceDTO;
import chess.domain.chessround.dto.ChessPlayerDTO;

import java.util.ArrayList;
import java.util.List;

class ChessBoard {
    static final int LENGTH_OF_CHESS_BOARD_SIDE = 8;
    private List<RowOfChessBlocks> chessRows;

    private ChessBoard(List<RowOfChessBlocks> chessRows) {
        this.chessRows = chessRows;
    }

    static ChessBoard createEmpty() {
        List<RowOfChessBlocks> chessRows = new ArrayList<>();
        for (int blockRow = 0; blockRow < 8; blockRow++) {
            chessRows.add(RowOfChessBlocks.createEmptyOf(blockRow));
        }
        return new ChessBoard(chessRows);
    }

    void fillWhiteChessPiecesOfPlayer(ChessPlayerDTO chessPlayerDTO) {
        fillChessPiecesOfPlayer(chessPlayerDTO, true);
    }

    void fillBlackChessPiecesOfPlayer(ChessPlayerDTO chessPlayerDTO) {
        fillChessPiecesOfPlayer(chessPlayerDTO, false);
    }

    private void fillChessPiecesOfPlayer(ChessPlayerDTO chessPlayerDTO, boolean isWhitePlayer) {
        for (ChessPieceDTO chessPieceDTO : chessPlayerDTO.getChessPieceDTOs()) {
            fillChessPieceTo(chessPieceDTO, isWhitePlayer);
        }
    }

    private void fillChessPieceTo(ChessPieceDTO chessPieceDTO, boolean isWhitePlayer) {
        int row = chessPieceDTO.getRow();

        RowOfChessBlocks chessRow = chessRows.get(remapBlockRowFrom(row));
        chessRow.fillChessPieceTo(chessPieceDTO, isWhitePlayer);
    }

    private int remapBlockRowFrom(int row) {
        return LENGTH_OF_CHESS_BOARD_SIDE - row;
    }
}
