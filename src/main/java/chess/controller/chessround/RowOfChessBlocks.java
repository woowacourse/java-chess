package chess.controller.chessround;

import chess.application.chessround.dto.ChessPieceDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RowOfChessBlocks implements Iterable<ChessBlock> {
    private List<ChessBlock> chessBlocks;

    private RowOfChessBlocks(List<ChessBlock> chessBlocks) {
        this.chessBlocks = chessBlocks;
    }

    static RowOfChessBlocks createEmptyOf(int blockRow) {
        List<ChessBlock> chessBlocks = new ArrayList<>();
        for (int blockColumn = 0; blockColumn < ChessBoard.LENGTH_OF_CHESS_BOARD_SIDE; blockColumn++) {
            ChessBlock chessBlock = new ChessBlock(
                    String.format("%d%d", remapRowFrom(blockRow), remapColumnFrom(blockColumn))
                    , "\u00A0");
            chessBlocks.add(chessBlock);
        }
        return new RowOfChessBlocks(chessBlocks);
    }

    private static int remapRowFrom(int blockRow) {
        return ChessBoard.LENGTH_OF_CHESS_BOARD_SIDE - blockRow;
    }

    private static int remapColumnFrom(int blockColumn) {
        return blockColumn + 1;
    }

    void fillChessPieceTo(ChessPieceDTO chessPieceDTO, boolean isWhitePlayer) {
        int column = chessPieceDTO.getColumn();
        ChessBlock currentBlock = chessBlocks.get(remapBlockColumnFrom(column));

        ChessSymbolGenerator generator = ChessSymbolGenerator.getInstance();
        String chessSymbol = generator.generateSymbol(chessPieceDTO.getName(), isWhitePlayer);
        currentBlock.setSymbol(chessSymbol);
    }

    private int remapBlockColumnFrom(int column) {
        return column - 1;
    }

    @Override
    public Iterator<ChessBlock> iterator() {
        return chessBlocks.iterator();
    }
}
