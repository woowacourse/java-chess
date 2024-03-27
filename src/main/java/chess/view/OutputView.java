package chess.view;

import chess.GameStatus;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public void printBoard(BoardDto boardDto) {
        for (Rank rank : RANK_ORDER) {
            printBoardOneLine(boardDto, rank);
        }
        System.out.println();
    }

    private void printBoardOneLine(BoardDto boardDto, Rank rank) {
        for (File file : FILE_ORDER) {
            Optional<PieceDto> piece = boardDto.find(new Position(file, rank));
            piece.ifPresentOrElse(this::printPiece, this::printEmptySpace);
        }
        System.out.println();
    }

    private void printPiece(PieceDto pieceDto) {
        if (pieceDto.isBlack()) {
            printBlackPiece(pieceDto.type());
            return;
        }
        printWhitePiece(pieceDto.type());
    }

    private void printBlackPiece(PieceType type) {
        String display = PIECE_DISPLAY.get(type);
        System.out.print(display.toUpperCase());
    }

    private void printWhitePiece(PieceType type) {
        String display = PIECE_DISPLAY.get(type);
        System.out.print(display.toLowerCase());
    }

    private void printEmptySpace() {
        System.out.print(EMPTY_SPACE);
    }

    public void printStatus(double blackScore, double whiteScore) {
        System.out.print("검은색: " + blackScore + ", 흰색: " + whiteScore);
        if (blackScore > whiteScore) {
            System.out.println(", 검은색 승리");
            return;
        }
        if (whiteScore > blackScore) {
            System.out.println(", 흰색 승리");
            return;
        }
        System.out.println(", 무승부");
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(ERROR_PREFIX + exception.getMessage());
    }

    public void printWinner(GameStatus gameStatus) {
        if (gameStatus == GameStatus.BLACK_WIN) {
            System.out.println("검은색 승리");
            return;
        }
        System.out.println("흰색 승리");
    }
}
