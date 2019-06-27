package chess.application.chessround.dto;

import java.util.Iterator;
import java.util.List;

public class ChessBoardDTO implements Iterable<RowOfChessBlocksDTO> {
    private List<RowOfChessBlocksDTO> rowOfChessBlocksDTOs;

    public ChessBoardDTO(List<RowOfChessBlocksDTO> rowOfChessBlocksDTOs) {
        this.rowOfChessBlocksDTOs = rowOfChessBlocksDTOs;
    }

    public List<RowOfChessBlocksDTO> getRowOfChessBlocksDTOs() {
        return rowOfChessBlocksDTOs;
    }

    @Override
    public Iterator<RowOfChessBlocksDTO> iterator() {
        return rowOfChessBlocksDTOs.iterator();
    }
}
