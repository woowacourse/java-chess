package chess.controller;

import static chess.domain.CommandType.END;
import static chess.domain.CommandType.MOVE;
import static chess.domain.CommandType.START;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessController {

    public void startChess() {
        ChessGame chessGame;

        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        while (true) {
            String firstCommand = InputView.inputCommand().get(0);
            if (firstCommand.equals(START.getCommandType())) {
                Board board = new Board();
                board.placeInitialPieces();
                chessGame = new ChessGame(board);

                OutputView.printBoard(makeBoardDto(board.getBoard()));

                progressChessGame(chessGame);

                break;
            }
            if (firstCommand.equals(END.getCommandType())) {
                break;
            }
            System.out.println("다시 입력하세요.");
        }
    }

    private void progressChessGame(ChessGame chessGame) {
        Team turn = Team.WHITE;
        while (true) {
            List<String> commands = InputView.inputCommand();
            String command = commands.get(0);
            if (command.equals(START.getCommandType())) {
                System.out.println("다시 입력하세요.");
                continue;
            }
            if (command.equals(MOVE.getCommandType())) {
                Position source = Position.of(commands.get(1));
                Position target = Position.of(commands.get(2));
                if (source.equals(target) || !chessGame.checkTurn(source, turn)) {
                    System.out.println("잘못된 움직임입니다.");
                    continue;
                }
                chessGame.move(source, target);
                Board board = chessGame.getBoard();
                OutputView.printBoard(makeBoardDto(board.getBoard()));
                turn = Team.takeTurn(turn);
                continue;
            }
            if (command.equals(END.getCommandType())) {
                break;
            }
        }
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
