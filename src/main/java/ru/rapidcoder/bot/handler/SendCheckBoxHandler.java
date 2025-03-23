package ru.rapidcoder.bot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rapidcoder.bot.component.ChekBoxButton;
import ru.rapidcoder.bot.component.SendCheckBoxButton;

import java.util.List;

public class SendCheckBoxHandler implements Handler {

    private static final Logger logger = LoggerFactory.getLogger(SendCheckBoxHandler.class);

    private final SendCheckBoxButton button;

    private List<ChekBoxButton> checkBoxButtons;

    private InlineKeyboardMarkup keyboardMarkup;

    public SendCheckBoxHandler(SendCheckBoxButton button) {
        this.button = button;
    }

    @Override
    public void execute(Update update, TelegramLongPollingCommandBot bot) {
        StringBuilder result = new StringBuilder();
        for (ChekBoxButton button : checkBoxButtons) {
            result.append(button.getCallbackData())
                    .append(":")
                    .append(button.isSelected())
                    .append("\n");
        }

        Message msg = (Message) update.getCallbackQuery()
                .getMessage();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(result.toString());
        sendMessage.setChatId(msg.getChatId());

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

    public void setCheckBoxButtons(List<ChekBoxButton> checkBoxButtons) {
        this.checkBoxButtons = checkBoxButtons;
    }

    public void setKeyboardMarkup(InlineKeyboardMarkup keyboardMarkup) {
        this.keyboardMarkup = keyboardMarkup;
    }
}
