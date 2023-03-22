package chess.view;

import static chess.util.PieceParser.parsePiece;

import chess.domain.board.Position;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + LINE_SEPARATOR
                + "> 게임 시작 : start" + LINE_SEPARATOR
                + "> 게임 종료 : end" + LINE_SEPARATOR
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final List<PieceDto> pieces) {
        Map<Position, Piece> board = new HashMap<>();

        pieces.forEach(pieceDto -> board.put(Position.from(findPosition(pieceDto)), parsePiece(pieceDto.getPiece())));

        for (char row = '8'; row >= '1'; row--) {
            printLine(board, row);
            System.out.println();
        }
    }

    private String findPosition(final PieceDto pieceDto) {
        return String.valueOf(pieceDto.getRow()) + pieceDto.getCol();
    }

    private void printLine(final Map<Position, Piece> board, final char row) {
        for (char col = 'a'; col <= 'h'; col++) {
            String position = String.valueOf(col) + String.valueOf(row);
            System.out.print(board.get(Position.from(position)).getName());
        }
    }
}
