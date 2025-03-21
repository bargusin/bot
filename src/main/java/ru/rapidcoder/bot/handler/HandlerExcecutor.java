package ru.rapidcoder.bot.handler;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

/**
 * Обработчик событий
 */
public class HandlerExcecutor {

    private static final HandlerExcecutor handlerExcecutor = new HandlerExcecutor();

    private final Map<String, Handler> handlers;

    private HandlerExcecutor() {
        handlers = new HashMap<>();
    }

    public static HandlerExcecutor getHandlerExcecutor() {
        return handlerExcecutor;
    }

    public void execute(String callbackData, Update update, TelegramLongPollingCommandBot bot) {
        if (handlers.containsKey(callbackData)) {
            Handler handler = handlers.get(callbackData);
            handler.execute(update, bot);
        } else {
            throw new RuntimeException("Handler by callbackData='" + callbackData + "' not found");
        }
    }

    /**
     * Добавление нового обрабочика событий
     *
     * @param handler обработчик события
     */
    public void add(Handler handler) {
        handlers.put(handler.getCallbackData(), handler);
    }

}
