package domain.move;

import domain.point.Point;

public enum Directions {
    TOP {
        @Override
        public Point move(Point point) {

            return null;
        }
    },
    RIGHT_TOP {
        @Override
        public Point move(Point point) {
            return null;
        }
    },
    RIGHT {
        @Override
        public Point move(Point point) {
            return null;
        }
    },
    RIGHT_DOWN {
        @Override
        public Point move(Point point) {
            return null;
        }
    },
    DOWN {
        @Override
        public Point move(Point point) {
            return null;
        }
    },
    LEFT_DOWN {
        @Override
        public Point move(Point point) {
            return null;
        }
    },
    LEFT {
        @Override
        public Point move(Point point) {
            return null;
        }
    },
    LEFT_TOP {
        @Override
        public Point move(Point point) {
            return null;
        }
    };

    public abstract Point move(Point point);
}
