package chess.controller.dto.response;

import java.util.List;

public class BoardResponseDTO {
    private final List<String> rank8;
    private final List<String> rank7;
    private final List<String> rank6;
    private final List<String> rank5;
    private final List<String> rank4;
    private final List<String> rank3;
    private final List<String> rank2;
    private final List<String> rank1;

    public BoardResponseDTO(List<String> rank8, List<String> rank7,
        List<String> rank6, List<String> rank5, List<String> rank4,
        List<String> rank3, List<String> rank2, List<String> rank1) {

        this.rank8 = rank8;
        this.rank7 = rank7;
        this.rank6 = rank6;
        this.rank5 = rank5;
        this.rank4 = rank4;
        this.rank3 = rank3;
        this.rank2 = rank2;
        this.rank1 = rank1;
    }

    public List<String> getRank8() {
        return rank8;
    }

    public List<String> getRank7() {
        return rank7;
    }

    public List<String> getRank6() {
        return rank6;
    }

    public List<String> getRank5() {
        return rank5;
    }

    public List<String> getRank4() {
        return rank4;
    }

    public List<String> getRank3() {
        return rank3;
    }

    public List<String> getRank2() {
        return rank2;
    }

    public List<String> getRank1() {
        return rank1;
    }
}
