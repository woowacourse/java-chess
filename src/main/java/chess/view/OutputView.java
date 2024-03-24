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

    public void printException(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printChessBoard(ChessBoard chessBoard) {
        Map<ChessPosition, Piece> board = chessBoard.getBoard();
        System.out.println(convertChessBoardText(board));
    }

    private String convertChessBoardText(final Map<ChessPosition, Piece> board) {
        return Arrays.stream(Rank.values())
                .map(rank -> Arrays.stream(File.values())
                        .map(file -> new ChessPosition(file, rank))
                        .map(board::get)
                        .map(Piece::getText)
                        .collect(Collectors.joining("")))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
