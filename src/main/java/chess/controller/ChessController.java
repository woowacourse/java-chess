package chess.controller;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessController {

    public void startChess() {
        Board board = new Board();
        board.placeInitialPieces();
        OutputView.printBoard(makeBoardDto(board.getBoard()));
    }

    private BoardDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(8, "."));
            rawBoard.add(row);
        }

        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();

            int realYPosition = position.getY() - 1;
            int realXPosition = position.getX() - 1;
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(realYPosition).set(realXPosition, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardDto(rawBoard);
    }
}
