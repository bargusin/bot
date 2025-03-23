package ru.rapidcoder.bot.component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.rapidcoder.bot.handler.Handler;
import ru.rapidcoder.bot.handler.HandlerExcecutor;
import ru.rapidcoder.bot.handler.SendCheckBoxHandler;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxComponent {

    private final HandlerExcecutor handlerExcecutor = HandlerExcecutor.getHandlerExcecutor();

    private final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

    private final List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

    public CheckBoxComponent() {
        keyboardMarkup.setKeyboard(rowsInline);
    }

    public void addItem(ChekBoxButton item, Handler handler) {
        handlerExcecutor.add(handler);
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(item);
        rowsInline.add(rowInline);
    }

    public void addSender(SendCheckBoxButton sendButton, SendCheckBoxHandler handler) {
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        handlerExcecutor.add(handler);
        //handlerExcecutor.add(new Handler(cancelButton));
        rowInline.add(sendButton);
        rowInline.add(sendButton);
        rowsInline.add(rowInline);
    }

    public List<ChekBoxButton> getItems() {
        List<ChekBoxButton> items = new ArrayList<>();
        for (List<InlineKeyboardButton> list : rowsInline) {
            for (InlineKeyboardButton button : list) {
                if (button instanceof ChekBoxButton) {
                    items.add((ChekBoxButton) button);
                }
            }
        }
        return items;
    }

    public InlineKeyboardMarkup getKeyboardMarkup() {
        return keyboardMarkup;
    }

}
