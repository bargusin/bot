package ru.rapidcoder.bot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
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

    public BotApiMethod<Serializable> execute(String callbackData, Update update) {
        if (handlers.containsKey(callbackData)) {
            Handler handler = handlers.get(callbackData);
            return handler.execute(update);
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
