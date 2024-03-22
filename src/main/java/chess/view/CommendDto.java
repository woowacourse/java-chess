package chess.view;

public record CommendDto(Commend commend, String from, String to) {

    private static final int COMMEND_INDEX = 0;
    private static final int MOVE_FROM_INDEX = 1;
    private static final int MOVE_TO_INDEX = 2;

    public static CommendDto from(String input) {
        String[] split = input.split(" ");
        Commend commend = Commend.inputToCommend(split[COMMEND_INDEX]);
        if (commend == Commend.START || commend == Commend.END) {
            return new CommendDto(commend, null, null);
        }
        return new CommendDto(commend, split[MOVE_FROM_INDEX], split[MOVE_TO_INDEX]);
    }
}
