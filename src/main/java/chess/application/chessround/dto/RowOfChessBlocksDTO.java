package chess.application.chessround.dto;

import chess.chessview.ChessBlock;

import java.util.Iterator;
import java.util.List;

public class RowOfChessBlocksDTO implements Iterable<ChessBlock> {
    private List<ChessBlock> chessBlockDTOs;

    public RowOfChessBlocksDTO(List<ChessBlock> chessBlockDTOs) {
        this.chessBlockDTOs = chessBlockDTOs;
    }

    public List<ChessBlock> getChessBlockDTOs() {
        return chessBlockDTOs;
    }

    @Override
    public Iterator<ChessBlock> iterator() {
        return chessBlockDTOs.iterator();
    }
}
