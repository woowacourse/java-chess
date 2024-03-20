package view;

import domain.*;
import view.mapper.PieceMapper;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY = ".";

    public MessageResolver() {
    }

    public String resolveChessBoardMessage(ChessBoard chessBoard) {
        StringBuilder message = new StringBuilder();
        for (int vertical = Rank.max(); vertical >= 0; vertical--) {
            StringBuilder rank = resolveRankMessage(File.find(vertical), chessBoard);
            message.append(rank);
        }
        return message.toString();
    }

    private StringBuilder resolveRankMessage(File file, ChessBoard chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int horizontal = 0; horizontal <= Rank.max(); horizontal++) {
            Position position = new Position(Rank.find(horizontal), file);
            String square = resolveSquareMessage(chessBoard, position);
            stringBuilder.append(square);
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder;
    }

    private String resolveSquareMessage(ChessBoard chessBoard, Position position) {
        if (chessBoard.hasPiece(position)) {
            Piece piece = chessBoard.piece(position);
            return PieceMapper.toSymbol(piece);
        }
        return EMPTY;
    }
}
