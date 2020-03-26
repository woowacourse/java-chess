package domain.commend;

import java.util.Arrays;
import java.util.List;

public enum CommendType {
    START {
        @Override
        public boolean isCommend(String input) {
            return input.equals("start");
        }
    },
    END {
        @Override
        public boolean isCommend(String input) {
            return input.equals("end");
        }
    },
    STATUS {
        @Override
        public boolean isCommend(String input) {
            return input.equals("status");
        }
    },
    MOVE {
        @Override
        public boolean isCommend(String input) {
            List<String> inputSplit = Arrays.asList(input.split(" "));
            if (inputSplit.size() == 0) {
                return false;
            }
            return inputSplit.get(0).equals("move");
        }
    };

    public abstract boolean isCommend(String input);
}
