package chess.view;

import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final int FILE_SIZE = 8;
    private static final int RANK_SIZE = 8;
    private static final String GAME_START_INFO_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator()
            + "> 게임 시작 : start" + System.lineSeparator()
            + "> 게임 종료 : end" + System.lineSeparator()
            + "> 게임 로드 : load id - 예. load 1" + System.lineSeparator()
            + "> 저장되어 있는 게임 ID들 : %s" + System.lineSeparator()
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator();
    private static final String SCORE_STATUS_MESSAGE_FORMAT = "%s팀은 %.1f점 입니다." + System.lineSeparator();
    private static final Map<PieceType, String> PIECE_VALUE_MAP = new EnumMap<>(PieceType.class);
    private static final Map<Color, String> COLOR_STRING_MAP = new EnumMap<>(Color.class);
    private static final String EMPTY_POSITION = ".";

    static {
        PIECE_VALUE_MAP.put(PieceType.KING, "k");
        PIECE_VALUE_MAP.put(PieceType.QUEEN, "q");
        PIECE_VALUE_MAP.put(PieceType.BISHOP, "b");
        PIECE_VALUE_MAP.put(PieceType.ROOK, "r");
        PIECE_VALUE_MAP.put(PieceType.KNIGHT, "n");
        PIECE_VALUE_MAP.put(PieceType.PAWN, "p");
        COLOR_STRING_MAP.put(Color.BLACK, "검은색");
        COLOR_STRING_MAP.put(Color.WHITE, "흰색");
    }

    public void printGameStartInfo(final List<Long> chessGameIds) {
        final String ids = chessGameIds
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.printf(GAME_START_INFO_MESSAGE, ids);
    }

    public void printBoard(final Map<Position, Piece> boardMap) {
        List<List<String>> chessBoard = initializeBoard();

        mappingChessBoard(boardMap, chessBoard);

        chessBoard.stream()
                .map(board -> String.join("", board))
                .forEach(System.out::println);
    }

    private void mappingChessBoard(final Map<Position, Piece> boardMap, final List<List<String>> chessBoard) {
        for (final Map.Entry<Position, Piece> positionPieceEntry : boardMap.entrySet()) {
            final Position position = positionPieceEntry.getKey();
            final Piece piece = positionPieceEntry.getValue();
            final int column = position.file().value();
            final int row = position.rank().value();
            final String pieceDisplay = formatPieceDisplay(piece);
            chessBoard.get(RANK_SIZE - row).set(column - 1, pieceDisplay);
        }
    }

    private List<List<String>> initializeBoard() {
        final List<List<String>> emptyBoard = new ArrayList<>();
        for (int i = 0; i < RANK_SIZE; i++) {
            emptyBoard.add(new ArrayList<>(Collections.nCopies(FILE_SIZE, EMPTY_POSITION)));
        }
        return emptyBoard;
    }

    private String formatPieceDisplay(final Piece piece) {
        String pieceDisplay = PIECE_VALUE_MAP.get(piece.getPieceType());
        if (piece.isBlack()) {
            return pieceDisplay.toUpperCase();
        }
        return pieceDisplay;
    }

    public void printStatus(final Map<Color, Score> colorScore) {
        for (final Entry<Color, Score> entry : colorScore.entrySet()) {
            System.out.printf(SCORE_STATUS_MESSAGE_FORMAT,
                    COLOR_STRING_MAP.get(entry.getKey()),
                    entry.getValue().getValue());
        }
    }

    public void printExceptionMessage(final Exception e) {
        System.out.println(e.getMessage());
    }
}
