package org.example.listeners.responses.modal;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;

import java.util.ArrayList;
import java.util.List;

public class ModalResponseBuilder {
    private final ModalRespondable event;

    private final String id;
    private String content = "";
    private final List<LayoutComponent> components = new ArrayList<>();

    public ModalResponseBuilder(ModalRespondable event, String id) {
        this.event = event;
        this.id = id;
    }

    public ModalResponseBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public ModalResponseBuilder addActionRow(ItemComponent ...components) {
        this.components.add(ActionRow.of(components));
        return this;
    }

    public ModalResponseBuilder addComponent(LayoutComponent component) {
        components.add(component);
        return this;
    }

    public ModalResponseBuilder addComponents(LayoutComponent ...components) {
        this.components.addAll(List.of(components));
        return this;
    }

    public ModalCallbackAction build() {
        return event.respondModal(Modal.create(id, content)
                                .addComponents(components)
                                .build());
    }


}
