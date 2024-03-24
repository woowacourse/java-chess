package view;

import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import view.mapper.PieceMapper;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY = ".";

    public String resolveChessBoardMessage(ChessBoard chessBoard) {
        StringBuilder message = new StringBuilder();
        for (int vertical = File.max(); vertical > 0; vertical--) {
            StringBuilder rank = resolveRankMessage(Rank.find(vertical), chessBoard);
            message.append(rank);
        }
        return message.toString();
    }

    private StringBuilder resolveRankMessage(Rank rank, ChessBoard chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int horizontal = 1; horizontal <= File.max(); horizontal++) {
            Position position = new Position(File.find(horizontal), rank);
            String square = resolveSquareMessage(chessBoard, position);
            stringBuilder.append(square);
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder;
    }

    private String resolveSquareMessage(ChessBoard chessBoard, Position position) {
        if (chessBoard.hasPiece(position)) {
            Piece piece = chessBoard.findPiece(position);
            return PieceMapper.toSymbol(piece);
        }
        return EMPTY;
    }
}
