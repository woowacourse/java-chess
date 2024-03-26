package view;

import domain.piece.PieceColor;
import domain.piece.PieceType;
import dto.BoardDto;
import dto.PieceDto;
import dto.PositionDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static domain.piece.PieceType.PAWN;
import static domain.piece.PieceType.ROOK;
import static domain.piece.PieceType.KNIGHT;
import static domain.piece.PieceType.BISHOP;
import static domain.piece.PieceType.QUEEN;
import static domain.piece.PieceType.KING;

public class OutputView {
    private static final Map<PieceType, String> pieceFormat = Map.ofEntries(
            Map.entry(ROOK, "R"),
            Map.entry(KNIGHT, "N"),
            Map.entry(BISHOP, "B"),
            Map.entry(QUEEN, "Q"),
            Map.entry(KING, "K"),
            Map.entry(PAWN, "P")
    );

    public void printErrorMessage(final String message) {
        System.out.println(message);
        System.out.println();
    }

    public void printWelcomeMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작: start");
        System.out.println("> 게임 종료: end");
        System.out.println("> 게임 이동: move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final BoardDto boardDto) {
        List<String> boardStatus = convertBoardStatus(boardDto.value());
        boardStatus.forEach(System.out::println);
        System.out.println();
    }

    private List<String> convertBoardStatus(final Map<PositionDto, PieceDto> boardStatus) {
        String[][] boardMessage = initEmptyBoardMessage();
        boardStatus.forEach((position, piece) -> addPieceStatusInBoardMessage(boardMessage, position, piece));

        return Arrays.stream(boardMessage)
                .map(rowString -> String.join("", rowString))
                .toList();
    }

    private static String[][] initEmptyBoardMessage() {
        String[][] strings = new String[8][8];
        for (String[] row : strings) {
            Arrays.fill(row, ".");
        }

        return strings;
    }

    private void addPieceStatusInBoardMessage(final String[][] boardMessage, final PositionDto position, final PieceDto piece) {
        boardMessage[position.row()][position.column()] = convertPieceTypeToString(piece.type(), piece.color());
    }

    private String convertPieceTypeToString(final PieceType pieceType, final PieceColor pieceColor) {
        String pieceMessage = pieceFormat.get(pieceType);

        if (pieceColor == PieceColor.WHITE) {
            return pieceMessage.toLowerCase();
        }

        return pieceMessage;
    }
}
