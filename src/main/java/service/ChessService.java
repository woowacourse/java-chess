package service;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import domain.CommandRequest;
import domain.PieceToStringConverter;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;

public class ChessService {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final ChessBoard chessBoard;
    private Camp currentCamp;
    private boolean ongoing;
    private final Map<Command, Consumer<CommandRequest>> commandsAndExecutions = Map.of(
        Command.START, ignored -> start(),
        Command.END, ignored -> end(),
        Command.MOVE, this::move);

    public ChessService() {
        this.chessBoard = new ChessBoard();
        this.currentCamp = Camp.WHITE;
        PieceToStringConverter.init();
    }

    public void execute(CommandRequest commandRequest) {
        Command command = Command.findRunCommand(commandRequest.getCommand());
        commandsAndExecutions.get(command).accept(commandRequest);
    }

    private void start() {
        if (!ongoing) {
            chessBoard.initialize();
            ongoing = true;
            return;
        }
        throw new IllegalStateException("이미 게임이 실행중입니다.");
    }

    private void end() {
        ongoing = false;
    }

    private void move(CommandRequest commandRequest) {
        if (ongoing) {
            Square currentSquare = convertToSquare(commandRequest.getCurrentSquareName());
            Square targetSquare = convertToSquare(commandRequest.getTargetSquareName());

            validateTurn(currentSquare);

            chessBoard.move(currentSquare, targetSquare);

            currentCamp = currentCamp.fetchOppositeCamp();
            return;
        }
        throw new IllegalStateException("게임을 먼저 실행해주세요.");
    }

    private Square convertToSquare(String squareName) {
        return new Square(File.find(squareName.charAt(FILE_INDEX)), Rank.find(squareName.charAt(RANK_INDEX)));
    }

    private void validateTurn(Square currentSquare) {
        if (!chessBoard.isCorrectCamp(currentCamp, currentSquare)) {
            throw new IllegalStateException(currentCamp.name() + "의 턴입니다.");
        }
    }

    public Map<Square, String> getChessBoard() {
        return chessBoard.getBoard()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> PieceToStringConverter.convert(entry.getValue())));
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
