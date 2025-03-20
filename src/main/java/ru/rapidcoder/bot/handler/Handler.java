package ru.rapidcoder.bot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Обработчик события компонента
 */
public interface Handler {

    BotApiMethod<Serializable> execute(Update update);

    String getCallbackData();

}
