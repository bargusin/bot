package ru.rapidcoder.bot.component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.rapidcoder.bot.handler.Handler;
import ru.rapidcoder.bot.handler.HandlerExcecutor;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxComponent {

    private final HandlerExcecutor handlerExcecutor = HandlerExcecutor.getHandlerExcecutor();

    private final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

    private final List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

    public CheckBoxComponent() {
        keyboardMarkup.setKeyboard(rowsInline);
    }

    public void addItem(ChekBoxButton item) {
        handlerExcecutor.add(new Handler(item));
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(item);
        rowsInline.add(rowInline1);
    }

    public InlineKeyboardMarkup getKeyboardMarkup() {
        return keyboardMarkup;
    }

}
