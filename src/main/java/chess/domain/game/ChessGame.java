package chess.domain.game;

import chess.boardstrategy.BoardStrategy;
import chess.domain.board.ChessBoard;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.type.Piece;

import java.util.List;
import java.util.Map;

import static chess.controller.Command.START_SOURCE_INDEX_IN_COMMANDLINE;
import static chess.controller.Command.TARGET_SOURCE_INDEX_IN_COMMANDLINE;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color turn;
    private StateOfChessGame stateOfChessGame;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        this.turn = Color.WHITE;
        this.stateOfChessGame = StateOfChessGame.READY;
    }

    public void start(BoardStrategy boardStrategy) {
        if(stateOfChessGame.isStarted()) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다");
        }
        this.chessBoard.initialize(boardStrategy.generate());
    }

    public void move(List<String> commandLine) {
        if(!stateOfChessGame.isStarted()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        Position start = createPositionByCommand(commandLine.get(START_SOURCE_INDEX_IN_COMMANDLINE));
        Position end = createPositionByCommand(commandLine.get(TARGET_SOURCE_INDEX_IN_COMMANDLINE));
        checkCorrectTurnByColor(start);
        chessBoard.move(start, end);
        turn = turn.getOpponent();
    }

    private Position createPositionByCommand(String sourceCommand) {
        List<String> columnAndRank = List.of(sourceCommand.split(""));
        Column column = Column.findColumnByValue(columnAndRank.get(0));
        Rank rank = Rank.findRankByValue(columnAndRank.get(1));

        return Position.of(column, rank);
    }

    private void checkCorrectTurnByColor(Position start) {
        Color colorOfStartPiece = chessBoard.findPieceInBoardByPosition(start).getColor();
        if(colorOfStartPiece.isOpponent(turn)) {
            throw new IllegalArgumentException("상대편의 기물을 움직일 수 없습니다");
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

}