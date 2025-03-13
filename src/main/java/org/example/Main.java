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
        String apiToken = System.getenv("DARK_HERESY_API_TOKEN");
        if (apiToken == null) {
            System.out.println("Please set DARK_HERESY_API_TOKEN env variable.");
            return;
        }
        JDA api = JDABuilder.createDefault(apiToken)
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
    }
}