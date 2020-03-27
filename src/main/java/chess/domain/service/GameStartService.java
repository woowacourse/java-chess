package chess.domain.service;

import chess.domain.board.Board;
import chess.view.OutputView;

public class GameStartService implements GameService {

    @Override
    public void run() {
        Board board = new Board();
        OutputView.printBoard(board);

//        while (true) {
//            MoveCommand moveCommand = new MoveCommand(InputView.requestMoveCommand(), board);
//            Position sourcePosition = moveCommand.getSourcePosition();
//            Position targetPosition = moveCommand.getTargetPosition();
//
//            Piece selectedPiece = board.getPieceBy(moveCommand.getSourcePosition());
//
//            List<Position> pathCell = PieceType.of(selectedPiece.getName()).getPathCells(sourcePosition,
//            targetPosition);
//
//            for (Position position : pathCell) {
//                System.out.println(position.toString());
//            }
//
//            if (board.isMovable(pathCell, selectedPiece)) {
//                selectedPiece.moveTo(targetPosition);
//                board.getCellBy(targetPosition).setPiece(selectedPiece);
//                board.getCellBy(sourcePosition).clear();
//            } else {
//                throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
//            }
//
//            OutputView.printBoard(board);
//        }
    }
}
