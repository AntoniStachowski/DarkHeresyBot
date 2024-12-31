package org.example.listeners.test;

public enum Difficulties {
    TRIVIAL("Trivial (+60)", "trivial", 60),
    ELEMENTARY("Elementary (+50)", "elementary", 50),
    SIMPLE("Simple (+40)", "simple", 40),
    EASY("Easy (+30)", "easy", 30),
    ROUTINE("Routine (+20)", "routine", 20),
    ORDINARY("Ordinary (+10)", "ordinary", 10),
    CHALLENGING("Challenging (+0)", "challenging", 0),
    DIFFICULT("Difficult (-10)", "difficult", -10),
    HARD("Hard (-20)", "hard", -20),
    VERY_HARD("Very hard (-30)", "very-hard", -30),
    ARDUOUS("Arduous (-40)", "arduous", -40),
    PUNISHING("Punishing (-50)", "punishing", -50),
    HELLISH("Hellish (-60)", "hellish", -60);

    private final String name;
    private final String id;
    private final int modifier;

    Difficulties(String name, String id, int modifier){
        this.name = name;
        this.id = id;
        this.modifier = modifier;
    }

    public static int roundDownToTen(int x) {
        return (x + (x >= 0 ? 0 : -9 )) / 10 * 10;
    }

    public static Difficulties getFromValue(int x) {
        int modifier = -roundDownToTen(x);
        for (Difficulties difficulty: Difficulties.values()) {
            if (modifier == difficulty.modifier) {
                return difficulty;
            }
        }
        if (modifier > 60) {
            return TRIVIAL;
        }
        return HELLISH;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getModifier() {
        return modifier;
    }

    public static Difficulties getFromId(String id){
        for (Difficulties difficulty: Difficulties.values()) {
            if (id.equals(difficulty.id)) {
                return difficulty;
            }
        }
        return null;
    }
}
