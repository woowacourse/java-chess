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

public class OutputView {
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
        for (int rank = 8; rank >= 1; rank--) {
            printBoardRow(board, Rank.fromNumber(rank));
        }
    }

    private void printBoardRow(Map<Position, Piece> board, Rank targetRank) {
        List<String> strings = new ArrayList<>(Collections.nCopies(8, "."));
        for (var positionAndPiece : board.entrySet()) {
            Position position = positionAndPiece.getKey();
            Piece piece = positionAndPiece.getValue();
            Rank rank = position.rank();
            File file = position.file();

            if (rank == targetRank) {
                strings.set(file.order(), pieceDisplay(piece));
            }
        }
        System.out.println(String.join("", strings));
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
