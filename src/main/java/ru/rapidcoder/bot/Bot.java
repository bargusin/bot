package ru.rapidcoder.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rapidcoder.bot.command.ChekBoxCommandOne;
import ru.rapidcoder.bot.command.ChekBoxCommandTwo;
import ru.rapidcoder.bot.command.HelpCommand;
import ru.rapidcoder.bot.command.StartCommand;
import ru.rapidcoder.bot.handler.HandlerExcecutor;

import java.io.Serializable;

public class Bot extends TelegramLongPollingCommandBot {

    private final String botName;

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    private final HandlerExcecutor handlerExcecutor = HandlerExcecutor.getHandlerExcecutor();

    public Bot(String botName, String tokenId) {
        super(tokenId);
        this.botName = botName;
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

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            // Обработка обычных сообщений
        } else if (update.hasCallbackQuery()) {
            BotApiMethod<Serializable> method = handlerExcecutor.execute(update.getCallbackQuery()
                    .getData(), update, this);
            try {
                execute(method);
            } catch (TelegramApiException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

}
