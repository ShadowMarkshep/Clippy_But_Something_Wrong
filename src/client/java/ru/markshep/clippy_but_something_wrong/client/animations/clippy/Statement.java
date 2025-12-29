package ru.markshep.clippy_but_something_wrong.client.animations.clippy;

public enum Statement {
    AIRPLANE(84, "animations/airplane.png", 5376),
    CHECK(35, "animations/check.png", 2240),
    EYES(104, "animations/eyes.png", 6656),
    IDLE(32, "animations/idle.png", 2112),
    LISTEN(28, "animations/listen.png", 1792),
    MUSIC(84, "animations/music.png", 5376),
    NOTED(145, "animations/noted.png", 9280),
    OFFICE(71, "animations/office.png", 4544),
    POINT_DOWN(31, "animations/point_down.png", 1984),
    POINT_UP(31, "animations/point_up.png", 1984),
    POINT_LEFT(30, "animations/point_left.png", 1920),
    POINT_RIGHT(34, "animations/point_right.png", 2176),
    READ(133, "animations/read.png", 8512),
    SHOVEL(85, "animations/shovel.png", 5440),
    SLEEP(145, "animations/sleep.png", 9280),
    SPYGLASS(92, "animations/spyglass.png", 5888),
    THINK(55, "animations/think.png", 3520),
    TIRED(133, "animations/tired.png", 8512),
    WAKE_UP(22, "animations/wake_up.png", 1408),
    WATCH_FRONT(36, "animations/watch_front.png", 2304),
    WATCH_LEFT(17, "animations/watch_left.png", 1088),
    WATCH_RIGHT(12, "animations/watch_right.png", 768),
    WTF(43, "animations/wtf.png", 2752);

    private final int totalFrames;
    private final String path;
    private final int width;

    Statement(int totalFrames, String path, int width) {
        this.totalFrames = totalFrames;
        this.path = path;
        this.width = width;
    }

    public int getTotalFrames() {
        return totalFrames - 1;
    }

    public String getPath() {
        return path;
    }

    public int getWidth() {
        return width;
    }
}
