package chess.game;

import java.util.Arrays;
import java.util.List;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.board.dto.BoardDto;
import chess.piece.Piece;

public class ChessGame {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final Board board;
    private Turn turn;

    public ChessGame(final Board board) {
        this.board = board;
        this.turn = Turn.WHITE;
    }

    public BoardDto generateBoardDto() {
        return BoardDto.of(board);
    }

    public Position generatePosition(final String positionInput) {
        final List<String> splitSourcePosition = Arrays.asList(positionInput.split(""));
        final File sourceFile = File.of(splitSourcePosition.get(FILE_INDEX));
        final Rank sourceRank = Rank.of(Integer.parseInt(splitSourcePosition.get(RANK_INDEX)));
        return new Position(sourceFile, sourceRank);
    }

    public void movePiece(Position sorucePosition, Position targetPosition) {
        checkTurn(sorucePosition);
        board.movePiece(sorucePosition, targetPosition);
        this.turn = turn.change();
    }

    private void checkTurn(Position sorucePosition) {
        Piece sourcePiece = board.findPieceByPosition(sorucePosition);
        if (!turn.isCorrectTurn(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 현재 턴인 진영의 기물만 이동할 수 있습니다.");
        }
    }
}
