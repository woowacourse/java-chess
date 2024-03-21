package view;

import static view.Command.END;
import static view.Command.MOVE;
import static view.Command.START;

import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printStartingMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.printf("> 게임 시작 : %s%n", START.message());
        System.out.printf("> 게임 종료 : %s%n", END.message());
        System.out.printf("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3%n", MOVE.message(), MOVE.message());
    }

    public void printBoard(ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.getBoard();
        List<String> strings = new ArrayList<>(Collections.nCopies(64, "."));

        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Position position = entry.getKey();
            Rank rank = position.rank();
            File file = position.file();
            Piece piece = entry.getValue();

            int row = 8 - rank.number();
            int column = file.order();

            strings.set(row * 8 + column, pieceDisplay(piece));
        }

        for (int i = 0; i < 8; i++) {
            System.out.println(String.join(" ", strings.subList(i * 8, (i + 1) * 8)));
        }
    }

    private String pieceDisplay(Piece piece) {
        String pieceName = piece.getClass().getSimpleName().substring(0, 1);
        if (piece.getColor().isWhite()) {
            return pieceName.toLowerCase();
        }
        return pieceName;
    }
}
