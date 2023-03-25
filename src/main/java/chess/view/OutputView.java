package chess.view;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.type.Piece;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class OutputView {
    private static final String CHESS_GAME_START_GUIDE_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String GAME_START_GUIDE_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_GUIDE_MESSAGE = "> 게임 종료 : end";
    private static final String MOVE_COMMAND_GUIDE_MESSAGE = "게임 이동 : move source위치 target 위치 - 예. move b2 b3";

    private static final Map<PieceType, String> symbolOfPiece;
    private static final Map<Color, Function<String, String>> symbolOfPieceByColor;

    static {
        symbolOfPiece = new EnumMap<>(PieceType.class);
        symbolOfPiece.put(PieceType.EMPTY_PIECE, ".");
        symbolOfPiece.put(PieceType.PAWN, "p");
        symbolOfPiece.put(PieceType.ROOK, "r");
        symbolOfPiece.put(PieceType.KNIGHT, "n");
        symbolOfPiece.put(PieceType.BISHOP, "b");
        symbolOfPiece.put(PieceType.QUEEN, "q");
        symbolOfPiece.put(PieceType.KING, "k");

        symbolOfPieceByColor = new EnumMap<>(Color.class);
        symbolOfPieceByColor.put(Color.BLACK, String::toUpperCase);
        symbolOfPieceByColor.put(Color.WHITE, String::toLowerCase);
        symbolOfPieceByColor.put(Color.NONE, Function.identity());
    }

    public void printStartGuideMessage() {
        System.out.println(CHESS_GAME_START_GUIDE_MESSAGE + System.lineSeparator()
                + GAME_START_GUIDE_MESSAGE + System.lineSeparator()
                + GAME_END_GUIDE_MESSAGE + System.lineSeparator()
                + MOVE_COMMAND_GUIDE_MESSAGE);
    }

    public void printBoard(Map<Position, Piece> boards) {
        System.out.println();
        Rank.getOrderedRanks()
                .forEach(rank -> {
                            Column.getOrderedColumns()
                                    .forEach(column -> System.out.print(findSymbolOfPieceByColor(boards, rank, column)));
                            System.out.println();
                        }
                );
    }

    private static String findSymbolOfPieceByColor(Map<Position, Piece> boards, Rank rank, Column column) {
        Piece piece = boards.get(Position.of(column, rank));
        String symbol = symbolOfPiece.get(piece.getPieceType());
        return symbolOfPieceByColor.get(piece.getColor()).apply(symbol);
    }


    public void printExceptionMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
