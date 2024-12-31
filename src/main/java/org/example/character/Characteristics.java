package org.example.character;

import net.dv8tion.jda.api.entities.emoji.Emoji;

public enum Characteristics {
    WEAPON_SKILL("weapon-skill", "Weapon skill", Emoji.fromUnicode("U+2694")),
    BALLISTIC_SKILL("ballistic-skill", "Ballistic skill", Emoji.fromUnicode("U+1F3F9")),
    STRENGTH("strength", "Strength", Emoji.fromUnicode("U+1F4AA")),
    TOUGHNESS("toughness", "Toughness",Emoji.fromUnicode("U+1FAA8")),
    AGILITY("agility", "Agility", Emoji.fromUnicode("U+1F45F")),
    INTELLIGENCE("intelligence", "Intelligence", Emoji.fromUnicode("U+1F4A1")),
    PERCEPTION("perception", "Perception", Emoji.fromUnicode("U+1F441")),
    WILLPOWER("willpower", "Willpower", Emoji.fromUnicode("U+1F623")),
    FELLOWSHIP("fellowship", "Fellowship", Emoji.fromUnicode("U+1F5E3")),
    INFLUENCE("influence", "Influence", Emoji.fromUnicode("U+1F4B0"));

    private final String id;
    private final String name;
    public final Emoji emoji;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Characteristics(String id, String name, Emoji emoji) {
        this.id = id;
        this.name = name;
        this.emoji = emoji;
    }

    public static Characteristics getFromId(String id){
        Characteristics[] characteristics = Characteristics.values();
        for (Characteristics characteristic: characteristics) {
            if (characteristic.id.equals(id)){
                return characteristic;
            }
        }
        return null;
    }
}
