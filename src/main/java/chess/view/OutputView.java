package chess.view;

import chess.model.board.ChessBoard;
import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printChessBoard(final ChessBoard chessBoard) {
        final Map<ChessPosition, Piece> board = chessBoard.getBoard();
        System.out.println(convertChessBoardText(board));
    }

    private String convertChessBoardText(final Map<ChessPosition, Piece> board) {
        return Arrays.stream(Rank.values())
                .map(rank -> Arrays.stream(File.values())
                        .map(file -> new ChessPosition(file, rank))
                        .map(board::get)
                        .map(PieceTextConverter::convertToText)
                        .collect(Collectors.joining("")))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
