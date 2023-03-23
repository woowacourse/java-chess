package chess.controller;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.PieceName;

import java.util.Map;

public class ChessBoardDTO {
    private static final int BOARD_SIZE = 8;
    private static final char BLANK = '.';

    private final StringBuilder boardMessage = new StringBuilder();

    public ChessBoardDTO(Map<Position, Piece> board) {
        for (int rank = BOARD_SIZE - 1; rank >= 0; rank--) {
            boardMessage.append(makeFileMessage(board, rank))
                    .append(System.lineSeparator());
        }
    }

    private String makeFileMessage(final Map<Position, Piece> board, final int rank) {
        final StringBuilder fileMessage = new StringBuilder();
        for (int file = 0; file < BOARD_SIZE; file++) {
            fileMessage.append(getPieceName(board, rank, file));
        }
        return fileMessage.toString();
    }

    private char getPieceName(final Map<Position, Piece> board, final int rank, final int file) {
        Position position = Position.of(rank, file);
        if (board.containsKey(position)) {
            return PieceName.findMessage(board.get(position));
        }
        return BLANK;
    }

    public StringBuilder getBoardMessage() {
        return boardMessage;
    }
}
