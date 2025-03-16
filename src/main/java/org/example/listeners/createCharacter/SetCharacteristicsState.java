package org.example.listeners.createCharacter;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.example.listeners.ListenerState;
import org.example.listeners.UnexpectedEventException;

public class SetCharacteristicsState extends ListenerState<CreateCharacterContext> {
    private static final String setCharacteristicsFirstName = "set-characteristics";
    private static final String abortCreationName = "stop-creating";

    private final ModalInteractionEvent sourceEvent;
    private InteractionHook hook;
    private final int offset;
    private final int count;

    public SetCharacteristicsState(ModalInteractionEvent event, int offset, int count) {
        this.offset = offset;
        this.count = count;
        this.sourceEvent = event;
    }

    @Override
    protected void whenContextSet() {
        sourceEvent.reply("Hello " + context.getCharacter().getName() + "! Please set your characteristics.")
                .addActionRow(
                        Button.primary(setCharacteristicsFirstName, "Set characteristics (pt. " + count + ")"),
                        Button.danger(abortCreationName, "Abort")
                ).setEphemeral(true).queue();
        hook = sourceEvent.getHook();
    }

    @Override
    public ListenerState<CreateCharacterContext> handleButtonEvent(ButtonInteractionEvent event) throws UnexpectedEventException {
        switch (event.getButton().getId()) {
            case abortCreationName -> {
                hook.deleteOriginal().queue();
                event.reply("Creation cancelled.").setEphemeral(true).queue();
                return null;
            }
            case setCharacteristicsFirstName -> {
                hook.deleteOriginal().queue();
                return new SetCharacteristicsModalState(event, offset, count);
            }
            default -> throw new UnexpectedEventException();
        }
    }

}
