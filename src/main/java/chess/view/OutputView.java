package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final Map<Class<?>, String> stauntonFormat = new HashMap<>();

    static {
        stauntonFormat.put(Rook.class, "r");
        stauntonFormat.put(Pawn.class, "p");
        stauntonFormat.put(Bishop.class, "b");
        stauntonFormat.put(King.class, "k");
        stauntonFormat.put(Knight.class, "n");
        stauntonFormat.put(Queen.class, "q");
    }

    public static void showBoard(final List<Piece> chessBoard) {
        final Map<PiecePosition, Piece> pieceMap = chessBoard.stream()
                .collect(Collectors.toMap(Piece::piecePosition, Function.identity()));
        for (int rank = 8; rank >= 1; rank--) {
            System.out.println(makeLineFormat(pieceMap, rank));
        }
        System.out.println();
    }

    private static String makeLineFormat(final Map<PiecePosition, Piece> chessBoard, final int rank) {
        return IntStream.rangeClosed('a', 'h')
                .mapToObj(file -> makeFormat(chessBoard, rank, (char) file))
                .collect(Collectors.joining(""));
    }

    private static String makeFormat(final Map<PiecePosition, Piece> chessBoard, final int rank, final char file) {
        return Optional.ofNullable(chessBoard.get(PiecePosition.of(rank, file)))
                .map(OutputView::convertCaseAccordingToColor)
                .orElse(".");
    }

    private static String convertCaseAccordingToColor(final Piece piece) {
        String format = stauntonFormat.get(piece.getClass());
        if (piece.color() == Color.BLACK) {
            format = format.toUpperCase();
        }
        return format;
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }
}
