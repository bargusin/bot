package ru.rapidcoder.bot.component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class ChekBoxButton extends InlineKeyboardButton {

    private final String name;

    private boolean selected;

    public ChekBoxButton(String name, String callbackData) {
        this.name = name;
        this.selected = false;

        setText(getButtonText());
        setCallbackData(callbackData);

        refresh();
    }

    public void refresh() {
        setText(getButtonText());
    }

    public void switchSelection() {
        this.selected = !selected;
        refresh();
    }

    private String getButtonText() {
        return selected ? "[x] " + name : "[] " + name;
    }
}
