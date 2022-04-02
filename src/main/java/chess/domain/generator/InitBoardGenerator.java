package chess.domain.generator;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardSetting;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.IntStream;

public class InitBoardGenerator implements BoardGenerator {

    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 7;

    @Override
    public Board generate() {
        List<List<Piece>> board = IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());

        for (BoardSetting boardSetting : BoardSetting.values()) {
            fillPieces(board, boardSetting);
        }

        return new Board(board);
    }

    private List<Piece> generatePieces() {
        return IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }

    private void fillPieces(List<List<Piece>> board, BoardSetting boardSetting) {
        Piece piece = boardSetting.getPiece();
        List<Position> positions = boardSetting.getPositions();

        for (Position position : positions) {
            fillPiece(board, piece, position);
        }
    }

    private void fillPiece(List<List<Piece>> board, Piece piece, Position position) {
        board.get(position.getRankIndex()).set(position.getFileIndex(), piece);
    }
}
