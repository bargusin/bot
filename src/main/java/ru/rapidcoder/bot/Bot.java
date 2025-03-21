package ru.rapidcoder.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.rapidcoder.bot.command.ChekBoxCommandOne;
import ru.rapidcoder.bot.command.ChekBoxCommandTwo;
import ru.rapidcoder.bot.command.HelpCommand;
import ru.rapidcoder.bot.command.StartCommand;
import ru.rapidcoder.bot.handler.HandlerExcecutor;

public class Bot extends TelegramLongPollingCommandBot {

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private final String botName;
    private final HandlerExcecutor handlerExcecutor = HandlerExcecutor.getHandlerExcecutor();

    public Bot(String botName, String tokenId) {
        super(tokenId);
        this.botName = botName;
        register(new StartCommand("start", "Старт"));
        register(new ChekBoxCommandOne("check1", "Множественный выбор 1"));
        register(new ChekBoxCommandTwo("check2", "Множественный выбор 2"));
        register(new HelpCommand("help", "Помощь"));
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
            handlerExcecutor.execute(update.getCallbackQuery()
                    .getData(), update, this);
        }
    }

}
