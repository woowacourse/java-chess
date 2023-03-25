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
    private Color turn = Color.WHITE;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    public void start(BoardStrategy boardStrategy) {
        if(isStarted()){
            throw new IllegalArgumentException("게임이 이미 시작되었습니다");
        }
        this.chessBoard.initialize(boardStrategy.generate());
    }

    public void move(List<String> commandLine) {
        if(!isStarted()) {
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
        Color colorOfStartPiece = chessBoard.findColorOfPieceInPosition(start);
        if(colorOfStartPiece.isOpponent(turn)) {
            throw new IllegalArgumentException("상대편의 기물을 움직일 수 없습니다");
        }
    }

    private boolean isStarted() {
        return chessBoard.isInitialized();
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

}

//
/**
 *
 private Map<Command, Consumer<List<String>>> setActionsByCommand(BoardStrategy boardStrategy) {
 Map<Command, Consumer<List<String>>> actionsByCommand = new EnumMap<>(Command.class);
 actionsByCommand.put(Command.START, ignored -> start(boardStrategy));
 actionsByCommand.put(Command.MOVE, commandLine -> move(commandLine));
 actionsByCommand.put(Command.END, ignored -> {});

 return actionsByCommand;
 }

 public void execute(Command command, List<String> additionalCommand) {
 actionsByCommand.getOrDefault(command, ignored-> new IllegalArgumentException("해당하는 명령이 존재하지 않습니다"))
 .accept(additionalCommand);

 }
 */