package org.example.character.skills;

import org.example.Skill;

public enum CommonLoreTypes implements Skill {
    ADEPTA_SORORITAS("Adepta Sororitas"),
    ADEPTUS_ARBITES("Adeptus Arbites"),
    ADEPTUS_ASTARTES("Adeptus Astartes"),
    ADEPTUS_ASTRA_TELEPATHICA("Adeptus Astra Telepathica"),
    ADEPTUS_MECHANICUS("Adeptus Mechanicus"),
    ADMINISTRATUM("Administratum"),
    ASKELLON_SECTOR("Askellon sector"),
    CHARTIST_CAPTAINS("Chartist captains"),
    COLLEGIA_TITANICUS("Collegia Titanicus"),
    ECCLESIARCHY("Ecclesiarchy"),
    IMPERIAL_CREED("Imperial Creed"),
    IMPERIAL_GUARD("Imperial Guard"),
    IMPERIAL_NAVY("Imperial Navy"),
    IMPERIUM("Imperium"),
    NAVIGATORS("Navigators"),
    PLANETARY_DEFENCE_FORCES("Planetary defence forces"),
    ROGUE_TRADERS("Rogue traders"),
    SCHOLA_PROGENIUM("Schola Progenium"),
    TECH("Tech"),
    UNDERWORLD("Underworld"),
    WAR("War");

    private final String name;

    CommonLoreTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.COMMON_LORE.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }
}
