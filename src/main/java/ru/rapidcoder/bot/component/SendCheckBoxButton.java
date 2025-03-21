package ru.rapidcoder.bot.component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class SendCheckBoxButton extends InlineKeyboardButton implements Component {

    public SendCheckBoxButton(String text, String callbackData) {
        setText(text);
        setCallbackData(callbackData);
    }
}
