package chess.domain.generator;

import static java.util.stream.Collectors.toList;

import chess.domain.BoardSetting;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.IntStream;

public class InitBoardGenerator implements BoardGenerator {

    @Override
    public List<List<Piece>> generate() {
        List<List<Piece>> board = IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());

        for (BoardSetting boardSetting : BoardSetting.values()) {
            fillPieces(board, boardSetting);
        }

        return board;
    }

    private List<Piece> generatePieces() {
        return IntStream.rangeClosed(0, 7)
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
