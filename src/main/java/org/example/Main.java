package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.example.character.CharacterManager;
import org.example.data.JsonDataManager;
import org.example.listeners.createCharacter.CreateCharacterStateListener;
import org.example.listeners.test.TestStateListener;

import java.io.File;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JsonDataManager dataManager = new JsonDataManager(new File("C:\\Users\\Antoni\\IdeaProjects\\DarkHeresyBot\\src\\main\\resources\\characters.json"));
        CharacterManager characterManager = new CharacterManager(dataManager);
        //TODO: remove secret
        JDA api = JDABuilder.createDefault("MTI5MjE2MTg4MDMwMjgxMzIzNQ.GZxOg9.5USjGVogZbgS1zHf8zi_eP9L7TH17pLobMUNXI")
                .addEventListeners(new CreateCharacterStateListener(characterManager),
                        new TestStateListener(characterManager))
                .build()
                .awaitReady();

        api.updateCommands().addCommands(
                //Commands.slash("start-session", "Begins a new session."),
                Commands.slash("create-character", "Create a new character for Dark Heresy 2"),
                Commands.slash("test", "Roll a skill test")
                        //.addOptions(new OptionData(OptionType.BOOLEAN, "no-switch", "Don't switch to the new character once done")
        ).complete();
        System.out.println("Commands added");
        System.out.println(api.retrieveCommands().complete());
    }
}