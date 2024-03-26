package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.ColumnPosition;
import chess.domain.position.Position;
import chess.domain.position.RowPosition;

import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.view.CommandMapper.*;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String POSITION_EMPTY_MESSAGE = ".";

    public void printStartMessage() {
        System.out.println(resolveStartMessage());
    }

    public void printChessBoardMessage(ChessBoard chessBoard) {
        System.out.println(resolveChessBoardMessage(chessBoard));
    }

    private String resolveStartMessage() {
        return new StringJoiner(LINE_SEPARATOR)
                .add("체스 게임을 시작합니다.")
                .add(String.format("> 게임 시작 : %s", START.getCode()))
                .add(String.format("> 게임 종료 : %s", END.getCode()))
                .add(String.format("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3", MOVE.getCode(), MOVE.getCode()))
                .toString();
    }

    private String resolveChessBoardMessage(ChessBoard chessBoard) {
        return IntStream.rangeClosed(RowPosition.MIN_NUMBER, RowPosition.MAX_NUMBER)
                .mapToObj(rowNumber -> resolveRowMessage(chessBoard, rowNumber))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String resolveRowMessage(ChessBoard chessBoard, int rowNumber) {
        return IntStream.rangeClosed(ColumnPosition.MIN_NUMBER, ColumnPosition.MAX_NUMBER)
                .mapToObj(columnNumber -> Position.of(rowNumber, columnNumber))
                .map(position -> resolveSquareMessage(chessBoard, position))
                .collect(Collectors.joining());
    }

    private String resolveSquareMessage(ChessBoard chessBoard, Position position) {
        if (chessBoard.positionIsEmpty(position)) {
            return POSITION_EMPTY_MESSAGE;
        }
        Piece foundPiece = chessBoard.findPieceByPosition(position);
        return PieceMessage.messageOf(foundPiece);
    }
}
