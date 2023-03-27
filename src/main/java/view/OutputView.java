package view;


import domain.board.piece.Piece;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.util.Map;

public final class OutputView {

    private static final int START_COLUMN = 0;
    private static final int END_COLUMN = 8;
    private static final int START_ROW = 7;
    private static final int END_ROW = 0;

    public void printBoard(final Map<Location, Piece> chessBoard) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int rowValue = START_ROW; rowValue >= END_ROW; rowValue--) {
            makeLine(stringBuilder, chessBoard, rowValue);
        }
        System.out.println(stringBuilder);
    }

    private void makeLine(final StringBuilder stringBuilder, final Map<Location, Piece> board, final int rowValue) {
        for (int columnValue = START_COLUMN; columnValue < END_COLUMN; columnValue++) {
            final Location currentLocation = Location.of(Row.valueOf(rowValue), Column.valueOf(columnValue));
            stringBuilder.append(makePieceSign(board.get(currentLocation)));
        }
        stringBuilder.append(System.lineSeparator());
    }

    private String makePieceSign(final Piece piece) {
        return PieceView.findSign(piece);
    }

    public void printScores(final double blackScore, final double whiteScore) {
        System.out.println("검은 진영 : " + blackScore);
        System.out.println("하얀 진영 : " + whiteScore);
    }

    public void printEndMessage() {
        System.out.println("게임이 종료되었습니다.");
    }
}
