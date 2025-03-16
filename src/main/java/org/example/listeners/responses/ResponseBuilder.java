package org.example.listeners.responses;

import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {
    private final IReplyCallback event;
    private String content = "";
    private Boolean ephemeral = false;

    private final List<LayoutComponent> components = new ArrayList<>();

    public ResponseBuilder(IReplyCallback event) {
        this.event = event;
    }

    public ResponseBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public ResponseBuilder setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        return this;
    }

    public ResponseBuilder addActionRow(ItemComponent ...components) {
        this.components.add(ActionRow.of(components));
        return this;
    }

    public ResponseBuilder addComponent(LayoutComponent component) {
        components.add(component);
        return this;
    }

    public ResponseBuilder addComponents(LayoutComponent ...components) {
        this.components.addAll(List.of(components));
        return this;
    }

    public ReplyCallbackAction build() {
        return event.reply(content)
                .addComponents(components)
                .setEphemeral(ephemeral);
    }

}
