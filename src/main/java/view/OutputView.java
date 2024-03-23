package view;

import static view.Command.END;
import static view.Command.MOVE;
import static view.Command.START;

import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final int RANK_COUNT = 8;
    private static final int FILE_COUNT = 8;
    private static final String EMPTY_DISPLAY = ".";
    private static final Map<Type, String> PIECE_DISPLAY = Map.of(
            Type.PAWN, "p",
            Type.KNIGHT, "n",
            Type.BISHOP, "b",
            Type.ROOK, "r",
            Type.QUEEN, "q",
            Type.KING, "k"
    );

    public void printStartingMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.printf("> 게임 시작 : %s%n", START.getName());
        System.out.printf("> 게임 종료 : %s%n", END.getName());
        System.out.printf("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3%n", MOVE.getName(), MOVE.getName());
    }

    public void printBoard(ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.getPositionAndPieces();
        for (int rank = RANK_COUNT; rank > 0; rank--) {
            System.out.println(generateBoardDisplay(board, Rank.fromNumber(rank)));
        }
        System.out.println();
    }

    private String generateBoardDisplay(Map<Position, Piece> board, Rank targetRank) {
        List<String> boardDisplay = new ArrayList<>(Collections.nCopies(FILE_COUNT, EMPTY_DISPLAY));
        for (var positionAndPiece : board.entrySet()) {
            fillPieceDisplay(boardDisplay, positionAndPiece, targetRank);
        }
        return String.join("", boardDisplay);
    }

    private void fillPieceDisplay(List<String> boardDisplay, Entry<Position, Piece> positionAndPiece, Rank targetRank) {
        Rank rank = positionAndPiece.getKey().rank();
        File file = positionAndPiece.getKey().file();
        if (rank == targetRank) {
            boardDisplay.set(file.order(), pieceDisplay(positionAndPiece.getValue()));
        }
    }

    private String pieceDisplay(Piece piece) {
        String pieceName = PIECE_DISPLAY.get(piece.type());
        if (piece.color().isBlack()) {
            return pieceName.toUpperCase();
        }
        return pieceName;
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
