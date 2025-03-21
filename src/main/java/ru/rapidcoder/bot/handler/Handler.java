package ru.rapidcoder.bot.handler;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Обработчик события компонента
 */
public interface Handler {

    void execute(Update update, TelegramLongPollingCommandBot bot);

    String getCallbackData();

}
