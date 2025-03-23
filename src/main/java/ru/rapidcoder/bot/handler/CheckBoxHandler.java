package ru.rapidcoder.bot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rapidcoder.bot.component.ChekBoxButton;

public class CheckBoxHandler implements Handler {

    private static final Logger logger = LoggerFactory.getLogger(CheckBoxHandler.class);

    private final ChekBoxButton button;

    private InlineKeyboardMarkup keyboardMarkup;

    public CheckBoxHandler(ChekBoxButton button) {
        this.button = button;
    }

    @Override
    public void execute(Update update, TelegramLongPollingCommandBot bot) {
        button.switchSelection();

        Message msg = (Message) update.getCallbackQuery()
                .getMessage();

        EditMessageReplyMarkup sendMessage = new EditMessageReplyMarkup();
        sendMessage.setMessageId(msg.getMessageId());
        sendMessage.setChatId(msg.getChatId());
        sendMessage.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String getCallbackData() {
        return button.getCallbackData();
    }

    public void setKeyboardMarkup(InlineKeyboardMarkup keyboardMarkup) {
        this.keyboardMarkup = keyboardMarkup;
    }
}
