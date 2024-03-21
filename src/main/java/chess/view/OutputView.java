package chess.view;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.PieceDto;
import chess.dto.PieceType;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final List<Rank> RANK_ORDER = List.of(
            Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ONE);
    private static final List<File> FILE_ORDER = List.of(
            File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H);
    private static final Map<PieceType, String> PIECE_DISPLAY = Map.of(
            PieceType.KING, "K", PieceType.QUEEN, "Q", PieceType.KNIGHT, "N",
            PieceType.BISHOP, "B", PieceType.ROOK, "R", PieceType.PAWN, "P");
    private static final String EMPTY_SPACE = ".";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printStartGame() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printBoard(Map<Position, PieceDto> board) {
        for (Rank rank : RANK_ORDER) {
            printBoardOneLine(board, rank);
        }
        System.out.println();
    }

    private void printBoardOneLine(Map<Position, PieceDto> board, Rank rank) {
        for (File file : FILE_ORDER) {
            PieceDto piece = board.get(new Position(file, rank));
            printPiece(piece);
        }
        System.out.println();
    }

    private void printPiece(PieceDto piece) {
        if (piece == null) {
            System.out.print(EMPTY_SPACE);
            return;
        }
        if (piece.isBlack()) {
            printBlackPiece(piece.type());
            return;
        }
        printWhitePiece(piece.type());
    }

    private void printBlackPiece(PieceType type) {
        String display = PIECE_DISPLAY.get(type);
        System.out.print(display.toUpperCase());
    }

    private void printWhitePiece(PieceType type) {
        String display = PIECE_DISPLAY.get(type);
        System.out.print(display.toLowerCase());
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(ERROR_PREFIX + exception.getMessage());
    }
}
