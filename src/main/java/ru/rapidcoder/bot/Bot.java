package ru.rapidcoder.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rapidcoder.bot.command.ChekBoxCommandOne;
import ru.rapidcoder.bot.command.ChekBoxCommandTwo;
import ru.rapidcoder.bot.command.HelpCommand;
import ru.rapidcoder.bot.command.StartCommand;
import ru.rapidcoder.bot.handler.HandlerExcecutor;
import ru.rapidcoder.bot.handler.KeyboardManager;

public class Bot extends TelegramLongPollingCommandBot {

    public static final String BOT_NAME = ResourcesAdapter.getProperties().get("botName").toString();
    public static final String TOKEN_ID = ResourcesAdapter.getProperties().get("tokenId").toString();
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    private final HandlerExcecutor handlerExcecutor = HandlerExcecutor.getHandlerExcecutor();

    protected KeyboardManager keyboardManager = KeyboardManager.getKeyboardManager();


    public Bot() {
        register(new StartCommand("start", "Старт"));
        register(new ChekBoxCommandOne("check1", "Множественный выбор 1"));
        register(new ChekBoxCommandTwo("check2", "Множественный выбор 2"));
        register(new HelpCommand("help", "Помощь"));
    }

    /**
     * Формирование имени пользователя
     *
     * @param msg сообщение
     */
    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    /**
     * Отправка ответа
     *
     * @param chatId id чата
     * @param text   текст ответа
     */
    private void sendMessage(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void sendEditMessage(Long chatId, Integer messageId, InlineKeyboardMarkup keyboardMarkup) {
        EditMessageReplyMarkup answer = new EditMessageReplyMarkup();
        answer.setMessageId(messageId);
        answer.setChatId(chatId);
        answer.setReplyMarkup(keyboardMarkup);

        // Необходимо поместить в стэк текущую клавиатуру
        keyboardManager.save(chatId, keyboardMarkup);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            // Обработка обычных сообщений
        } else if (update.hasCallbackQuery()) {
            Message msg = (Message) update.getCallbackQuery().getMessage();
            String result = handlerExcecutor.execute(update.getCallbackQuery().getData());
            if (result == null) {
                sendEditMessage(msg.getChatId(), msg.getMessageId(), keyboardManager.get(msg.getChatId()));
            } else {
                sendMessage(msg.getChatId(), result);
            }
        }
    }

    @Override
    public String getBotToken() {
        return TOKEN_ID;
    }

}
