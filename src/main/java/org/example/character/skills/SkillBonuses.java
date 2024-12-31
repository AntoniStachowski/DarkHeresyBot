package org.example.character.skills;

public enum SkillBonuses {
    UNKNOWN(-20, "Unknown(-20)"),
    KNOWN(0, "Known(0)"),
    TRAINED(10, "Trained(+10)"),
    EXPERIENCED(20, "Experienced(+20)"),
    VETERAN(30, "Veteran(+30)");

    public final int bonus;
    private final String name;

    SkillBonuses(int bonus, String name) {
        this.bonus = bonus;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SkillBonuses getByName(String name) {
        SkillBonuses[] bonuses = SkillBonuses.values();
        for(SkillBonuses bonus: bonuses) {
            if(bonus.name.equals(name)) {
                return bonus;
            }
        }
        return null;
    }
}
