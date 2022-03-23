package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.IntStream;

public class ChessBoard {

    private final List<List<Piece>> board;

    public ChessBoard() {
        this.board = IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());
    }

    private List<Piece> generatePieces() {
        return IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }

    public void init() {
        for (BoardSetting boardSetting : BoardSetting.values()) {
            fillPieces(boardSetting);
        }
    }

    private void fillPieces(BoardSetting boardSetting) {
        Piece piece = boardSetting.getPiece();
        List<Position> positions = boardSetting.getPositions();

        for (Position position : positions) {
            fillPiece(piece, position);
        }
    }

    private void fillPiece(Piece piece, Position position) {
        board.get(position.getRankIndex()).set(position.getFileIndex(), piece);
    }
}
