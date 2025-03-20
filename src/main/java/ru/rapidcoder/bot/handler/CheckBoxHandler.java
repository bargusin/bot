package ru.rapidcoder.bot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.rapidcoder.bot.component.ChekBoxButton;

import java.io.Serializable;

public class CheckBoxHandler implements Handler {

    private final ChekBoxButton button;

    private InlineKeyboardMarkup keyboardMarkup;

    public CheckBoxHandler(ChekBoxButton button) {
        this.button = button;
    }

    @Override
    public BotApiMethod<Serializable> execute(Update update) {
        button.switchSelection();

        Message msg = (Message) update.getCallbackQuery()
                .getMessage();

        EditMessageReplyMarkup answer = new EditMessageReplyMarkup();
        answer.setMessageId(msg.getMessageId());
        answer.setChatId(msg.getChatId());
        answer.setReplyMarkup(keyboardMarkup);

        return answer;
    }

    @Override
    public String getCallbackData() {
        return button.getCallbackData();
    }

    public void setKeyboardMarkup(InlineKeyboardMarkup keyboardMarkup) {
        this.keyboardMarkup = keyboardMarkup;
    }
}
