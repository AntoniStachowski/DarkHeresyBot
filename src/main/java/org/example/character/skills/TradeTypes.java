package org.example.character.skills;

import org.example.Skill;

public enum TradeTypes implements Skill {
    AGRI("Agri"),
    ARCHAEOLOGIST("Archaeologist"),
    ARMOURER("Armourer"),
    ASTROGRAPHER("Astrographer"),
    CHYMIST("Chymist"),
    CRYPTOGRAPHER("Cryptographer"),
    COOK("Cook"),
    EXPLORATOR("Explorator"),
    LINGUIST("Linguist"),
    LOREMANCER("Loremancer"),
    MORTICATOR("Morticator"),
    PERFORMANCER("Performancer"),
    PROSPECTOR("Prospector"),
    SCRIMSHAWER("Scirmisher"),
    SCULPTOR("Sculptor"),
    SHIPWRIGHT("Shipwright"),
    SOOTHSAYER("Soothsayer"),
    TECHNOMAT("Technomat"),
    VOIDFARER("Voidfarer");

    private final String name;

    TradeTypes(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return ComplexSkills.TRADE.name + "("  + name + ")";
    }

    @Override
    public String getSpecializationName() {
        return name;
    }

}
